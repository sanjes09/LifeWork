const mongoose = require("mongoose");
const {Schema} = mongoose;
const path = require("path");

const ImageSchema = new Schema({
    title: {type: String, required: "titulo no puede estar vacio."},
    description: {type: String, required: "descripcion no puede estar vacia."},
    filename: {type: String},
    userfilename: {type: String}, 
    views: {type: Number, default: 0},
    likes: {type: Number, default: 0},
    timestamp: {type: Date, default: Date.now}
});

ImageSchema.virtual("uniqueID")
            .get(function (){
                return this.filename.replace(path.extname(this.filename),"");
            });

module.exports = mongoose.model("Image", ImageSchema);