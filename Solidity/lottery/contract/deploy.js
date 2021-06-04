const HDWalletProvider = require("@truffle/hdwallet-provider");
const Web3 = require('web3');
const { interface, bytecode } = require('./compile');
const provider = new HDWalletProvider(
  'pudding orphan screen torch lava manual enrich discover capital twenty file inspire',
  'https://rinkeby.infura.io/v3/203d5c0b362148819014f26057fb0d90'
)
const web3 = new Web3(provider);

const deploy = async () => {
  const accounts = await web3.eth.getAccounts();
  console.log('Attempting to deploy from account', accounts[0]);

  const result = await new web3.eth.Contract(interface)
  .deploy({data: bytecode})
  .send({from: accounts[0], gas: '1000000'})

  console.log('contract deployed to', result.options.address);
  console.log('ABI', interface);
}

deploy();