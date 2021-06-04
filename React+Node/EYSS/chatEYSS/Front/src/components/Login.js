import React, {Component} from 'react';

class Login extends Component {
    state = {
        username:'',
        password:'',
        alert:''
    }

    handleChange = ({target}) => {
        const name = target.name
        const value = target.value
        this.setState({
            [name]: value
        })
    }
  
    handleSubmit = (e) => {
        e.preventDefault();
        var patt = new RegExp(/^[<>]+$/g);
        var res = patt.test(this.state.username);
        if(!res){
            fetch("/login?data="+JSON.stringify(this.state))
            .then(response => response.json())
            .then(data => {
                if(!data.ok){
                    this.setState({alert: data.error})
                }else{
                    this.props.socket.emit("new user", this.state.username, isValid => {
                        if(isValid === 0){
                            this.props.minombre(this.state.username);
                        }
                        if(isValid === 1){
                            this.setState({alert: "Nickname already Exists"});
                        }
                        if(isValid === 2){
                            this.setState({alert: "You are already chating"});
                        }
                    });
                }
            });
        }else{
            this.setState({alert: "Caracteres invalidos"});
        }
    }

    render (){
        return (
            <div className="container" id="loginWindows">
                <div className="row mt-4">
                <div className="col-4 mx-auto">
                        <div className="card">
                            <div className="card-header text-center">
                                <div id="alerta">{this.state.alert ? <div class="alert alert-danger">{this.state.alert}</div> : ""}</div>
                            </div>
                            <div className="card-body" id="chat">
                                <form onSubmit={this.handleSubmit}>
                                    <div className="input-group mb-2">
                                        Username:
                                    </div>
                                    <div className="input-group mb-2">
                                        <input type="text" id="username" name="username" className="form-control" onChange={this.handleChange}/>
                                    </div>
                                    <div className="input-group mb-2">
                                        Password:
                                    </div>
                                    <div className="input-group mb-2">
                                        <input type="password" id="password" name="password" className="form-control" onChange={this.handleChange}/>
                                    </div>
                                    <div className="input-group">
                                        <input type="submit" className="btn btn-primary mx-auto" value="Login" />
                                    </div>
                                </form>
                            </div>
                            <div className="card-footer">
                                <button class="btn btn-sm btn-success mx-auto" onClick={this.props.change}>Or Register</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
  
export default Login;










