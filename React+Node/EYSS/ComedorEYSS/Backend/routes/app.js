const Horario = require('../models/Horario');
const Correo = require('../models/Correo');
const router = require("express").Router();
const fs = require('fs');

router.post("/agregarCorreo", async (req,res) => {
    try {
        if(req.body.correo.indexOf('@') !== -1){
            let host = req.body.correo.split('@');
            if(host[1] === 'eyss.io'){
                if(host[0].indexOf(' ') === -1){
                    let x = await Correo.find({correo: req.body.correo})
                    if (!x.length) {
                        let nuevo = new Correo({correo: req.body.correo})
                        await nuevo.save();
                        res.json({ 
                            ok: true
                        });
                    }else{
                        res.json({ 
                            ok: false,
                            error: "Correo ya existe"
                        });
                    }
                }else{
                    res.json({ 
                        ok: false,
                        error: "No puede contener espacio"
                    });
                }
            }else{
                res.json({ 
                    ok: false,
                    error: "Correo no válido, solo puede ser @eyss.io"
                });
            }
        }else{
            res.json({ 
                ok: false,
                error: "Correo no válido!"
            });
        }
    } catch (error) {
        res.json({ 
            ok: false,
            error: 'Error desconocido'
        });
    }
})

router.post("/eliminarReserva", async (req,res) => {
    try {
        let x = new Date(Date.now());
        let fecha = x.getDate()+'-'+x.getMonth()+1+'-'+x.getFullYear();
        let horario = await Horario.findOne({fecha});
        let eliminar = horario;
        let borrado = '';
        var nuevo = eliminar["reserva"+req.body.hora].map((puesto) => {
          if(puesto.posicion === req.body.posicion){
            borrado = puesto.usuario;
            puesto.usuario = '';
            puesto.disponible = true;
          }
          return puesto;
        });
        eliminar["reserva"+req.body.hora] = nuevo;
        horario = eliminar;
        await horario.save();
        res.json({
            ok: true,
            horario,
            borrado
        });
    } catch (error) {
        res.json({
            ok: false,
            error
        });
    }
})

router.post("/nuevoEspacio", async (req,res) => {
    try {
        let x = new Date(Date.now());
        let fecha = x.getDate()+'-'+x.getMonth()+1+'-'+x.getFullYear();
        let horario = await Horario.findOne({fecha});
        let nuevo = horario;
        if(nuevo["reserva"+req.body.espacio][nuevo["reserva"+req.body.espacio].length-1].posicion + 1 > 5){
            res.json({
                ok: false,
                error: 'Maximo 6 puestos por hora'
            });
        }else{
            nuevo["reserva"+req.body.espacio].push({usuario: '',posicion: nuevo["reserva"+req.body.espacio][nuevo["reserva"+req.body.espacio].length-1].posicion + 1,disponible: true});
            horario = nuevo;
            await horario.save();
            res.json({
                ok: true,
                horario
            });
        }
    } catch (error) {
        res.json({
            ok: false,
            error
        });
    }
})

router.get("/date", async (req,res) => {
    let x = new Date(Date.now());
    let nuevo = false;
    let estoy = false;
    let fecha = x.getDate()+'-'+x.getMonth()+1+'-'+x.getFullYear();
    let horario = await Horario.find({fecha});
    if(!horario.length){
        nuevo = true;
        horario = new Horario({
            last: [],
            reserva12: [
                {
                    usuario: '',
                    posicion: 0,
                    disponible: true
                },
                {
                    usuario: '',
                    posicion: 1,
                    disponible: true
                },
                {
                    usuario: '',
                    posicion: 2,
                    disponible: true
                },
                {
                    usuario: '',
                    posicion: 3,
                    disponible: true
                },
                {
                    usuario: '',
                    posicion: 4,
                    disponible: true
                },
                {
                    usuario: '',
                    posicion: 5,
                    disponible: true
                }
            ],
            reserva1230: [
                {
                    usuario: '',
                    posicion: 0,
                    disponible: true
                },
                {
                    usuario: '',
                    posicion: 1,
                    disponible: true
                },
                {
                    usuario: '',
                    posicion: 2,
                    disponible: true
                },
                {
                    usuario: '',
                    posicion: 3,
                    disponible: true
                },
            ],
            reserva1: [
                {
                    usuario: '',
                    posicion: 0,
                    disponible: true
                },
                {
                    usuario: '',
                    posicion: 1,
                    disponible: true
                }
            ],
            reserva145: [
                {
                    usuario: '',
                    posicion: 0,
                    disponible: true
                },
                {
                    usuario: '',
                    posicion: 1,
                    disponible: true
                },
                {
                    usuario: '',
                    posicion: 2,
                    disponible: true
                },
                {
                    usuario: '',
                    posicion: 3,
                    disponible: true
                },
            ],
            reserva2: [
                {
                    usuario: '',
                    posicion: 0,
                    disponible: true
                },
                {
                    usuario: '',
                    posicion: 1,
                    disponible: true
                },
                {
                    usuario: '',
                    posicion: 2,
                    disponible: true
                },
                {
                    usuario: '',
                    posicion: 3,
                    disponible: true
                },
                {
                    usuario: '',
                    posicion: 4,
                    disponible: true
                },
            ],
            fecha: fecha
        });
        await horario.save();
    }else{
        horario = horario[0]
    }
    if(!nuevo){
        horario.reserva12.forEach(element => {
            if(element.usuario === req.session.usuario){
                estoy = true;
            }
        });
        if(!estoy){
            horario.reserva1230.forEach(element => {
                if(element.usuario === req.session.usuario){
                    estoy = true;
                }
            });
        }
        if(!estoy){
            horario.reserva1.forEach(element => {
                if(element.usuario === req.session.usuario){
                    estoy = true;
                }
            });
        }
        if(!estoy){
            horario.reserva145.forEach(element => {
                if(element.usuario === req.session.usuario){
                    estoy = true;
                }
            });
        }
        if(!estoy){
            horario.reserva2.forEach(element => {
                if(element.usuario === req.session.usuario){
                    estoy = true;
                }
            });
        }
    }
    res.json({
        ok: true,
        date: Date.now(),
        horario,
        usuario: req.session.usuario,
        estoy,
        admin: req.session.admin
    });
});

router.post("/reservar", async (req,res) => {
    let saved = false;
    let horario = await Horario.findOne({fecha: req.body.fecha});
    horario["reserva"+req.body.posicion[0]].forEach(element => {
        if(element.posicion === req.body.posicion[1]){
            if(element.usuario === ''){
                element.usuario = req.session.usuario;
                element.disponible = false;
                saved = true
            }
        }
    });
    if(horario.last.indexOf(req.session.usuario) === -1){
        horario.last.push(req.session.usuario);
    }

    await horario.save();
    if(saved){
        res.json({
            ok: true,
            horario
        });
    }else{
        res.json({
            ok: false,
            error: "El puesto ya se ocupó",
            horario
        });
    }
});

module.exports = router;