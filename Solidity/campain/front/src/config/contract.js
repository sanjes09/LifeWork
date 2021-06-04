import web3 from './web3';
import Factory from './Factory.json';

export default new web3.eth.Contract(Factory.abi, "0x01e15bacf72e6Dca1652185e5f49C525AADd3D91");