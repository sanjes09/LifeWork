const bcrypt = require('bcrypt');
const saltRounds = 10;

exports.hash = password =>{
    return new Promise( ( succ, exceptionHandler ) => {
        bcrypt.hash( password, saltRounds, ( err, hash ) => {
            if(err){
                exceptionHandler(err);
            } else {
                succ(hash);
            };
        });
    });
};

exports.compare = ( toCompare, hash ) =>{
    return new Promise( ( succ, exceptionHandler ) => {
        bcrypt.compare(toCompare, hash, function(err, res) {
            if(err){
                exceptionHandler(err);
            } else {
                succ(res);
            };
        });
    });
};
