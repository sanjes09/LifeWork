import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Home from "./views/Home/Home.jsx";
import Campaign from "./views/Campaign/Campaign.jsx";
import CreateCampaign from "./views/CreateCampaign/CreateCampaign.jsx";
import CampaignRequests from "./views/CampaignRequests/CampaignRequests.jsx";
import AddRequest from "./views/AddRequest/AddRequest.jsx";

const Routes = () => {
  return (
    <>
      <BrowserRouter>
        <Switch> 
          <Route exact path="/" component={Home}/>
          <Route exact path="/create-campaign" component={CreateCampaign}/>
          <Route exact path="/campaign/:address" component={Campaign}/>
          <Route exact path="/campaign/:address/requests" component={CampaignRequests}/>
          <Route exact path="/campaign/:address/add-requests" component={AddRequest}/>
          
          <Route exact component={Home}/>
        </Switch>
      </BrowserRouter>
    </>
  );
}

export default Routes;
