require("./models/connect");
const User = require('./models/User');
const Correo = require('./models/Correo');
const express = require('express');
const cookieSession = require('cookie-session');
const { hash, compare } = require('./models/password');
const appRouter = require('./routes/app');
const path = require('path');

const app = express();
const port = 3001;
// const port = 80;

app.use(express.json({limit: '50mb'}));
app.use(express.urlencoded({extended: true ,limit: '50mb'}))
app.use(express.static(path.join(__dirname,'public')));
app.use(cookieSession({
    name: 'session',
    keys: [
        'dehfmsFcejefihw378ew38hSc4grh9mjec783cuaWDXAi3nf54d8a3ocrmaw3rh78c3hmc3mhar8h3mj3a78h38jxd8ewjscrtodmsj37mxdh38whd8ym84hraxwweui',
        'fkhwecJmkfcsimtiwnxd,eugfir4nhwo,sXDWADjdxepoje.euxhf,euzshmuijh,wudhmwyd#XRieur7duiwnhcuidhsecfrwisycfmuw4bfywdycgemt7fecfiemcyihesmchfemf'
    ]
}));

app.use( '/app', ( req, res, next ) => {
    if (req.session && req.session.id) {
        next();
    } else {
        res.json({
            error: "Not logged in"
        });
    };
});

app.use( '/app', appRouter);

app.get( "/logged", (req,res) => {
    if (req.session && req.session.id) {
        res.json({
            valid: true
        });
    } else {
        res.json({
            valid: false
        });
    };
});

app.post( '/login', async ( req, res ) => {
    const { usuario, password } = req.body;
    if ( ! usuario || ! password ) {
        res.json({ 
            error: "Faltan datos" 
        });
        return;
    };
    const data = await User.find({ usuario })
    if (!data.length) {
        res.json({ 
            error: "Usuario no encontrado"
        });
        return;
    };
    let user = data[0];
    let hashedPassword = user.password;
    let validUser;
    try{
        validUser = await compare(password,hashedPassword);
    }catch(e){
        res.json({ 
            error: "Contraseña incorrecta"
        });
        return;
    };
    if ( validUser ) {
        req.session.id = user._id;
        req.session.usuario = user.usuario;
        req.session.admin = user.admin;
        console.log(req.session.id+" logging in");
        res.json({
            ok: true,
            usuario,
            admin: user.admin
        })
        return;
    }else{
        res.json({
            ok: false,
            error: "Contraseña incorrecta"
        });
        return;
    };
});

app.post( '/signup', async ( req, res ) => {
    if (!req.body.password) {
        res.json({ 
            error: "Falta contraseña"
        });
        return;
    }

    let hashedPassword = await hash(req.body.password);

    let user = new User({
        nombre: req.body.nombre,
        usuario: req.body.usuario,
        correo: req.body.correo,
        password: hashedPassword,
        admin: false
    });

    const data = await Correo.find({ correo: req.body.correo });
    if (!data.length) {
        res.json({ 
            error: "Correo no existe" 
        });
        return;
    };
    const data3 = await User.find({ correo: req.body.correo });
    if (data3.length) {
        res.json({ 
            error: "Ya existe ese correo" 
        });
        return;
    };
    const data2 = await User.find({ usuario: req.body.usuario });
    if (data2.length) {
        res.json({ 
            error: "Ya existe ese usuario" 
        });
        return;
    };

    await user.save();

    res.json({
        ok: true
    });
    return;
});

app.get( '/logout',( req, res )=>{
    console.log(req.session.id, 'logging out');
    req.session = null;
    res.json({
        ok:true
    });
    return;
});

app.get('/confirmAccount/:id', ( req, res ) =>{
    jwt.verify(req.params.id, 'email_validation', async (err, decoded) => {
        if (err) {
            res.json({
                ok: false,
                error: "Token Expired"
            });
            return;
        }else{
            try {
                await User.findOneAndUpdate({_id: decoded.id},{valid:true})
                res.sendFile(path.join(__dirname,'/public/index.html'));
                return;
            } catch (error) {
                res.json({
                    ok: false,
                    error: "user not found"
                });
                return;
            };
        };
    });
});

app.get('*', (req, res) => {
    res.sendFile(path.join(__dirname,'/public/index.html'));
    return;
});

app.listen(port, () => console.log( `App listening on port ${ port }!` ));