const express = require("express");
const router = express.Router();

const home = require("../controllers/home");
const image = require("../controllers/image");


module.exports = app => {
    //HOME
    router.get("/", home.index);

    //IMAGEN
    router.get("/images", image.index);
    router.get("/images/upload", image.upload);
    router.post("/images/upload", image.create);
    router.post("/images/:id/like", image.like);
    router.post("/images/:id/unlike", image.unlike);
    router.post("/images/:id/comment", image.comment);
    router.get("/images/:id", image.look);
    router.delete("/images/:id", image.remove);

    app.use(router);
};