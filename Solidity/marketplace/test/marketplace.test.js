const {BN, expectEvent, expectRevert} = require('@openzeppelin/test-helpers');
const { ethers, upgrades } = require("hardhat");

const IERC20 = artifacts.require("IERC20");

const aggregator = "0x5f4eC3Df9cbd43714FE2740f5E3616155c5b8419";
const Uniswap = "0x7a250d5630B4cF539739dF2C5dAcb4c659F2488D";
// const WETH = "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2";
const DAI = "0x6B175474E89094C44Da98b954EedeAC495271d0F";

describe("Marketplace", () => {
  let token20;
  let token721;
  let market;
  let marketV2;
  let admin;
  let alice;
  let bob;
  let random;
  
  before(async () => {
    [admin, alice, bob, random] = await ethers.getSigners();
    const Marketplace = await ethers.getContractFactory("Marketplace");
    const MyTestToken20 = await ethers.getContractFactory("MyTestToken20");
    const MyTestToken721 = await ethers.getContractFactory("MyTestToken721");
  
    token20 = await MyTestToken20.deploy();
    await token20.deployed();
    token721 = await MyTestToken721.deploy();
    await token721.deployed();
  
    market = await upgrades.deployProxy(Marketplace, [token20.address, token721.address, aggregator, Uniswap]);
    await market.deployed();
  });

  describe("ERC20", () => {
    it("Should increase amount", async () => {
      await token20.mint(alice.address, web3.utils.toWei(String(10)));
      assert.equal(await token20.balanceOf(alice.address), web3.utils.toWei(String(10)));
    })
  
    it("Should not increase amount if not owner", async () => {
      await expectRevert(
        token20.connect(alice).mint(alice.address, web3.utils.toWei(String(10))),
        "ERC20PresetMinterPauser: must have minter role to mint"
      )
    })
  })

  describe("ERC721", () => {
    it("Should create an item", async () => {
      await token721.createItem(alice.address, 10);
      assert.equal(await token721.ownerOf(10),alice.address);
    })
  
    it("Should fail to create the same item", async () => {
      await expectRevert(
        token721.createItem(alice.address, 10),
        "Item already exists"
      )
    })
  
    it("Should post an item in USD", async () => {
      await token721.connect(alice).approve(market.address, 10);
      const receipt = await market.connect(alice).sellItem(10, 20000);
      let x = await await market.connect(alice).allItems(10);
      assert.ok(x.seller === alice.address);
      // expectEvent(receipt, 'SellItem', {
      //   seller: alice.address,
      //   price: new BN(20000),
      //   id: new BN(10)
      // });
    })
  
    it("Should not post an item without approval", async () => {
      await token721.createItem(alice.address, 20);
      await expectRevert(
        market.connect(alice).sellItem(20, 20000),
        "Not allowed to sell"
      )
    })
  
    it("Should not post an other persons item", async () => {
      await token721.createItem(alice.address, 30);
      await expectRevert(
        market.connect(bob).sellItem(30, 20000),
        "This is not your item"
      )
    })
  })

  describe("Buy", () => {

    it("Should not buy because it is not implemented", async () => {
      // await expectRevert(
      //   market.connect(bob).buyWithEth(10, {value: web3.utils.toWei(String(20))}),
      //   "TypeError: market.connect(...).buyWithEth is not a function"
      // )
      try {
        await market.connect(bob).buyWithEth(10, {value: web3.utils.toWei(String(20))});
      } catch (error) {
        assert.ok(error.message === "market.connect(...).buyWithEth is not a function")
      }
    })

    it("Should upgrade the contract", async () => {
      const MarketplaceV2 = await ethers.getContractFactory("MarketplaceV2");
      marketV2 = await upgrades.upgradeProxy(market.address, MarketplaceV2);
      let x = await await marketV2.connect(alice).allItems(10);
      assert.ok(x.seller === alice.address);
    })

    it("Should buy an item with ETH", async () => {
      const initialBuyer = await web3.eth.getBalance(bob.address);
      const initialSeller = await web3.eth.getBalance(alice.address);
      const receipt = await marketV2.connect(bob).buyWithEth(10, {value: web3.utils.toWei(String(20))});
      assert.ok(parseInt(initialBuyer) >= parseInt(await web3.eth.getBalance(bob.address)) && parseInt(initialSeller) <= parseInt(await web3.eth.getBalance(alice.address)));
      // expectEvent(receipt, 'BuyItem', {
      //   buyer: bob,
      //   seller: alice,
      //   id: new BN(10),
      //   price: new BN(20000)
      // });
    })

    it("Should not buy an item with ETH already yours", async () => {
      await expectRevert(
        marketV2.connect(bob).buyWithEth(10, {value: web3.utils.toWei(String(20))}),
        "This is your own item"
      )
    })
  
    it("Should not buy an item with ETH already sold", async () => {
      await expectRevert(
        marketV2.connect(random).buyWithEth(10, {value: web3.utils.toWei(String(20))}),
        "Item already sold"
      )
    })

    it("Should buy an item with ETH and pay with DAI", async () => {
      await token721.connect(alice).approve(marketV2.address, 20);
      await marketV2.connect(alice).sellItem(20, 20000);
      const daiToken = await IERC20.at(DAI);

      const initialBuyer = await web3.eth.getBalance(bob.address);
      const initialSellerDAI = new BN(await daiToken.balanceOf(alice.address));

      await marketV2.connect(bob).buyWithEthToDai(20, DAI, {value: web3.utils.toWei(String(20))});

      const fianlBuyer = await web3.eth.getBalance(bob.address);
      const finalSellerDAI = new BN(await daiToken.balanceOf(alice.address));

      assert.ok(parseInt(initialBuyer) >= parseInt(fianlBuyer) && parseInt(initialSellerDAI) <= parseInt(finalSellerDAI));
      // expectEvent(receipt, 'BuyItem', {
      //   buyer: bob,
      //   seller: alice,
      //   id: new BN(10),
      //   price: new BN(20000)
      // });
    })

    it("Should not buy an item with ETH and pay with DAI already yours", async () => {
      await expectRevert(
        marketV2.connect(bob).buyWithEthToDai(20, DAI, {value: web3.utils.toWei(String(20))}),
        "This is your own item"
      )
    })
  
    it("Should not buy an item with ETH and pay with DAI already sold", async () => {
      await expectRevert(
        marketV2.connect(random).buyWithEthToDai(20, DAI, {value: web3.utils.toWei(String(20))}),
        "Item already sold"
      )
    })

  })

});