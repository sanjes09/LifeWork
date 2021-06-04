const { ethers, upgrades } = require("hardhat");

const Previous_Address = "0x7a250d5630B4cF539739dF2C5dAcb4c659F2488D";

async function main() {
  const MarketplaceV2 = await ethers.getContractFactory("MarketplaceV2");
  const marketplaceV2 = await upgrades.upgradeProxy(Previous_Address, MarketplaceV2);
  console.log("marketplace upgraded");
}

main();