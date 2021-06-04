const express = require("express");
const router = require("express").Router();
const User = require("../models/User");
const passport = require("passport");
const {isAuthenticated, alreadyAuthenticated} = require("../helpers/auth");

router.get("/users/logout", (req,res) => {
    req.logOut();
    res.redirect("/");
});

router.get("/users/signin", (req,res) => {
    res.render("users/signin");
});

router.post("/users/signin", passport.authenticate("local", {
    successRedirect: "/notes",
    failureRedirect: "/users/signin",
    failureFlash: true
}));

router.get("/users/signup", (req,res) => {
    res.render("users/signup");
});

router.post("/users/signup", async (req,res) => {
    const emailUser = await User.findOne({email:req.body.email});
    if(emailUser){
        req.flash("error_msg", "Email ya en uso");
        res.redirect("/users/signup");
    }
    const user = new User({username: req.body.username, email: req.body.email, password: req.body.password});
    user.password = await user.encryptPassword(req.body.password);
    await user.save();
    req.flash("success_msg", "Registro exitoso!");
    res.redirect("/users/signin");
});

router.get("/users/:id", isAuthenticated, async (req,res) => {
    const user = await User.findOne({_id :req.params.id});
    
    res.render("users/home",{user});
});

module.exports = router;