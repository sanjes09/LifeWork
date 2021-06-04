const { ethers, upgrades } = require("hardhat");

const aggregator = "0x5f4eC3Df9cbd43714FE2740f5E3616155c5b8419";
const Uniswap = "0x7a250d5630B4cF539739dF2C5dAcb4c659F2488D";

async function main() {
  const MyTestToken20 = await ethers.getContractFactory("MyTestToken20");
  const myTestToken20 = await MyTestToken20.deploy();
  const MyTestToken721 = await ethers.getContractFactory("MyTestToken721");
  const myTestToken721 = await MyTestToken721.deploy();

  const Marketplace = await ethers.getContractFactory("Marketplace");
  const marketplace = await upgrades.deployProxy(Marketplace, [myTestToken20.address, myTestToken721.address, aggregator, Uniswap]);
  await marketplace.deployed();

  console.log("myTestToken20 deployed to:", myTestToken20.address);
  console.log("myTestToken721 deployed to:", myTestToken721.address);
  console.log("marketplace deployed to:", marketplace.address);
}

main();