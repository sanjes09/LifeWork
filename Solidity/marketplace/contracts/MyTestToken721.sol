//SPDX-License-Identifier: MIT
pragma solidity ^0.7.0;

import '@openzeppelin/contracts/token/ERC721/ERC721.sol';
import '@openzeppelin/contracts/access/Ownable.sol';
// import '@openzeppelin/contracts/presets/ERC721PresetMinterPauserAutoId.sol';

contract MyTestToken721 is ERC721, Ownable{

    constructor() ERC721("MyTestToken721", "MT2"){

    }

    function createItem(address to, uint id) public onlyOwner{
        require(!_exists(id), "Item already exists");
        _safeMint(to, id);
    }

}