const fs = require("fs");
let crypto = "";
let coin = "";
let Existe = [];
let NoExiste = [];

fs.open('./cryptotaxmate-my-transacctions.csv', 'r', function(err, fileToRead){
    if (!err){
        fs.readFile(fileToRead, {encoding: 'utf-8'}, function(err,data){
            if (!err){
                crypto=data;
                fs.open('./dincer.csv', 'r', function(err, fileToRead){
                    if (!err){
                        fs.readFile(fileToRead, {encoding: 'utf-8'}, function(err,data){
                            if (!err){
                                coin=data;
                                crypto = crypto.split("\n");
                                coin = coin.split("\n");
                                let existe = 0;
                                coin.forEach(elementCoin => {
                                    existe = 0;
                                    // console.log(elementCoin);
                                    elementCoin = elementCoin.split(",");
                                    let compraCoin = parseFloat(elementCoin[1]);
                                    let ventaCoin = parseFloat(elementCoin[3]);
                                    let comisionCoin = parseFloat(elementCoin[5]);
                                    let compraCoinFee = parseFloat(elementCoin[7]);
                                    let ventaCoinFee = parseFloat(elementCoin[8]);
                                    console.log(elementCoin[0]+" vendido: "+ventaCoin+" comprado: "+compraCoin+" comisionCoin: "+comisionCoin);
                                    
                                    let index = 0;
                                    crypto.forEach(elementCrypto => {
                                        if(existe===0){
                                            elementCrypto = elementCrypto.split(",");
                                            let compraCrypto = elementCrypto[10].substring(1,elementCrypto[10].length-1)
                                            compraCrypto = parseFloat(compraCrypto); 
                                            let ventaCrypto = elementCrypto[8].substring(1,elementCrypto[8].length-1)
                                            ventaCrypto = parseFloat(ventaCrypto); 
                                            let comisionCrypto = elementCrypto[22].substring(1,elementCrypto[22].length-1)
                                            comisionCrypto = parseFloat(comisionCrypto);
                                            if((comisionCrypto === comisionCoin) && ((compraCrypto === compraCoin)||(compraCrypto === ventaCoin)||(compraCrypto === compraCoinFee)||(compraCrypto === ventaCoinFee)||(ventaCrypto === compraCoin)||(ventaCrypto === ventaCoin)||(ventaCrypto === compraCoinFee)||(ventaCrypto === ventaCoinFee))){
                                                existe = 1;
                                                crypto.splice(index,1);
                                                Existe.push(elementCoin);
                                                // console.log("compara coin:"+elementCoin);
                                                // console.log("con crypto:"+elementCrypto);
                                                // console.log("existe");
                                            }
                                            // console.log("vendido: "+venta2+" comprado: "+compraCrypto+" comisionCoin: "+comisionCrypto);
                                            index++;
                                        }
                                    });
                                    if(existe === 0){
                                        NoExiste.push(elementCoin);
                                    }
                                });
                                fs.writeFile('NoExiste.txt', NoExiste, err => {
                                    if (err) {
                                      console.error(err)
                                      return
                                    }
                                    //file written successfully
                                })
                                fs.writeFile('Existe.txt', Existe, err => {
                                    if (err) {
                                      console.error(err)
                                      return
                                    }
                                    //file written successfully
                                })
                                // NoExiste.forEach(element => {
                                //     console.log(element);
                                // });
                            }else{
                                console.log(err);
                            }
                        });
                    }else{
                        console.log(err);
                    }
                });
            }else{
                console.log(err);
            }
        });
    }else{
        console.log(err);
    }
});




