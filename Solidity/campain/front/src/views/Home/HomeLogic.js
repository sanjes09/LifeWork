import {useState} from "react";
import Factory from '../../config/contract';

const HomeLogic = () => {

  const [campaigns, setCampaigns] = useState([]);

  const getCampaigns = async () => {
    let camp = await Factory.methods.getDeployedCampaigns().call();
    setCampaigns(camp);
  }

  return {
    getCampaigns,
    campaigns
  };
}

export default HomeLogic;