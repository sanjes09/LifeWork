//SPDX-License-Identifier: MIT
pragma solidity ^0.7.0;

import '@openzeppelin/contracts/presets/ERC20PresetMinterPauser.sol';

contract MyTestToken20 is ERC20PresetMinterPauser{

    constructor() ERC20PresetMinterPauser("MyTestToken20", "MT1"){}

}