const mongoose = require('mongoose')

const schema = new mongoose.Schema({
    last: [String],
    reserva12: [
        {
            usuario: String,
            posicion: Number,
            disponible: Boolean
        }
    ],
    reserva1230: [
        {
            usuario: String,
            posicion: Number,
            disponible: Boolean
        }
    ],
    reserva1: [
        {
            usuario: String,
            posicion: Number,
            disponible: Boolean
        }
    ],
    reserva145: [
        {
            usuario: String,
            posicion: Number,
            disponible: Boolean
        }
    ],
    reserva2: [
        {
            usuario: String,
            posicion: Number,
            disponible: Boolean
        }
    ],
    fecha: String
})

module.exports = mongoose.model('Horario', schema,'Horario')
