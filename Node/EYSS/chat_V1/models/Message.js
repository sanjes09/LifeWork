const mongoose = require("mongoose");
const {Schema} = mongoose;

const MessageSchema = new Schema({
    user: {type: String, required: "titulo no puede estar vacio."},
    msg: {type: String, required: "descripcion no puede estar vacia."}
});

module.exports = mongoose.model("Message", MessageSchema);