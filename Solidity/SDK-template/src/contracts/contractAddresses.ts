import { ContractInterface } from 'ethers'

import { ContractName, ContractNames, Maybe, NetworkIds } from '../types'
import IERC20Abi from './abis/IERC20.json'

type ContractAddresses = { [key in ContractNames]: Maybe<string> }

const contractAddresses: { [K in NetworkIds]: ContractAddresses } = {
  [NetworkIds.MAINNET]: {
    IERC20: '0x0000000000000000000000000000000000000000'
  },
  [NetworkIds.RINKEBY]: {
    IERC20: '0x0000000000000000000000000000000000000000'
  },
  [NetworkIds.AVALANCHE_TESTNET]: {
    IERC20: '0x0000000000000000000000000000000000000000'
  },
  [NetworkIds.MATIC_TESTNET]: {
    IERC20: '0x0000000000000000000000000000000000000000'
  },
  [NetworkIds.BINANCE_TESTNET]: {
    IERC20: '0x0000000000000000000000000000000000000000'
  },
  [NetworkIds.MOONBASE_ALPHA]: {
    IERC20: '0x0000000000000000000000000000000000000000'
  },
}

/**
 * Check of the networkId exist
 * @param {NetworkIds} networkId
 * @returns {networkId is NetworkIds}
 */
export const isKnownNetwork = (networkId: NetworkIds): networkId is NetworkIds => {
  return (
    networkId === 1 ||
    networkId === 4 ||
    networkId === 80001 ||
    networkId === 43113 ||
    networkId === 97 ||
    networkId === 1287
  )
}

/**
 * Get Contract Address by networkId and contract name
 * @param {NetworkIds} networkId
 * @param {ContractName} contractName
 * @returns {string}
 */
export const getContractAddress = (networkId: NetworkIds, contractName: ContractName): string => {
  if (!contractAddresses[networkId][contractName]) {
    throw new Error(`Contract address for network ${networkId} not set`)
  }

  return contractAddresses[networkId][contractName] ?? ''
}

/**
 * Get contract ABI by contract name
 * @param {ContractName} contractName
 * @returns {ContractInterface}
 */
export const getContractABI = (contractName: ContractName): ContractInterface => {
  let abi
  switch (contractName) {
    case ContractNames.IERC20:
      abi = IERC20Abi.abi
      break
    default:
      throw new Error(`Contract abi for contract ${contractName} not set`)
  }

  return abi
}

/**
 * Get contract information , the ABI of the contract and the address
 * @param {NetworkIds} networkId
 * @param {ContractName} contractName
 * @returns {{address: string; abi: ContractInterface}}
 */
export const getContractInfo = (
  networkId: NetworkIds,
  contractName: ContractName
): { address: string; abi: ContractInterface } => {
  const address = getContractAddress(networkId, contractName)
  const abi = getContractABI(contractName)

  return {
    address,
    abi
  }
}