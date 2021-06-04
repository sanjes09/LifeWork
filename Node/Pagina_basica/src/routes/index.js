const express = require("express");
const router = express.Router();


router.get("/", (req,res) => {
    res.render("index.html",{titulo: "Pagina de prueba"});
    
});

router.get("/contact", (req,res) => {
    res.render("contact.html",{titulo: "Pagina de contacto"});
    
});


module.exports = router;