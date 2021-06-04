import React, {Component} from "react";
import PrivChat from "./PrivChat";
import toastr from "toastr";
import 'toastr/build/toastr.min.css'

class Chat extends Component {
    constructor(){
        super();
        this.state = {
            inputGlobal: "",
            privChat: "",
            messages: [],
            users: [],
            notifications: ''
        };
        this.handleInput = this.handleInput.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentWillMount(){
        
        this.props.socket.emit("load chat", msgs => {
            this.setState({
                messages: msgs
            })
        });
        
        this.props.socket.on("usernames", usuarios => {
            let x = [];
            usuarios.forEach(element => {
                x.push({name: element,notif:0});
            });

            this.setState({
                users: x,
            })
        });

        this.props.socket.on("new message", data => { 
            this.setState({
                messages: [...this.state.messages,data]
            })
            let div = document.getElementById("GlobalChat");
            div.scrollTop = div.scrollHeight;
        });

        this.props.socket.on("new priv message", data => { 
            if(data.from !== this.state.privChat){
                const x = this.state.users.map((user, i) => {
                    if(user.name === data.from){
                        return ({name: user.name, notif: parseInt(user.notif)+1})
                    }else{
                        return ({name: user.name, notif: user.notif})
                    }
                });
                this.setState({users: x})
            }else{
                toastr.options = {
                    positionClass : 'toast-top-full-width',
                    hideDuration: 300,
                    timeOut: 60000
                }
                toastr.clear()
                setTimeout(() => toastr.success("New private message from: '"+data.from+"'"), 300)
            }
        });
        
    }
        
    handleInput(e){
        const {value,name} = e.target
        this.setState({
            [name]: value
        });
    }

    handleSubmit(e){
        e.preventDefault();
        this.props.socket.emit("send message", this.state.inputGlobal);
        this.setState({inputGlobal:""});
        let div = document.getElementById("GlobalChat");
        div.scrollTop = div.scrollHeight;
    }  

    handlePrivChat(user){
        if(this.props.minombre !== user){
            const x = this.state.users.map((value, i) => {
                if(value.name === user){
                    return ({name: value.name, notif: 0})
                }else{
                    return ({name: value.name, notif: value.notif})
                }
            });
            this.setState({users: x})
            this.setState({privChat: user});
        }
    }

    handleExitPrivChat = () => {
        this.setState({privChat: "", notifications: ""})
    }

    render(){
        const allmsg = this.state.messages.map((messages, i) => {
            if(messages.user === this.props.minombre){
                return(<div className="col-7 ml-auto text-right px-0 py-2"><span className="p-2" style={{borderRadius: "10px", backgroundColor: "#b5ffb9"}}><span style={{fontWeight: "bold"}}>{messages.user}: </span>{messages.msg}</span></div>)
            }
            else{
                return(<div className="col-7 mr-auto px-0 py-2"><span className="p-2" style={{borderRadius: "10px", backgroundColor: "#b5c6ff"}}><span style={{fontWeight: "bold"}}>{messages.user}: </span>{messages.msg}</span></div>)
            }
        });
        const users = this.state.users.map((user, i) => {
            return (
                <button class="btn d-block" onClick={()=>{this.handlePrivChat(user.name)}}><i class="fas fa-user pr-1"></i>{user.name}{user.notif ? <span class="badge badge-danger ml-1">{user.notif}</span> : ""}</button>
            )
        });

        return (
            <div className="row">
                {/* <div style={{position: "absolute", right: 0, zIndex:1 }}>{this.state.notifications ? <div class="alert alert-success alert-dismissible fade show" role="alert">New message from: {this.state.notifications}<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>: ""}</div> */}
                <div className="col-10">
                    <div className="row mt-1">
                        <div className="col-7">
                            <div className="card">
                                <div className="card-header p-2">
                                    Chat
                                </div>
                                <div className="card-body py-0" id="GlobalChat" style={{height: "500px", overflowY: "auto"}}>
                                    {allmsg}
                                </div>
                                <form name="globalMessage" className="card-footer" onSubmit={this.handleSubmit}>
                                    <div className="input-group">
                                        <input type="text" name="inputGlobal" value={this.state.inputGlobal} onChange={this.handleInput} className="form-control" placeholder="Mensaje" required/>
                                        <div className="input-group-append">
                                            <input type="submit" className="btn btn-success" value="Enviar"/>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div className="col-5">
                            {this.state.privChat ? <PrivChat salir={this.handleExitPrivChat} nombre={this.state.privChat} minombre={this.props.minombre} socket={this.props.socket}/> : ""}
                        </div>
                    </div>
                </div>
                <div className="col-2 mt-1">
                    <div className="card">
                        <div className="card-header p-2">
                            Online Users
                        </div>
                        <div className="card-body" style={{height: "550px", overflowY: "scroll"}}>
                            {users}
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default Chat;