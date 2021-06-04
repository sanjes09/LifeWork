var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var img_schema = new Schema({
    titulo: {
        type: String, 
        required: true
    },
    creador: {
        type: Schema.Types.ObjectId,
        ref:"User"
    },
    extension:{
        type: String,
        required: true
    }
})

var Imagen = mongoose.model("Imagen",img_schema);

module.exports = Imagen;