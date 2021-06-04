var mongoose = require("mongoose");
var Schema = mongoose.Schema;

mongoose.connect("mongodb://localhost/prueba", { useNewUrlParser: true });

var user_schema = new Schema({
    nombre: {
        type: String, 
        required: "el nombre es obligatorio", 
        maxlength:[20,"no puede ser mayor a 20 caracteres"], 
        minlength:[4,"no puede ser menor a 4 caracteres"]
    },
    cedula: {
        type: Number, 
        min: [1000000,"La cedula no puede ser menor que 1000000"], 
        max: [30000000,"La cedula no puede ser mayor a 30000000"]
    },
    dir: String,
    password: {
        type: String,
        minlength: [8,"contrasenia muy corta"],
        validate: {
            validator: function (p){
                return this.password_confirmation == p;
            },
            message: "Las constrasenias no son iguales"
        }
    },
    sex: {
        type:String, 
        enum: {
            values: ["M","F"], 
            message: "opcion no valida"
        }
    },
    correo: {
        type: String, 
        required: "el correo es obligatorio", 
        match: [/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/,"coloca un email valido"]
    }
});

user_schema.virtual("password_confirmation").get(function(){
    return this.p_c;
}).set(function(password){
    this.p_c = password;
});

/*
user_schema.virtual("full_name").get(function(){
    return this.name + this.last_name;
}).set(function(full_name){
    var words = full_name.split(" ");
    this.name = words[0];
    this.last_name = words[1];
});
*/

var User = mongoose.model("User", user_schema);

module.exports.User = User;
