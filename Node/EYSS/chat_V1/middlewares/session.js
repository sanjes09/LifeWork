module.exports = function(req,res,next){
    if(!req.session.name){
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