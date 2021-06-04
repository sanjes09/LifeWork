const imagen = require("../models/Image");
const ctrl = {};

ctrl.index = async (req,res) => {
    const todas = await imagen.find().sort({date:-1});
    res.render("index", {todas});
};

module.exports = ctrl;