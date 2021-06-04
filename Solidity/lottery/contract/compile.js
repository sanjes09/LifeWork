const fs = require('fs');
const path = require('path');
const solc = require('solc');

const LotteryPath = path.resolve(__dirname, "contracts", "Lottery.sol");

const source = fs.readFileSync(LotteryPath, "utf8");

let input = {
  language: 'Solidity',
  sources: {
    'Lottery.sol': {
      content: source
    }
  },
  settings: {
    optimizer: {
        enabled: true
    },
    outputSelection: {
      '*': {
        '*': [ '*' ]
      }
    }
  }
}; 
let x = JSON.parse(solc.compile(JSON.stringify(input)))
if(x.errors){
  console.log('x.errors', x.errors)
}else{
  x = JSON.parse(solc.compile(JSON.stringify(input))).contracts['Lottery.sol'].Lottery;
}
const interface = x.abi;
const bytecode = x.evm?.bytecode?.object;
module.exports = {interface,bytecode};