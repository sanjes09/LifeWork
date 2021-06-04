const path = require("path");
const exphbs = require("express-handlebars");
const morgan = require("morgan");
const multer = require("multer");
const express = require("express");
const errorHandler = require("errorhandler");
const route = require("./routes/index");
const app = express();

//DATABASE
require("./database");

//SETTINGS
app.set("port", process.env.PORT || 1234);
app.set("views", path.join(__dirname, "./views"));
app.engine(".hbs", exphbs({
    defaultLayout: "main",
    partialsDir: path.join(app.get("views"), "partials"),
    layoutsDir: path.join(app.get("views"), "layouts"),
    extname: ".hbs",
    helpers: require("./server/helpers")
}));
app.set("view engine", ".hbs");

//MIDDLEWARES
app.use(morgan("dev"));
app.use(multer({dest: path.join(__dirname,"./public/upload/temp")}).single("image"));
app.use(express.urlencoded({extended: false}));
app.use(express.json());

//GLOBAL VARIABLES

//ROUTER
route(app);

//STATIC FILES
app.use("/public", express.static(path.join(__dirname,"./public")));

//ERRORHANDLERS
if("development" === app.get("env")){
    app.use(errorHandler);
}

//STARTING SERVER
app.listen(app.get("port"), () => {
    console.log("Server on port", app.get("port"));
});