import React, {Component} from 'react';

class Option extends Component {

    render (){
        return (
            <div className="container" id="loginWindows">
                <div className="row mt-4">
                    <div className="col-4 mx-auto">
                        <div className="card">
                            <div className="card-body" id="chat">
                                <div className="input-group">
                                    <button className="btn btn-primary mx-auto" onClick={this.props.reg}>Register</button>
                                    <button className="btn btn-success mx-auto" onClick={this.props.login}>Login</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
  
export default Option;










