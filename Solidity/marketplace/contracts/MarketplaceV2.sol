//SPDX-License-Identifier: MIT
pragma solidity ^0.7.0;

import '@openzeppelin/contracts/token/ERC20/IERC20.sol';
import '@openzeppelin/contracts/token/ERC721/IERC721.sol';
import '@openzeppelin/contracts/token/ERC20/SafeERC20.sol';
import '@openzeppelin/contracts/math/SafeMath.sol';
import '@openzeppelin/contracts-upgradeable/access/OwnableUpgradeable.sol';
import "@openzeppelin/contracts-upgradeable/proxy/Initializable.sol";
import '@chainlink/contracts/src/v0.7/interfaces/AggregatorV3Interface.sol';
import '@uniswap/v2-periphery/contracts/interfaces/IUniswapV2Router02.sol';

import "hardhat/console.sol";

contract MarketplaceV2 is Initializable, OwnableUpgradeable{

    using SafeMath for uint256;
    using SafeERC20 for IERC20;

    AggregatorV3Interface internal priceFeed; 
    IUniswapV2Router02 internal uniswapRouter; 

    //EVENTS
    event SellItem(address seller, uint price, uint id);
    event BuyItem(address buyer, address seller, uint id, uint price);

    struct Item {
        address seller;
        uint price;
        uint id;
        bool available;
    }

    IERC20 token;
    IERC721 itemsToken;

    mapping (uint => Item) public allItems;
    uint itemsCount;  

    function initialize(IERC20 _token, IERC721 _itemsToken, address ethPrice, address uniswap) public initializer{
        token = _token;
        itemsToken = _itemsToken;
        priceFeed = AggregatorV3Interface(ethPrice);
        uniswapRouter = IUniswapV2Router02(uniswap);
        itemsCount = 0;
    }

    /**
        @dev Get current WEI per USD value
    */
    function getLatestPrice() public view returns (uint) {
        (
            /* uint80 roundId */,
            int256 answer,
            /* uint256 startedAt */,
            /* uint256 updatedAt */,
            /* uint80 answeredInRound */
        ) = priceFeed.latestRoundData();
        // return answer;
        int weiUsd = 10**26/answer;
        return uint(weiUsd);
    }

    /**
        @notice The user must have previously approved the marketplace to use his ERC721 token
        @dev User creates item in the marketplace to sell it
    */
    function sellItem(uint id, uint price) public {
        // user can not buy his own item
        require(itemsToken.ownerOf(id) == msg.sender, "This is not your item");

        // the marketplace should have been approved to sell the item
        require(itemsToken.getApproved(id) == address(this), "Not allowed to sell");

        Item memory newOne = Item({seller: msg.sender, price: price, id: id, available: true});
        allItems[id] = newOne;

        emit SellItem(msg.sender, price, id);
    }

    /**
        @notice The user must send 5% extra eth just in case
        @dev User can pay the item with eth
    */
    function buyWithEth(uint id) public payable{
        require(itemsToken.ownerOf(id) != msg.sender, "This is your own item");
        require(msg.value >= 0, "Not enough balance");

        Item storage item = allItems[id];
        require(item.available, "Item already sold");

        uint weiPerUsd = getLatestPrice();
        // verify 
        require(msg.value >= item.price * weiPerUsd);
        item.available = false;

        // return the remaining wei
        uint resto = msg.value - (item.price * weiPerUsd);
        require(resto > 0);
        payable(item.seller).transfer(item.price * weiPerUsd);
        payable(msg.sender).transfer(resto);
        
        // execute ERC721 transfer
        itemsToken.safeTransferFrom(itemsToken.ownerOf(id), msg.sender, id);

        emit BuyItem(msg.sender, item.seller, id, item.price);

        item.price = 0;
        // new event
    }

    /**
        @notice The user must send 5% extra eth just in case
        @dev User can pay the item with eth and seller recieves DAI
    */
    function buyWithEthToDai(uint id, address toTokenAddress) public payable{
        require(itemsToken.ownerOf(id) != msg.sender, "This is your own item");
        require(msg.value >= 0, "Not enough balance");

        Item storage item = allItems[id];
        require(item.available, "Item already sold");

        uint weiPerUsd = getLatestPrice();
        // verify enough payment value
        require(msg.value >= item.price * weiPerUsd);
        item.available = false;

        // return the remaining wei if any
        uint resto = msg.value - (item.price * weiPerUsd);
        if(resto > 0){
            payable(msg.sender).transfer(resto);
        }
        
        address[] memory path = new address[](2);
        path[0] = uniswapRouter.WETH();
        path[1] = toTokenAddress;

        uint prevBalance = IERC20(toTokenAddress).balanceOf(item.seller);

        uniswapRouter.swapExactETHForTokens{value: item.price * weiPerUsd}(item.price, path, item.seller, block.timestamp + 3600);

        require(prevBalance <= IERC20(toTokenAddress).balanceOf(item.seller), "Did not pay");
        
        // execute ERC721 transfer
        itemsToken.safeTransferFrom(itemsToken.ownerOf(id), msg.sender, id);

        emit BuyItem(msg.sender, item.seller, id, item.price);

        item.price = 0;
        // new event
    }

    /**
        @notice The user must have previously approved the marketplace to use his ERC20 token
        @dev User buys an item from the marketplace
    */
    function buyItem(uint id) public {
        require(itemsToken.ownerOf(id) != msg.sender, "This is your own item");

        Item storage item = allItems[id];

        require(item.available, "Item already sold");
        item.available = false;

        // get current buyer/seller balances
        uint buyerBalance = token.balanceOf(msg.sender);
        uint sellerBalance = token.balanceOf(itemsToken.ownerOf(id));
        
        // execute ERC20 transfer
        token.safeTransferFrom(msg.sender, itemsToken.ownerOf(id), item.price);

        // validate transfer success
        require(token.balanceOf(msg.sender) <= buyerBalance.sub(item.price) && token.balanceOf(itemsToken.ownerOf(id)) >= sellerBalance.add(item.price), "Transfer did not succeed");
        
        // execute ERC721 transfer
        itemsToken.safeTransferFrom(itemsToken.ownerOf(id), msg.sender, id);

        emit BuyItem(msg.sender, item.seller, id, item.price);

        item.price = 0;
        // new event
    }

}