const Message = require("./models/Message");
const PrivMessage = require("./models/PrivMessage");

module.exports = function (io){
    let nicknames = [];
    let connections = [];
    let users = [];

    io.on("connection", socket => {
        console.log("connectes socket",socket.handshake.address);        

        socket.on("load chat", async data => {
            if(connections.indexOf(socket.id) != -1){ 
                try{
                    data(await Message.find({}));
                }catch(e){
                    console.log(e);
                }
            }
        });

        socket.on("load private chat", async (username, data) => {
            if(connections.indexOf(socket.id) != -1){
                try{   
                    const todos = await PrivMessage.find({$or:[{from: socket.nickname, to: username},{to: socket.nickname, from: username}]}).sort({date: "asc"});
                    data(todos);
                }catch(e){
                    console.log(e);
                }
            }
        });

        socket.on("send message", async function (data) {
            if(connections.indexOf(socket.id) != -1){
                try{
                    connections.forEach(id => {
                        io.sockets.connected[id].emit("new message", {user: socket.nickname, msg: data}); 
                    });
                    const newMessage = new Message({user: socket.nickname, msg: data});
                    await newMessage.save();
                }catch(e){
                    console.log(e); 
                }
            }
        });

        socket.on("send priv message", async function (username, data) {
            if(connections.indexOf(socket.id) != -1){
                try{
                    let x = "";
                    users.forEach(e => {
                        if(e.name === username){
                            x = e.id;
                        }
                    });
                    io.sockets.connected[x].emit("new priv message", {from: socket.nickname, msg: data});
                    const newPrivMessage = new PrivMessage({from: socket.nickname, to: username, msg: data});
                    await newPrivMessage.save();
                }catch(e){
                    console.log(e); 
                }
            }
        });

        socket.on("new user", (username, callback) => {
            let x = 0;
            if(nicknames.indexOf(username) != -1){
                x=1;
                callback(1);
            } 
            if(connections.indexOf(socket.id) != -1){ 
                x=1;
                callback(2);
            }
            if(x==0){
                callback(0);
                connections.push(socket.id);
                users.push({id: socket.id, name: username});
                socket.nickname = username;
                nicknames.push(socket.nickname);
                io.sockets.emit("usernames", nicknames);
            }
        });

        socket.on("disconnect", data => {
            if(!socket.nickname) return;
            connections.splice(connections.indexOf(socket.id,1));
            nicknames.splice(nicknames.indexOf(socket.nickname),1);
            io.sockets.emit("usernames", nicknames);
        })
    });
}

