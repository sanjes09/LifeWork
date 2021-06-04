import React, { Component } from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Home from "./views/Home/Home";
// import Spinner from 'react-bootstrap/Spinner';

export class Routes extends Component {

  render() {
    return (
      <>
        {/* {this.state.loading ? 
          <div className="col-2 text-center mx-auto"><Spinner animation="border" className='colorSpinner'/></div>
        : */}
          <BrowserRouter>
            <Switch> 
              <Route exact render={() => <Home/>}/>
            </Switch>
          </BrowserRouter>
        {/* } */}
      </>
    );
  }
}

export default Routes;
