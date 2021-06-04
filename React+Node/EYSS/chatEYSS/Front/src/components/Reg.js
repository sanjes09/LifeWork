import React, {Component} from 'react';

class Reg extends Component {
    state = {
        username:'',
        email:'',
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
        fetch("/signup?data="+JSON.stringify(this.state))
        .then(response => response.json())
        .then(data => {
            if(!data.ok){
                this.setState({alert: data.error})
            }else{
                this.props.registrado();
            }
        });
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
                                        Email:
                                    </div>
                                    <div className="input-group mb-2">
                                        <input type="email" id="email" name="email" className="form-control" onChange={this.handleChange}/>
                                    </div>
                                    <div className="input-group mb-2">
                                        Password:
                                    </div>
                                    <div className="input-group mb-2">
                                        <input type="password" id="password" name="password" className="form-control" onChange={this.handleChange}/>
                                    </div>
                                    <div className="input-group">
                                        <input type="submit" className="btn btn-success mx-auto" value="Register" />
                                    </div>
                                </form>
                            </div>
                            <div className="card-footer">
                                <button class="btn btn-sm btn-primary mx-auto" onClick={this.props.change}>Or Login</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
  
export default Reg;