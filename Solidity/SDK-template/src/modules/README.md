# Modules

1. IERC20 token (ierc20)
    * `name()`
    * `symbol()`
    * `decimals()`
    * `totalSupply()`
    * `balanceOf(address: string)`
    * `allowance(owner: string, spender: string)`
    * `increaseAllowance(spender: string, amount: BigNumber)`
    * `decreaseAllowance(spender: string, amount: BigNumber)`
    * `approve(address: string, amount: BigNumber)`
    * `transfer(address: string, amount: BigNumber)`
    * `transferFrom(address: string, recipient: string, amount: BigNumber)`
    * `mint(address: string, amount: BigNumber)`
    * `pause()`
    * `unpause()`

2. Utilities (utils)
    * `addToIpfs(data: string)`
    * `checkAllowance(from: string, to: string, amount: BigNumber, address: string)`
    * `fromWei(amount: BigNumber, address: string)`
    * `toWei(amount: BigNumber, address: string)`