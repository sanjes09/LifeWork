const mongoose = require("mongoose");
const {Schema} = mongoose;

const PrivMessageSchema = new Schema({
    from: {type: String, required: "titulo no puede estar vacio."},
    to: {type: String, required: "titulo no puede estar vacio."},
    msg: {type: String, required: "descripcion no puede estar vacia."},
    date: {type: Date, default: Date.now}
});

module.exports = mongoose.model("PrivMessage", PrivMessageSchema);