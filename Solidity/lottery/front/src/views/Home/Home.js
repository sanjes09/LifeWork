import React, { Component } from "react";
import Navbar from '../../components/NavBar/NavBar';
import web3 from '../../config/web3';
import lottery from '../../config/contract';
import toastr from 'toastr';
import './home.css';

export class Home extends Component {

  state = {
    manager: '',
    players: [],
    balance: '',
    amount: '',
    loading: false,
    picking: false
  };

  update = async () => {
    const manager = await lottery.methods.manager().call();
    const players = await lottery.methods.getPlayers().call();
    const balance = await web3.eth.getBalance(lottery.options.address);
    console.log('manager', manager)
    console.log('players', players)
    console.log('balance', balance)
    this.setState({
      manager,
      players,
      balance
    })
  }

  agregar = async () => {
    let accounts = await web3.eth.getAccounts();
    this.setState({loading: true})
    await lottery.methods.enter().send({from: accounts[3], value: web3.utils.toWei(this.state.amount, 'ether')});
    this.setState({loading: false})
    this.update();
  }

  pickWinner = async () => {
    let accounts = await web3.eth.getAccounts();
    this.setState({picking: true})
    await lottery.methods.pickWinner().send({from: accounts[0]});
    this.setState({picking: false})
    this.update();
    toastr.success("A winner has been picked!");
  }

  handleChange = (e) => {
    this.setState({
      [e.target.name]: e.target.value
    })
  }

  componentDidMount(){
    this.update();
  }

  render() {
    return (
      <>
      <Navbar auth={this.props.auth} home={true}/>
      <div className="home-main superCenter">
        <div className="row w-100">
          <div className="col-6 mx-auto" style={{backgroundColor: 'white', borderRadius: 10, height: '60vh'}}>
            <p>Manager: {this.state.manager}</p>
            <p>Number of players: {this.state.players}</p>
            <p>Amount: {web3.utils.fromWei(this.state.balance, 'ether')} ether</p>
            <br/>
            <hr/>
            <br/>
            <input type="text" name="amount" placeholder="Amount to send" onChange={this.handleChange}/>
            <button onClick={this.agregar}>Enter</button>
            {this.state.loading && 
              <p>Waiting for success...</p>
            }
            <br/>
            <hr/>
            <br/>
            <button onClick={this.pickWinner}>Pick a winner</button>
            {this.state.picking && 
              <p>Picking winner...</p>
            }
          </div>
        </div>
      </div>
      </>
    );
  }
}

export default Home;