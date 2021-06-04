var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var animalSchema = new Schema({
    name: String,
    registration_no: String,
    date_of_birth: String,
    best_lactation: String,
    classification_score: String,
    index: String,
    link: String,
    details: String,
    image:{
        url: String,
        caption: String
    }
})

var Animal = mongoose.model("Animal",animalSchema);

module.exports = Animal;