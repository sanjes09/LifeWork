const mongoose = require('mongoose')

const schema = new mongoose.Schema({
    nombre: String,
    usuario: String,
    correo: String,
    password: String,
    admin: {type:Boolean, default: false}
})

module.exports = mongoose.model('User', schema,'User')
