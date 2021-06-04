var express = require("express");
var Imagen = require("./models/imagenes");
var router = express.Router();
var image_finder = require("./middlewares/find_image");
var fs = require("fs");


router.get("/",function(req,res){
    res.render("app/index");
});

router.get("/logout",function(req,res){
    req.session.user_id = null;
    res.redirect("index");
});

router.get("/imagenes/new",function(req,res){
    res.render("app/imagenes/new");
})

router.all("/imagenes/:id*",image_finder);

router.get("/imagenes/:id/edit",function(req,res){
    res.render("app/imagenes/edit");
});

router.route("/imagenes/:id")
    .get(function(req,res){
        res.render("app/imagenes/show");
    })
    .put(function(req,res){
        res.locals.imagen.titulo = req.body.titulo;
        res.locals.imagen.save(function(err){
            if(!err){
                res.redirect("/app/imagenes");
            }else{
                res.render("app/imagenes/"+req.params.id+"/edit");                    
            }
        })
    })
    .delete(function(req,res){
        Imagen.findOneAndRemove({ _id: req.params.id}, function(err,imagen){
            if(!err){            
                console.log(req.params.id+"."+imagen.extension);
                    
                fs.unlink("./public/imagenes/"+req.params.id+"."+imagen.extension, function(err){});
                res.redirect("/app/imagenes");
            }else{
                console.log(err);
                res.redirect("/app/imagenes"+req.params.id);
            }
        });
    })

router.route("/imagenes")
    .get(function(req,res){
        Imagen.find({creador: res.locals.user._id},function(err,imagenes){
            if(err){ res.redirect("/app"); return;}
            res.render("app/imagenes/index",{imagenes: imagenes});
        })
    })
    .post(function(req,res){
        var extension = req.files.archivo.name.split(".").pop();
        var data = {
            titulo: req.body.titulo,
            creador: res.locals.user._id,
            extension: extension
        }
        var imagen = new Imagen(data);
        imagen.save(function(err){
            if(!err){
                fs.rename(req.files.archivo.path, "public/imagenes/"+imagen._id+"."+extension,function(){});
                res.redirect("/app/imagenes");
            }else{
                res.render(err);
            }
        });
    });


module.exports = router;