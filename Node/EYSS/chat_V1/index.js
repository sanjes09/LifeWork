//DEPENDENCIES
const http =  require("http");
const express = require("express");
const socket = require("socket.io");
const path = require('path');

require("./database");

//CONFIG
const app = express();
app.set("port", process.env.PORT || 4000);

//STATIC FILES
app.use(express.static(path.join(__dirname,'public')));

const server = http.createServer(app);
const io = socket.listen(server);

require("./sockets")(io);

//SERVER START
server.listen(app.get("port"), () => {
    console.log("Server on port "+app.get("port"));
})