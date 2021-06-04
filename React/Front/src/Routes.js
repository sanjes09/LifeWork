import React, { Component } from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Register from "./views/Register";
import Login from "./views/Login";
import Dashboard from "./views/Dashboard";
import Axios from "axios";
import Spinner from 'react-bootstrap/Spinner';


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
          <div className="col-2 text-center mx-auto my-5"><Spinner animation="border" variant="secondary"/></div>
        :
          <BrowserRouter>
            <Switch> 
              {/* {this.state.auth ? <Route exact path="/projects" component={Projects}></Route> : null}
              {this.state.auth ? <Route exact path="/project/:id" component={Dashboard}></Route> : null}
              {this.state.auth ? <Route exact path="/report/:id" component={MiddleReport}></Route> : null}
              {this.state.auth ? <Route exact path="/reportPDF" component={ReportPDF}></Route> : null} */}

              {!this.state.auth ? <Route exact path="/register" component={Register}></Route> : null}
              {!this.state.auth ? <Route exact path="/login" component={Login}></Route> : null}

              {/* <Route exact path="/" component={Home}></Route>

              <Route exact component={Home} auth={false}></Route> */}
              </Switch>
          </BrowserRouter>
        }
      </>
    );
  }
}

export default Routes;
