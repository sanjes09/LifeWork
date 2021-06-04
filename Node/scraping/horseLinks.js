const cheerio = require("cheerio");
const request = require("request");
const Links = require("./models/links");
require("./database");

request("https://www.horsetelex.com/horses/semen/", (err, res, body) =>{
    if(!err && res.statusCode == 200){
        let $ = cheerio.load(body);
        $("a",".alphabet").each(async function(){
            let x = await Links.find({url: "https://www.horsetelex.com/"+$(this).attr("href")});
            if(!x.length){
                let nuevo = new Links({url: "https://www.horsetelex.com/"+$(this).attr("href")});
                nuevo.save();
            }else{
                console.log('existe');
            }
        });
        console.log("links creados");
    }else{
        if(err){
            console.log(err);
        }
    }
});