import { ethers } from 'ethers'
import SDK from '../src/index'
import {expect} from 'chai';

describe('Utils functions', () => {

  it('should get hash from IPFS', async() => {
    // Given
    const provider = new ethers.providers.InfuraProvider(4, process.env.INFURA_API_KEY)
    const signer = new ethers.Wallet(process.env.PRIVATE_KEY ?? '', provider);
    const sdk = await SDK.create(provider, signer)

    // When
    const hash = await sdk.modules.utils.addToIpfs("some data");

    expect(hash.startsWith("Qm")).to.be.true;

  }, 300000)

})
