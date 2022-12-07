/* eslint-disable-next-line  @typescript-eslint/no-var-requires */
require('dotenv').config()

import { ethers, Signer } from 'ethers'

import IERC20 from './modules/ierc20'
import Utils from './modules/utils'
import { SDKProvider, PackageModules } from './types'

class SDK {
  _provider: SDKProvider
  /* eslint-disable-next-line  @typescript-eslint/no-explicit-any */
  _ethers: any
  _signer: Signer | undefined
  modules: PackageModules

  constructor(provider: SDKProvider, modules: PackageModules, signer?: Signer) {
    this._provider = provider;
    this._ethers = ethers;
    this._signer = signer;
    this.modules = modules;
  }

  static async create(provider: SDKProvider, signer?: Signer): Promise<SDK> {
    const ierc20 = await IERC20.create(provider, signer);
    const utils = await Utils.create(provider, signer);

    return new SDK(provider, { ierc20, utils }, signer)
  }
}

export default SDK
