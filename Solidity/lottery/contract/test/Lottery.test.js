const assert = require('assert');
const ganache = require('ganache-cli');
const Web3 = require('web3');

const provider = ganache.provider();
const web3 = new Web3(provider);
const { interface, bytecode } = require('../compile');

let accounts;
let lottery;

beforeEach(async () => {
  accounts = await web3.eth.getAccounts()
  lottery = await new web3.eth.Contract(interface)
  .deploy({data: bytecode})
  .send({from: accounts[0], gas: '1000000'})

  lottery.setProvider(provider);
})
 
describe("lottery", () => {
  it("deploys a contract", () => {
    assert.ok(lottery.options.address);
  }) 

  it("entered players", async () => {
    await lottery.methods.enter().send({from: accounts[1], value: web3.utils.toWei("0.01", "ether")});
    await lottery.methods.enter().send({from: accounts[2], value: web3.utils.toWei("0.02", "ether")});
    await lottery.methods.enter().send({from: accounts[3], value: web3.utils.toWei("0.2", "ether")});
    const players = await lottery.methods.getPlayers().call({from: accounts[0]});
    assert.strictEqual(accounts[1], players[0])
    assert.strictEqual(accounts[2], players[1])
    assert.strictEqual(accounts[3], players[2])
    assert.strictEqual(3, players.length)
  })

  it('reject player', async () => {
    try {
      await lottery.methods.enter().send({from: accounts[4], value: web3.utils.toWei("0.001", "ether")});
      assert(false);
    } catch (error) {
      assert(error);
    }
  })

  it('reject pick winner', async () => {
    try {
      await lottery.methods.enter().send({from: accounts[1]});
      assert(false);
    } catch (error) {
      assert(error);
    }
  })

  it("picked winner", async () => {
    await lottery.methods.enter().send({from: accounts[1], value: web3.utils.toWei("0.01", "ether")});
    await lottery.methods.enter().send({from: accounts[2], value: web3.utils.toWei("0.02", "ether")});
    await lottery.methods.enter().send({from: accounts[3], value: web3.utils.toWei("0.2", "ether")});
    let valor = await web3.eth.getBalance(lottery.options.address);
    console.log('contract value ', web3.utils.fromWei(`${valor}`, "ether"));

    await lottery.methods.pickWinner().send({from: accounts[0]});

    valor = await web3.eth.getBalance(lottery.options.address);
    console.log('contract value ', web3.utils.fromWei(`${valor}`, "ether"));
    assert(true);
  })

})