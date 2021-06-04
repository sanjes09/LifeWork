import React, { Component } from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Register from "./views/Register";
import Login from "./views/Login";
import Dashboard from "./views/Dashboard";
import Axios from "axios";

export class Routes extends Component {

  state = {
    loading: true,
    auth: false
  }

  componentDidMount(){
    Axios
    .get('/logged')
    .then(response => {
        if(response.data.valid){
          this.setState({
            loading: false,
            auth: true,
          })
        }else{
          this.setState({
            loading: false,  
            auth: false
          })
        }
    })
  }

  render() {
    return (
      <>
        {this.state.loading ? 
          <div className="col-2 text-center mx-auto my-5"><div className="loader">Loading...</div></div>
        :
          <BrowserRouter>
            <Switch> 
              {!this.state.auth ? <Route exact path="/register" component={Register}></Route> : null}
              {!this.state.auth ? <Route exact path="/login" component={Login}></Route> : null}

              {this.state.auth ? <Route exact path="/dashboard" component={Dashboard}></Route> : null}

              {this.state.auth ? <Route exact component={Dashboard}></Route>: null}
              {!this.state.auth ? <Route exact component={Login}></Route> : null}
              <Route exact component={Login}></Route>

              </Switch>
          </BrowserRouter>
        }
      </>
    );
  }
}

export default Routes;
