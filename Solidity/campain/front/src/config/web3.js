import Web3 from 'web3';
import HDWalletProvider from "@truffle/hdwallet-provider";

let web3;

if(window?.web3){
  web3 = new Web3(window.web3.currentProvider);
}else{
  const provider = new HDWalletProvider(
    'pudding orphan screen torch lava manual enrich discover capital twenty file inspire',
    'https://rinkeby.infura.io/v3/203d5c0b362148819014f26057fb0d90'
  )
  web3 = new Web3(provider);
}

export default web3;