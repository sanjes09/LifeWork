import React, {Component} from 'react';
import './App.css';
import Nav from "./components/Nav";
import Chat from "./components/Chat";
import Login from "./components/Login";
import Option from "./components/Option";
import Reg from "./components/Reg";
import io from "socket.io-client";


class App extends Component {
  
  state = {
    minombre: '',
    reg: '',
    login: '1'
  }

  componentWillMount(){
    this.socket = io("/");
    fetch("/logged")
    .then(response => response.json())
    .then(data => {
      if(data.name!==""){
        this.socket.emit("new user", data.name, isValid =>{});
        this.setState({minombre: data.name, login: '', reg: ''});
      }
    });
  }

  handlelogout = () =>{
    fetch("/logout")
    .then(response => response.json())
    .then(data => {
      if(data.ok){
        this.socket.emit("logout", data.name);
        this.setState({minombre: "", login:'1'});
      }
    });
  }

  changeName = (data) => {
    this.setState({minombre: data, login: ""})
  }

  handlechangeOption = () => {
    if(this.state.login){
      this.setState({
        login: '',
        reg: '1'
      })
    }else{
      this.setState({
        login: '1',
        reg: ''
      })
    }
  }

  handleRegistrado = () => {
    this.setState({reg: "", login: "1"});
  }

  render (){
    
    return (
      <div>
        <Nav logout={this.handlelogout}/>
        <div className="container-fluid">
          {this.state.login ? <Login change={this.handlechangeOption} socket={this.socket} minombre={this.changeName}/> : null}
          {this.state.reg ? <Reg change={this.handlechangeOption} registrado={this.handleRegistrado}/> : null}
          {this.state.minombre ? <Chat minombre={this.state.minombre} socket={this.socket} /> : null}
        </div>
      </div>
    );
  }
}

export default App;