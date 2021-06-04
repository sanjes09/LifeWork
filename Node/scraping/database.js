const mongoose = require("mongoose");

mongoose.connect("mongodb+srv://admin:admin@chat-dwrlc.mongodb.net/scraping?retryWrites=true&w=majority", {
    useUnifiedTopology: true,
    useCreateIndex: true,
    useNewUrlParser: true,
    useFindAndModify: false
})
    .then(db => console.log("Db connected\n"))
    .catch(err => console.error(err));