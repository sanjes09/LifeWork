// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract Lottery {
  address public manager;
  address[] public players;

  constructor(){
    manager = msg.sender;
  }

  //middlewares
  modifier onlyManager(){
    require(msg.sender == manager);
    _;
  }
  //middlewares

  function enter() public payable {
    require(msg.value >= 0.01 ether);
    players.push(msg.sender);
  }

  function random() private view returns (uint) {
    return uint(keccak256(abi.encodePacked(block.timestamp, msg.sender, players)));
  } 

  function pickWinner() public payable onlyManager{ 
    uint index = random() % players.length;
    payable(players[index]).transfer(address(this).balance);
    players = new address[](0);
  }
   
  function getPlayers() public view returns(address[] memory){
    return players;
  }
}  