import {ethers} from 'ethers'

import IERC20 from '../modules/ierc20'
import Utils from '../modules/utils'

export interface PackageModules {
  ierc20: IERC20 | null
  utils: Utils | null
}

export type SDKProvider =
  | ethers.providers.Web3Provider
  | ethers.providers.InfuraProvider
  | ethers.providers.JsonRpcProvider

export interface Transaction {
  hash: string
  getReceipt(): Promise<ethers.providers.TransactionReceipt>
}

export type NetworkId = 1 | 4 | 80001 | 43113 | 97 | 1287

export enum NetworkIds {
  MAINNET = 1,
  RINKEBY = 4,
  MATIC_TESTNET = 80001,
  AVALANCHE_TESTNET = 43113,
  BINANCE_TESTNET = 97,
  MOONBASE_ALPHA = 1287
}

export type ContractName = 'IERC20'

export enum ContractNames {
  IERC20 = 'IERC20',
}

export type Maybe<T> = T | null