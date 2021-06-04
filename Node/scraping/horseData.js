const cheerio = require("cheerio");
const request = require("request");
const Link = require("../../models/links");
const Imagen = require("../../models/imagenes");
const Breeder = require("../../models/breeders");
require("../../database");

async function start () { 
    let lks = "";
    let $ = "";
    let links = [];

    lks = await Link.find({});
    lks.forEach(element => {
        links.push(element.url);
    });

    let x = links.length;

    while(links.length){
        let element = links.pop();
        request(element, async (err,res,body) => {
            if(!err && res.statusCode == 200){
                let $ = cheerio.load(body);
                let breeder = new Breeder();
                breeder["name"] = "";
                breeder["address"] = "";
                breeder["phone"] = "";
                breeder["website"] = "";
                breeder["email"] = "";
                breeder["social"] = [];
                breeder["breeders"] = [];
                breeder["news"] = [];

                let name = $("h2.top-flush","#content").text();
                let description = $(".breeder-detail-description p","#breeder-detail-leftcol").text();
                breeder["name"] = name;
                breeder["description"] = description;

                $("#breeder-detail-photos","#breeder-detail-leftcol").each(function (){
                    let url = $("#breeder-detail-photo-main-container #breeder-detail-photo-main",$(this)).attr("src");
                    let local = url.split("/");
                    local = local[local.length-1];
                    breeder["image"]["url"] = "/images/breeder/"+local;
                    let str = $("#breeder-detail-photo-main-caption",$(this)).text();
                    breeder["image"]["caption"] = str;
                    let nueva = new Imagen({url: "http://www.holsteinplaza.com"+url, name: local});
                    nueva.save();
                });
                $(".breeder-detail-fieldgroup","#breeder-detail-rightcol").each(function (){
                    let att = $("h5",$(this)).text();
                    att = att.toLowerCase();
                    att = att.split(" ").join("");
                    att = att.split(".").join("");
                    att = att.split("\n").join("");
                    att = att.split("\t").join("");
                    att = att.split("\\s{2,}").join(" ");
                    if(att == "socialnetworks"){
                        $("a",$(this)).each(function() {
                            breeder["social"].push($(this).attr("href"));
                        });
                    }else{
                        let str = $("p",$(this)).text();
                        str = str.split("\n").join("");
                        str = str.split("\t").join("");
                        str = str.trim().replace(/\s\s+/g,' ');
                        breeder[att] = str;
                    }
                });
                $(".breeder-detail-donors-result","#breeder-detail-donors-results").each(function (){
                    let str = $(".breeder-detail-donors-result-name a",$(this)).text();
                    breeder["donors"].push(str);
                });
                $(".breeder-detail-news-item","#breeder-detail-newscol").each(function (){
                    let str = $("h5 a",$(this)).text();
                    breeder["news"].push(str);
                });
                await breeder.save();
                await Link.findOneAndDelete({url: String(element)});
                x-=1;
                console.log("faltan "+x);
            }else{
                if(err){
                    console.log(err);
                }
            }
        });
    }
}
start();
