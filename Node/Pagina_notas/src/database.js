const mongoose = require("mongoose");

mongoose.connect("mongodb://localhost/prueba", {
    useCreateIndex: true,
    useNewUrlParser: true,
    useFindAndModify: false
})
    .then(db => console.log("Db connected"))
    .catch(err => console.error(err));