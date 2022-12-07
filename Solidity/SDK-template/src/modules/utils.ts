import { ethers, Signer, BigNumber } from 'ethers'
import { ContractNames, SDKProvider } from '../types'
import { getContractInfo } from '../contracts/contractAddresses';
import {TextDecoder,TextEncoder} from "web-encoding"
global.TextDecoder = TextDecoder;
global.TextEncoder = TextEncoder;
import ipfsClient from "ipfs-http-client";

const ipfs:any = ipfsClient({
  host: "ipfs.infura.io",
  port: 5001,
  protocol: "https",
});

class Utils {
  provider: SDKProvider
  signer: Signer | undefined
  networkId: Number

  constructor(provider: SDKProvider, networkId = 4, signer?: Signer) {
    this.provider = provider
    this.signer = signer
    this.networkId = networkId
  }

  static async create(provider: SDKProvider, signer?: Signer): Promise<Utils> {
    const networkId = (await provider.getNetwork()).chainId
    return new Utils(provider, networkId, signer)
  }

  addToIpfs = async (content: string):Promise<string> => {
    const { cid } = await ipfs.add(content);
    return cid.toString();
  }

  checkAllowance = async (from: string, to: string, amount: BigNumber, address: string): Promise<boolean> => {
    if(this.signer){
      const { abi } = getContractInfo(+this.networkId, ContractNames.IERC20);
      const contract = new ethers.Contract(address, abi, this.provider).connect(this.signer);
    
      const allowance = await contract.allowance(from, to);
      
      let valid = true;
      if(Number(allowance) < Number(amount)){
        try {
          const infinite = BigNumber.from("2").pow(BigNumber.from("256")).sub(BigNumber.from("1"));
          const transactionObject = await contract.approve(to, infinite);
          await this.provider.waitForTransaction(transactionObject.hash)
        } catch (error) {
          valid = false;
        }
      }
      return valid;
    }
    return false;
  }

  fromWei = async (amount: BigNumber, address: string): Promise<string> => {
    const networkId = (await this.provider.getNetwork()).chainId;
  
    const { abi } = getContractInfo(networkId, ContractNames.IERC20);
    const contract = new ethers.Contract(address, abi, this.provider);
    const decimals = await contract.decimals();
    
    return String(amount.div(BigNumber.from(String(10**parseInt(decimals)))))
  }

  toWei = async (amount: BigNumber, address: string): Promise<string> => {
    const networkId = (await this.provider.getNetwork()).chainId;
    const { abi } = getContractInfo(networkId, ContractNames.IERC20);
    const contract = new ethers.Contract(address, abi, this.provider);
    const decimals = await contract.decimals();
  
    return String(amount.mul(BigNumber.from(String(10**parseInt(decimals)))))
  }
  
}

export default Utils