const mongoose = require('mongoose')

const schema = new mongoose.Schema({
    correo: String,
})

module.exports = mongoose.model('Correo', schema,'Correo')
