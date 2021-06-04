const express = require("express");
const app = express();
const path = require("path");

//SETTINGS
app.set("port",process.env.PORT || 1234);
app.set("views",path.join(__dirname,"views"));
app.engine("html",require("ejs").renderFile);
app.set("view engine", "ejs");

//MIDDLEWARES


//ROUTES
app.use(require("./routes/index"));

//STATIC FILES


app.listen(app.get("port"),() => {
    console.log("Ejecutando en el puerto 1234");
});