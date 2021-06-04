const HDWalletProvider = require("@truffle/hdwallet-provider");
const Web3 = require('web3');
const Campaign = require('./build/Campaign.json');
const Factory = require('./build/Factory.json');
const provider = new HDWalletProvider(
  'pudding orphan screen torch lava manual enrich discover capital twenty file inspire',
  'https://rinkeby.infura.io/v3/203d5c0b362148819014f26057fb0d90'
)
const web3 = new Web3(provider);

const deploy = async () => {
  const accounts = await web3.eth.getAccounts();
  console.log('Attempting to deploy from account', accounts[0]);

  const result = await new web3.eth.Contract(Factory.abi)
  .deploy({data: Factory.evm?.bytecode?.object})
  .send({from: accounts[0], gas: '1000000'})

  console.log('contract deployed to', result.options.address);
  //0xAA88A1228f07e9A0132a0E68BA57040c068b83be
}

deploy();