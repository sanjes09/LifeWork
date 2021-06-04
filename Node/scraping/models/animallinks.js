var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var animalLinksSchema = new Schema({
    url: String
})

var animalLinks = mongoose.model("animalLinks",animalLinksSchema);

module.exports = animalLinks;