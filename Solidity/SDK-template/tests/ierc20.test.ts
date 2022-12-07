import { BigNumber, ethers } from 'ethers'
import SDK from '../src/index'
import {expect} from 'chai';

describe('IERC20 Test', () => {

  it('should get data from the contract', async() => {
    // Given
    const provider = new ethers.providers.InfuraProvider(4, process.env.INFURA_API_KEY)
    const signer = new ethers.Wallet(process.env.PRIVATE_KEY ?? '', provider);
    const sdk = await SDK.create(provider, signer)

    // When
    const decimals = await sdk.modules.ierc20.decimals();
    
    // Then
    expect(decimals).to.be.not.equal(BigNumber.from("0"))

  }, 30000)

})
