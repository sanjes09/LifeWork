import React, {useEffect} from "react";
import {Link} from 'react-router-dom';
import Navbar from '../../components/NavBar/NavBar';
import HomeLogic from './HomeLogic';
import '../home.css';

const Home = () => {

  const {
    getCampaigns,
    campaigns
  } = HomeLogic();

  useEffect(()=>{
    getCampaigns();
    //eslint-disable-next-line react-hooks/exhaustive-deps
  },[]);

  return (
    <>
      <Navbar home={true}/>
      <div className="home-main superCenter">
        <div className="bg-white p-4" style={{borderRadius: 10, height: '80vh', width: '50vw'}}>
          <div className="row">
            <div className="col-8">
              <p style={{fontSize: '1.5rem'}}>Current Campaigns:</p>
            </div>
            <div className="col-4 text-right">
              <Link to="/create-campaign">
                <button>Create new</button>
              </Link>
            </div>
          </div>
          {campaigns.map((arg)=>{
            return (
              <div className="row py-2" style={{borderTop: '1px dotted lightgrey'}}>
                <div className="col-8 text-center">
                  {arg}
                </div>
                <div className="col-4 text-center">
                  <Link to={'/campaign/'+arg}>
                    <button>View details</button>
                  </Link>
                </div>
              </div>
            )
          })}
        </div>
      </div>
    </>
  );
}

export default Home;