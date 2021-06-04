const cheerio = require("cheerio");
const request = require("request");
const Links = require("./models/links");
const AnimalLinks = require("./models/animallinks");
require("./database");

async function start () { 

    let buscar = await Links.find({});
    while(buscar.length){
        let lnk = buscar.pop();
        request(lnk, (err, res, body) =>{
            if(!err && res.statusCode == 200){
                let $ = cheerio.load(body);
                $("tbody tr","#semen").each(async function(){
                    let link = $("td a",$(this)).attr("href");
                    if(link){
                        let x = await AnimalLinks.find({url: "https://www.horsetelex.com/"+link});
                        if(!x.length){
                            let nuevo = new AnimalLinks({url: "https://www.horsetelex.com/"+link});
                            nuevo.save();
                        }else{
                            console.log(x.url, 'existe');
                        }
                    }
                });
            }else{
                if(err){
                    console.log(err);
                }
            }
        });
    }
};

start();