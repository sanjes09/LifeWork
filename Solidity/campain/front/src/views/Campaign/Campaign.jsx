import React, {useEffect} from "react";
import {Link} from 'react-router-dom';
import Navbar from '../../components/NavBar/NavBar';
import CampaignLogic from './CampaignLogic';
import '../home.css';

const Campaign = (props) => {

  const {
    getData,
    handleChange,
    contribute,
    contributors,
    minimumC,
    campaignBalance,
    requests,
    amount,
    submit
  } = CampaignLogic(props);

  useEffect(()=>{
    getData();
    //eslint-disable-next-line react-hooks/exhaustive-deps
  },[]);

  return (
    <>
      <Navbar home={true}/>
      <div className="home-main superCenter">
        <div className="bg-white p-4" style={{borderRadius: 10, height: '80vh', width: '50vw'}}>
          <div className="row p-0 m-0">
            <div className="col-6 p-0 m-0">
              <div className="row p-0 m-0">
                <div className="col-6 p-2 m-0" style={{border: '1px solid lightgrey'}}>
                  <p>{campaignBalance}</p>
                  <span>Campaign Balance</span>
                </div>
                <div className="col-6 p-2 m-0" style={{border: '1px solid lightgrey'}}>
                  <p>{minimumC}</p>
                  <span>Minimum Contribution</span>
                </div>
              </div>
              <div className="row p-0 m-0">
                <div className="col-6 p-2 m-0" style={{border: '1px solid lightgrey'}}>
                  <p>{requests}</p>
                  <span>Total Requests</span>
                </div>
                <div className="col-6 p-2 m-0" style={{border: '1px solid lightgrey'}}>
                  <p>{contributors}</p>
                  <span>Contributors</span>
                </div>
              </div>
            </div>
            <div className="col-6 p-0 m-0 text-center">
              <p>Contribue to this campaign</p>
              <input type="text" value={amount} onChange={handleChange}/>
              <br/>
              <button className="mt-2" onClick={contribute}>Contibute</button>
              {submit && 
                <p>Processing...</p>
              }
            </div>
          </div>
          <div className="row p-0 m-0 mt-2">
            <Link to={`/campaign/${props.match.params.address}/requests`}>
              <button>View Requests</button>
            </Link>
          </div>
        </div>
      </div>
    </>
  );
}

export default Campaign;