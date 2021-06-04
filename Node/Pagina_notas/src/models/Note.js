const mongoose = require("mongoose");
const {Schema} = mongoose;

const NoteSchema = new Schema({
    titulo: {type: String, required: "titulo no puede estar vacio."},
    descripcion: {type: String, required: "descripcion no puede estar vacia."},
    fecha: {type: Date, default: Date.now},
    user: {type: String}
});

module.exports = mongoose.model("Note", NoteSchema);