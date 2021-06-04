// SPDX-License-Identifier: MIT
pragma solidity >0.7.6;

contract Factory {
    Campaign[] deployedCampaigns;
    
    function createCampaign(uint minimun) public {
      Campaign campaign = new Campaign(minimun, msg.sender);
      deployedCampaigns.push(campaign);
    }
    
    function getDeployedCampaigns() public view returns (Campaign[] memory) {
      return deployedCampaigns;
    }
}

contract Campaign {
  struct Request{
    string description;
    uint value;
    address recipient;
    bool complete;
    uint approvedCount;
  }
  mapping (uint => mapping (address => bool)) votes;

  address public manager;
  uint public minimunContribution;
  mapping (address => bool) public approvers;
  uint public approversCount;
  Request[] public requests;

  constructor(uint minimum, address creator){
    manager = creator;
    minimunContribution = minimum;
  }

  //middlewares 
  modifier onlyManager(){
    require(msg.sender == manager);
    _;
  }
  modifier isApprover(){
    require(approvers[msg.sender]);
    _;
  }
  //middlewares

  function contribute() public payable{
    require(msg.value >= minimunContribution);
    approvers[msg.sender] = true;
    approversCount++;
  }

  function getBasicData() public view returns(uint, uint, uint, uint, address){
    return (
      minimunContribution,
      address(this).balance,
      requests.length,
      approversCount,
      manager
    );
  }

  function getRequests() public view returns(Request[] memory) {
    return requests;
  }

  function createRequest(string memory description, uint value, address recipient) public onlyManager{
    Request memory x = Request({
      description: description,
      value: value,
      recipient: recipient,
      complete: false,
      approvedCount: 0
    });
    requests.push(x);
  }

  function approveRequest(uint index) public isApprover{
    require(!votes[index][msg.sender]); 
    votes[index][msg.sender] = true;
    requests[index].approvedCount++;
  }

  function finalizeRequest(uint index) public onlyManager{
    Request storage request = requests[index];
    require(!request.complete);
    require(request.approvedCount > approversCount/2);

    payable(request.recipient).transfer(request.value);
    request.complete = true;
  }

}  