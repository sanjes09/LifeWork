var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var imgSchema = new Schema({
    url: String,
    name: String
})

var img = mongoose.model("img",imgSchema);

module.exports = img;