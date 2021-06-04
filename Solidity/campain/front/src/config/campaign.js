import web3 from './web3';
import Campaign from './Campaign.json';

const contrato = (address) => {
  return new web3.eth.Contract(Campaign.abi, address);
}

export default contrato;