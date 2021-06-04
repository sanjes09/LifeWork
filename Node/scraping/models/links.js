var mongoose = require("mongoose");
var Schema = mongoose.Schema;

var linksSchema = new Schema({
    url: String
})

var links = mongoose.model("links",linksSchema);

module.exports = links;