import React, {useEffect} from "react";
import {Link} from 'react-router-dom';
import Navbar from '../../components/NavBar/NavBar';
import AddRequestLogic from './AddRequestLogic';
import '../home.css';

const AddRequest = (props) => {

  const {
    handleChange,
    handleSubmit,
    description,
    amount,
    recipient,
    submit
  } = AddRequestLogic(props);

  return (
    <>
      <Navbar home={true}/>
      <div className="home-main superCenter">
        <div className="bg-white p-4" style={{borderRadius: 10, height: '80vh', width: '50vw'}}>
          <div className="row p-0 m-0">
            <div className="col-6 p-0 m-0 text-center">
              <p>Create a request</p>
              <label>Description</label>
              <br/>
              <input type="text" name="description" value={description} onChange={handleChange}/>
              <br/>
              <label>Amount (wei)</label>
              <br/>
              <input type="text" name="amount" value={amount} onChange={handleChange}/>
              <br/>
              <label>Recipient</label>
              <br/>
              <input type="text" name="recipient" value={recipient} onChange={handleChange}/>
              <br/>
              <button className="mt-2" onClick={handleSubmit}>Contibute</button>
              {submit && 
                <p>Processing...</p>
              }
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default AddRequest;