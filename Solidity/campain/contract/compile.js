const fs = require('fs-extra');
const path = require('path');
const solc = require('solc');

const buildFolder = path.resolve(__dirname, "build");
fs.removeSync(buildFolder);
fs.ensureDirSync(buildFolder);

const CampaignPath = path.resolve(__dirname, "contracts", "Campaign.sol");

const source = fs.readFileSync(CampaignPath, "utf8");

let input = {
  language: 'Solidity',
  sources: {
    'Campaign.sol': {
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
let output = JSON.parse(solc.compile(JSON.stringify(input)))

if(output.errors){
  console.log('output.errors', output.errors)
}else{
  output = output.contracts['Campaign.sol'];
  for (const contract in output) {
    fs.outputJsonSync(path.resolve(buildFolder, contract + ".json"),output[contract]);
  }
}