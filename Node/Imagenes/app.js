var express = require("express");
var app = express();
var bodyParser = require("body-parser");
var User = require("./models/user").User;
// var session = require("express-session");
var cookieSession = require("cookie-session");
var router_app = require("./routes_app");
var session_middleware = require("./middlewares/session");
var methodOverride = require("method-override");
var formidable = require("express-form-data");

app.use(methodOverride("_method"));
app.use("/public",express.static("public"));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

app.use(cookieSession({
    nombre: "session",
    keys: ["lalve-1","lalve-2"]
}));
// app.use(session({
//     secret: "12345678",
//     resave: true,
//     saveUninitialized: true
//     /*genid: function(req){

//     }*/
// }));

app.use(formidable.parse({keepExtensions: true}));

app.use("/app",session_middleware);
app.use("/app",router_app);

app.set("view engine","jade");

app.get("/",function(req,res){
    res.render("index");
});

app.get("/login",function(req,res){
    res.render("login");
});

app.post("/login",function(req,res){
    // User.find(function(err,doc){
    //     console.log(doc);
    //     res.render("index");
    // });

    User.findOne({nombre: req.body.nombre, password: req.body.pass},/*"nombre correo",*/function(err,user){
        req.session.user_id = user._id;
        res.render("index");
    })
});

app.get("/reg",function(req,res){
    res.render("reg");
});

app.post("/reg",function(req,res){
    
    var user = new User({
                        nombre: req.body.nombre, 
                        dir: req.body.dir, 
                        cedula: req.body.cedula, 
                        password: req.body.password, 
                        password_confirmation: req.body.password_confirmation, 
                        sex: req.body.sex, 
                        correo: req.body.correo
                    });

    // user.save(function(err) {
    //     if(err){
    //         console.log(String(err));
    //     } else {
    //         res.render("index");
    //     }
    // });

    user.save().then(function(us){
        res.send("Guardado exitoso");
    },function(err){
        if(err){
            console.log(String(err));
            res.send("No pudimos guardar la informacion");
        }
    })
});


app.listen(1234);
