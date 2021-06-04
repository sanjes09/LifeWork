const moment = require("moment");

const helpers = {}; 

helpers.tiempo = timestamp => {
    moment(timestamp).startOf("minute").fromNow();
}

module.exports = helpers;