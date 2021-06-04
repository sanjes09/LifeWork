import React from "react";
import Navbar from '../../components/NavBar/NavBar';
import CreateCampaignLogic from './CreateCampaignLogic';
import '../home.css';

const CreateCampaign = () => {

  const {
    handleChange,
    handleClick,
    amount,
    submit
  } = CreateCampaignLogic();

  return (
    <>
      <Navbar home={true}/>
      <div className="home-main superCenter">
        <div className="bg-white p-4" style={{borderRadius: 10, height: '80vh', width: '50vw'}}>
          <p style={{fontSize: '1.5rem'}} className="mb-4">Create a Campaign:</p>
          <div className="row">
            <div className="col-5">
              <label>Minimum contribution (wei)</label>
            </div>
            <div className="col-5">
              <input type="text" onChange={handleChange} value={amount}/>
            </div>
            <div className="col-2">
              <button onClick={handleClick}>Create</button>
            </div>
          </div>
          {submit && 
            <p>Creating...</p>
          }
        </div>
      </div>
    </>
  );
}

export default CreateCampaign;