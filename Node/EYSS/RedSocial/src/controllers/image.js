const path = require("path");
const {randomNumber} = require("../helpers/lib");
const fileUpload = require('express-fileupload')
const fs2 = require('fs')
const fs = require("fs-extra");
const Image = require("../models/Image");
const Comment = require("../models/comment");
const md5 = require("md5");

const ctrl = {};

ctrl.index = async (req,res) =>{
    const imagenes = await Image.find().sort({date:-1});
    res.render("my_images",{imagenes});
};

// ctrl.look = async (req,res) =>{
//     const imagen = await Image.findOne({"_id": req.params.id});
//     if(imagen){
//         imagen.views = imagen.views + 1;
//         await imagen.save();
//         const comments = await Comment.find({"image_id": req.params.id});
//         res.render("image",{imagen, comments});
//     }else{
//         res.redirect("/");
//     }
// };

ctrl.look = async (req,res) =>{
    const bucket = new mongoose.mongo.GridFSBucket(mongoose.connections[0].db)
    const id = req.params.id

    const p = await Image.findOne({"_id": req.params.id});
    bucket.openDownloadStreamByName(id).
        pipe(res).
        // pipe(fs.createWriteStream('./uploads/'+p[0].image.name)).
        on('error', function(error) {
            assert.ifError(error)
        })
};

ctrl.upload = (req,res) =>{
    res.render("uploadImage");
};

ctrl.create = (req,res) =>{
    const saveImagen = async () =>{
        const imgURL = randomNumber();
        const imagenes = await Image.find({filename: imgURL});
        if(imagenes.length > 0){
            saveImagen();
        }else{
            const ext = path.extname(req.file.originalname).toLowerCase();
            const tempPath = req.file.path;
            const targetPath = path.resolve(`src/public/upload/${imgURL}${ext}`);
            if(ext === ".png" || ext === ".jpg" || ext === ".jpeg" || ext === ".gif"){
                await fs.rename(tempPath,targetPath);
                const imagen = new Image({
                    title: req.body.title,
                    description: req.body.description,
                    filename: imgURL + ext,
                    userfilename: req.file.originalname 
                });
                const imageSaved = await imagen.save();
                res.redirect("/");
            }else{
                await fs.unlink(tempPath);
                res.status(500).json({error: "Only images are allowed"});
            }
        }
    }
    saveImagen();
};

ctrl.like = async (req,res) =>{
    const imagen = await Image.findOne({"_id": req.params.id});
    if(imagen){
        imagen.likes = imagen.likes + 1;
        await imagen.save();
        res.json({likes: imagen.likes});
    }else{
        res.redirect("/");
    }
};

ctrl.unlike = async (req,res) =>{
    const imagen = await Image.findOne({"_id": req.params.id});
    if(imagen){
        imagen.likes = imagen.likes - 1;
        await imagen.save();
        res.json({likes: imagen.likes});
    }else{
        res.redirect("/");
    }
};

ctrl.comment = async (req,res) =>{
    const image = await Image.findOne({"_id":req.params.id});
    if(image){
        const comment = new Comment(req.body);
        comment.gravatar = md5(comment.email);
        comment.image_id = req.params.id;
        await comment.save();    
        res.redirect("/images/"+req.params.id);    
    }else{
        res.redirect("/");
    }
};

ctrl.remove = async (req,res) =>{
    const img = await Image.findOneAndRemove({"_id":req.params.id});
    await fs.unlink("src/public/upload/"+img.filename);
    await Comment.deleteMany({"image_id":img._id});
    res.json(true);
};

module.exports = ctrl;