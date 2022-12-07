import { Contract, ethers, Signer, BigNumber } from 'ethers'
import { ContractNames, SDKProvider, Transaction } from '../types'
import { getContractInfo } from '../contracts/contractAddresses'

class IERC20 {
  provider: SDKProvider
  signer: Signer | undefined
  contract: Contract

  constructor(provider: SDKProvider, networkId = 4, signer?: Signer) {
    this.provider = provider
    this.signer = signer

    const { abi, address } = getContractInfo(networkId, ContractNames.IERC20)

    if (!abi || !address) {
      throw new Error('Network not supported')
    }

    if (signer) {
      this.contract = new ethers.Contract(address, abi, provider).connect(signer)
    } else {
      this.contract = new ethers.Contract(address, abi, provider)
    }
  }

  static async create(provider: SDKProvider, signer?: Signer): Promise<IERC20> {
    const networkId = (await provider.getNetwork()).chainId
    return new IERC20(provider, networkId, signer)
  }

  get address(): string {
    return this.contract.address
  }

  get getContract(): Contract {
    return this.contract
  }

  // STORAGE
  name = async (): Promise<string> => {
    return this.contract.name()
  }

  symbol = async (): Promise<string> => {
    return this.contract.symbol()
  }
  
  decimals = async (): Promise<number> => {
    return this.contract.decimals()
  }
  
  totalSupply = async (): Promise<number> => {
    return this.contract.decimals()
  }

  // FUNCTIONS
  balanceOf = async (address: string): Promise<number> => {
    return this.contract.balanceOf(address)
  }

  allowance = async (owner: string, spender: string): Promise<number> => {
    return this.contract.allowance(owner, spender)
  }

  increaseAllowance = async (spender: string, amount: BigNumber): Promise<number> => {
    return this.contract.increaseAllowance(spender, amount)
  }

  decreaseAllowance = async (spender: string, amount: BigNumber): Promise<number> => {
    return this.contract.decreaseAllowance(spender, amount)
  }

  approve = async (address: string, amount: BigNumber): Promise<boolean> => {
    return this.contract.approve(address, amount)
  }

  transfer = async (address: string, amount: BigNumber): Promise<boolean> => {
    return this.contract.transfer(address, amount)
  }

  transferFrom = async (address: string, recipient: string, amount: BigNumber): Promise<boolean> => {
    return this.contract.transferFrom(address, recipient, amount)
  }

  mint = async (address: string, amount: BigNumber): Promise<Transaction> => {
    const transactionObject = await this.contract.mint(address, amount)
    return {
      hash: transactionObject.hash,
      getReceipt: async () => this.provider.waitForTransaction(transactionObject.hash)
    }
  }

  pause = async (): Promise<Transaction> => {
    const transactionObject = await this.contract.pause()
    return {
      hash: transactionObject.hash,
      getReceipt: async () => this.provider.waitForTransaction(transactionObject.hash)
    }
  }

  unpause = async (): Promise<Transaction> => {
    const transactionObject = await this.contract.unpause()
    return {
      hash: transactionObject.hash,
      getReceipt: async () => this.provider.waitForTransaction(transactionObject.hash)
    }
  }


}

export default IERC20