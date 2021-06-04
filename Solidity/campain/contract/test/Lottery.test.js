const assert = require('assert');
const ganache = require('ganache-cli');
const Web3 = require('web3');

const provider = ganache.provider();
const web3 = new Web3(provider);
const Campaign = require('../build/Campaign.json');
const Factory = require('../build/Factory.json');

// const interface = output.abi;
// const bytecode = output.evm?.bytecode?.object;

let accounts;
let factory;
let campaign;
let campaignAddress;

beforeEach(async () => {
  accounts = await web3.eth.getAccounts();
  factory = await new web3.eth.Contract(Factory.abi)
  .deploy({data: Factory.evm?.bytecode?.object})
  .send({from: accounts[0], gas: '1000000'});

  await factory.methods.createCampaign("100").send({
    from: accounts[0],
    gas: '1000000'
  });

  const addresses = await factory.methods.getDeployedCampaigns().call();
  campaignAddress = addresses[0];
  campaign = await new web3.eth.Contract(Campaign.abi,campaignAddress);
})
 
describe("factory", () => {
  
  it("deploys factory and campaign", () => {
    assert.ok(factory.options.address);
    assert.ok(campaign.options.address);
  }) 

  it("is the managet", async () => {
    const manager = await campaign.methods.manager().call();
    assert.ok(manager === accounts[0])
  })

  it('can contribute', async () => {
    await campaign.methods.contribute().send({
      from: accounts[1],
      value: '100'
    })
    const isContributor = await campaign.methods.approvers(accounts[1]).call();
    assert.ok(isContributor);
  })

  it('is not contributor', async () => {
    const isContributor = await campaign.methods.approvers(accounts[2]).call();
    assert.ok(!isContributor);
  })

  it("require minumin contribution", async () => {
    try {
      await campaign.methods.contribute().send({
        from: accounts[1],
        value: '20'
      })
      assert(false);
    } catch (error) {
      assert(error);
    }
  })

  it("allows a make paymanet request", async () => {
    await campaign.methods.createRequest("pago","100",accounts[5]).send({
      from: accounts[0],
      gas: '1000000'
    });
    const request = await campaign.methods.requests(0).call();
    assert.strictEqual("pago", request.description)
  })

  it("processes a request", async () => {
    await campaign.methods.contribute().send({
      from: accounts[1],
      value: web3.utils.toWei("1", "ether")
    })
    await campaign.methods.contribute().send({
      from: accounts[2],
      value: web3.utils.toWei("2", "ether")
    })
    await campaign.methods.contribute().send({
      from: accounts[3],
      value: web3.utils.toWei("3", "ether")
    })

    await campaign.methods.createRequest("pagar", web3.utils.toWei("1", "ether"), accounts[4]).send({
      from: accounts[0],
      gas: '1000000'
    })

    await campaign.methods.approveRequest(0).send({
      from: accounts[1],
      gas: '1000000'
    })
    await campaign.methods.approveRequest(0).send({
      from: accounts[2],
      gas: '1000000'
    })

    await campaign.methods.finalizeRequest(0).send({
      from: accounts[0],
      gas: '1000000'
    })

    const response = await web3.eth.getBalance(accounts[4]);
    let balance = web3.utils.fromWei(response, "ether");
    balance = parseFloat(balance);
    assert(balance >  100);

  })

})