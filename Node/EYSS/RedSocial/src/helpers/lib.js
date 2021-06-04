const helpers = {};

helpers.randomNumber = () => {
    const possible = "asdfghjklqwertyuiopzxcvbnm1234567890";
    let number = "";
    for(let i = 0; i < 6; i++){
        number += possible.charAt(Math.floor(Math.random()*possible.length));
    }
    return number;
};

module.exports = helpers;