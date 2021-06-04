//DEPENDENCIES
const http =  require("http");
const express = require("express");
const socket = require("socket.io");
const path = require('path');
const cookieSession = require('cookie-session');
const User = require("./models/User");
const bcrypt = require("bcryptjs");

require("./database");

//CONFIG
const app = express();
app.set("port", 3001);
app.use(cookieSession({
    name: 'session',
    keys: [
        'dehfmsFcejefihw378ew38hSc4grh9mjec783cuaWDXAWDdAe9rj,a3ocrmaw3rh78c3hmc3mhar8h3mj3a78h38jxd8ewjscripiejq37mxdh38whd8ym84hraxwweui',
        'fkhwecJmkfcsimxlzexd,eufhzmfhwo,sXDWADjdxepoje.euxhf,euzshmuijh,wudhmwyd#XRiqwzdnuiwnhcuidhsecfgeycfmuw4bfywdycgemt7fecfiemcyihesmchfemf'
    ]
}))

//STATIC FILES
app.use(express.static(path.join(__dirname,'public')));

//USER
app.get("/signup", async (req,res) => {
    let data = JSON.parse(req.query.data);

    let user = new User();
    user.username = data.username;
    user.email = data.email;
    user.password = await user.encryptPassword(data.password);

    const emailUser = await User.findOne({email:data.email});
    const usernameUSer = await User.findOne({username:data.username});
    if(emailUser){
        res.json({
            ok: false,
            error: "Email already in use"
        });
    }else{
        if(usernameUSer){
            res.json({
                ok: false,
                error: "Username already in use"
            });
        }else{
            const user = new User({username: data.username, email: data.email, password: data.password});
            user.password = await user.encryptPassword(data.password);req.body
            await user.save();
            res.json({
                ok: true
            });
        }
    }
});

app.get("/login" , async (req,res) => {
    let data = JSON.parse(req.query.data);
    let user = await User.find({username: data.username});
    if (!user.length) {
        res.json({
            ok: false,
            error: "User not found"
        })
        return
    }
    user = user[0];
    let hashedPassword = user.password
    let validUser
    try{
        validUser = await bcrypt.compare(data.password,hashedPassword)
    }catch(e){
        res.json({
            ok: false,
            error: "Unknown Error"
        })
        return
    }
    if ( validUser ) {
        req.session.name=data.username;
        res.json({
            ok: true,
        })
        return
    }else{
        res.json({
            ok: false,
            error: "Invalid User"
        })
        return        
    }
})

app.get("/logged" , (req,res) => {
    if(req.session.name){
        res.json({
            name: req.session.name
        });
    }else{
        res.json({
            name: ""
        });
    }
})

app.get("/logout" , (req,res) => {
    let x = req.session.name;
    req.session = null;
    res.json({
        ok: true,
        name: x
    });
})


const server = http.createServer(app);
const io = socket.listen(server);

require("./sockets")(io);

//SERVER START
server.listen(app.get("port"), () => {
    console.log("Server on port "+app.get("port"));
})