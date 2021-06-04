import React, {Component} from "react";

class PrivChat extends Component {
    constructor(){
        super();
        this.state = {
            inputPrivate: "",
            privmessages: [],
        };
        this.handleInput = this.handleInput.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidUpdate(prevProps){
        if(this.props.nombre !== prevProps.nombre){
            this.setState({
                privmessages: []
            })
            this.props.socket.emit("load private chat", this.props.nombre, msgs => {
                this.setState({
                    privmessages: msgs
                })
            });
            let div = document.getElementById("PrivChat");
            div.scrollTop = div.scrollHeight;
        }
    }
    
    componentWillMount(){
        this.props.socket.emit("load private chat", this.props.nombre, msgs => {
            this.setState({
                privmessages: msgs
            })
        });
        this.props.socket.on("new priv message", data => { 
            if(data.from === this.props.nombre){
                this.setState({
                    privmessages: [...this.state.privmessages,data]
                })
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
        var patt = new RegExp(/^[<>]+$/g);
        var res = patt.test(this.state.inputPrivate);
        if(!res){
            this.props.socket.emit("send priv message", this.props.nombre, this.state.inputPrivate);
            let mensaje = {from: this.props.minombre, msg: this.state.inputPrivate};
            this.setState({privmessages: [...this.state.privmessages,mensaje]});
            this.setState({inputPrivate: ""});
            let div = document.getElementById("PrivChat");
            div.scrollTop = div.scrollHeight;
        }else{
            this.setState({inputPrivate: ""});
        }
    }

    render(){

        const privmsg = this.state.privmessages.map((privmessages, i) => {
            if(privmessages.from === this.props.minombre){
                return (<div className="col-7 ml-auto text-right px-0 py-2"><span className="p-2" style={{borderRadius: "10px", backgroundColor: "#b5ffb9"}}><span style={{fontWeight: "bold"}}>{privmessages.from}: </span>{privmessages.msg}</span></div>)
            }else{
                return (<div className="col-7 mr-auto px-0 py-2"><span className="p-2" style={{borderRadius: "10px", backgroundColor: "#b5c6ff"}}><span style={{fontWeight: "bold"}}>{privmessages.from}: </span>{privmessages.msg}</span></div>)
            }
        });

        return (
            <div className="card" style={{height: "50hv"}}>
                <div className="card-header p-2">
                    <div className="row">
                        <div className="col-10">
                            <i className="fas fa-user pr-1"></i>{this.props.nombre}
                        </div>
                        <div className="col-2 text-right">
                            <button className="btn p-0 m-0" onClick={this.props.salir}><i class="fas fa-window-close"></i></button>
                        </div>
                    </div>
                </div>
                <div className="card-body py-0" id="PrivChat" style={{height: "500px", overflowY: "auto"}}>
                    {privmsg}
                </div>
                <form name="privateMessage" className="card-footer" onSubmit={this.handleSubmit}>
                    <div className="input-group">
                        <input type="text" name="inputPrivate" value={this.state.inputPrivate} onChange={this.handleInput} className="form-control" required/>
                        <div className="input-group-append">
                            <input type="submit" className="btn btn-success" value="Enviar"/>
                        </div>
                    </div>
                </form>
            </div>
        )
    }
}

export default PrivChat;