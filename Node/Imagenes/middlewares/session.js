var User = require("../models/user").User;

module.exports = function(req,res,next){
    if(!req.session.user_id){
        res.redirect("/login");
    }
    else{
        User.findById(req.session.user_id,function(err,user){
            if(err){
                console.log(err);
                res.redirect("/login");
            }else{
                res.locals = {user: user};
                next();
            }
        })
    }
};