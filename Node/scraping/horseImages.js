const cheerio = require("cheerio");
const fs = require("fs");
const request = require("request");
const Imagen = require("../../models/imagenes");
require("../../database");

let images = [];

async function start () { 
    let lks = "";
    let $ = "";

    lks = await Imagen.find({});
    let x = lks.length;

    while(lks.length){
        let element = lks.pop();
        request(element.url, async (err,res,body) => {
            await Imagen.findOneAndDelete({url: element.url});
            x--;
            console.log("faltan: "+ x);
        }).pipe(fs.createWriteStream("./images/breeder/"+element.name));
    }
}
start();