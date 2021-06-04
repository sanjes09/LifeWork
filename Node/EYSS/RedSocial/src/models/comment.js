const mongoose = require("mongoose");
const {Schema} = mongoose;
const {ObjectId} = Schema;

const CommentSchema = new Schema({
    image_id: {type: ObjectId},
    name: {type: String, required: "titulo no puede estar vacio."},
    email: {type: String, required: "descripcion no puede estar vacia."},
    gravatar: {type: String},
    comment: {type: String, required: "comentario no puede estar vacia."},
    timestamp: {type: Date, default: Date.now}
});

module.exports = mongoose.model("Comment", CommentSchema);