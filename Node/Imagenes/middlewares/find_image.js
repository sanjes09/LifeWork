var Imagen = require("../models/imagenes");
var owner_check = require("./image_permission");


module.exports = function(req,res,next){
    Imagen.findById(req.params.id)
                    .populate("creador")
                    .exec(function(err,imagen){                        
                        if(imagen != null && owner_check(imagen,req,res)){
                            res.locals.imagen = imagen;
                            next();
                        }else{
                            res.redirect("/app");
                        }
                    })
}