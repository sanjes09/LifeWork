const mongoose = require('mongoose')

mongoose.connect("mongodb+srv://admin:admin@chat-dwrlc.mongodb.net/comedor?retryWrites=true&w=majority", {
    useUnifiedTopology: true,
    useNewUrlParser: true,
    useFindAndModify: false
})
    .then(db => console.log("DB connected"))
    .catch(err => console.error(err));