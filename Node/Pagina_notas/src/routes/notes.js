const router = require("express").Router();
const Note = require("../models/Note");
const {isAuthenticated} = require("../helpers/auth");

router.get("/notes", isAuthenticated, async (req,res) => {
    const notes = await Note.find({user: req.user._id}).sort({fecha: "desc"});    
    res.render("notes/notes", {notes});
});

router.post("/notes/new-note", isAuthenticated, async (req,res) => {
    const note = new Note({titulo: req.body.titulo, descripcion: req.body.descripcion, user: req.user._id});
    await note.save();
    req.flash("success_msg", "Nota creada con exito!");
    res.redirect("/notes");
});

router.get("/notes/edit/:id", isAuthenticated, async (req,res) => {
    const nota = await Note.findById(req.params.id);
    res.render("notes/edit-note", {nota});
});

router.put("/notes/edit-note/:id", isAuthenticated, async (req,res) => {
    await Note.findByIdAndUpdate(req.params.id, {titulo: req.body.titulo, descripcion: req.body.descripcion});
    req.flash("success_msg", "Nota modificada con exito!");
    res.redirect("/notes");
});

router.delete("/notes/delete/:id", isAuthenticated, async (req,res) => {
    await Note.findByIdAndRemove(req.params.id);
    req.flash("error_msg", "Nota Eliminada con exito!");
    res.redirect("/notes");
});

router.get("/notes/add", isAuthenticated, (req,res) => {
    res.render("notes/new-note");
});

module.exports = router;