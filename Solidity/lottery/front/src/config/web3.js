import Web3 from 'web3';
import HDWalletProvider from "@truffle/hdwallet-provider";

const provider = new HDWalletProvider(
  'pudding orphan screen torch lava manual enrich discover capital twenty file inspire',
  'https://rinkeby.infura.io/v3/203d5c0b362148819014f26057fb0d90'
)
const web3 = new Web3(provider);

export default web3;