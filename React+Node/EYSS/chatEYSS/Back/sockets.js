const Message = require("./models/Message");
const PrivMessage = require("./models/PrivMessage");

module.exports = function (io){
    let nicknames = [];
    let Activos = [];

    io.on("connection", socket => {

        socket.on("load chat", async data => {
            if(Activos.findIndex(x => x.id == socket.id) != -1){ 
                try{
                    data(await Message.find({}));
                }catch(e){
                    console.log(e);
                }
            }
        });

        socket.on("load private chat", async (username, data) => {
            if(Activos.findIndex(x => x.id == socket.id) != -1){
                try{   
                    const todos = await PrivMessage.find({$or:[{from: socket.nickname, to: username},{to: socket.nickname, from: username}]}).sort({date: "asc"});
                    data(todos);
                }catch(e){
                    console.log(e);
                }
            }
        });

        socket.on("send message", async function (data) {
            if(Activos.findIndex(x => x.id == socket.id) != -1){
                try{
                    Activos.forEach(sockt => {
                        io.sockets.connected[sockt.id].emit("new message", {user: socket.nickname, msg: data}); 
                    });
                    const newMessage = new Message({user: socket.nickname, msg: data});
                    await newMessage.save();
                }catch(e){
                    console.log(e); 
                }
            }
        });

        socket.on("send priv message", async function (username, data) {
            if(Activos.findIndex(x => x.id == socket.id) != -1){
                try{
                    let x = "";
                    Activos.forEach(user => {
                        if(user.name === username){
                            x = user.id;
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
            if(Activos.findIndex(x => x.ip == socket.handshake.address) != -1){ 
                x=1;
                callback(2);
            }
            if(x==0){
                callback(0);
                socket.nickname = username;
                nicknames.push(username);
                Activos.push({ip: socket.handshake.address, id: socket.id, name: socket.nickname})
                io.sockets.emit("usernames", nicknames);
            }
        });

        socket.on("logout", function (name){
            if(!socket.nickname) return;
            nicknames.splice(nicknames.indexOf(socket.nickname),1);
            Activos.splice(Activos.findIndex(x => x.id == socket.id));
            io.sockets.emit("usernames", nicknames);
        })

        socket.on("disconnect", data => {
            if(!socket.nickname) return;
            nicknames.splice(nicknames.indexOf(socket.nickname),1);
            Activos.splice(Activos.findIndex(x => x.id == socket.id));
            io.sockets.emit("usernames", nicknames);
        })
    });
}