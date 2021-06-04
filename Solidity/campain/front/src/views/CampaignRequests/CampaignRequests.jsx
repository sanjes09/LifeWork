import React, {useEffect} from "react";
import {Link} from 'react-router-dom';
import Navbar from '../../components/NavBar/NavBar';
import CampaignRequestsLogic from './CampaignRequestsLogic';
import '../home.css';

const CampaignRequests = (props) => {

  const {
    getData,
    approve,
    finalize,
    requests,
    doing,
    count
  } = CampaignRequestsLogic(props);

  useEffect(()=>{
    getData();
  },[]);

  return (
    <>
      <Navbar home={true}/>
      <div className="home-main superCenter">
        <div className="bg-white p-4" style={{borderRadius: 10, height: '80vh', width: '50vw'}}>
          <div className="row p-0 m-0">
            <div className="col-8">
              <p>Pending Requests:</p>
            </div>
            <div className="col-4 text-right">
              <Link to={`/campaign/${props.match.params.address}/add-requests`}>
                <button>Add Request</button>
              </Link>
            </div>
          </div>
          <div className="row p-0 m-0">
            <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '10%'}}>ID</div>
            <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '20%'}}>Description</div>
            <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '15%'}}>Amount</div>
            <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '15%'}}>Recipient</div>
            <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '10%'}}>Approvals</div>
            <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '15%'}}>Approve</div>
            <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '15%'}}>Finalize</div>
          </div>
          {requests.map((arg,index)=>{
            if(!arg.complete)
              return(
                <div className="row p-0 m-0">
                  <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '10%', wordBreak: 'break-all'}}>{index}</div>
                  <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '20%', wordBreak: 'break-all'}}>{arg.description}</div>
                  <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '15%', wordBreak: 'break-all'}}>{arg.value}</div>
                  <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '15%', wordBreak: 'break-all'}}>{arg.recipient}</div>
                  <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '10%', wordBreak: 'break-all'}}>{arg.approvedCount}/{count}</div>
                  <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '15%', wordBreak: 'break-all'}}>
                    <button onClick={()=>approve(index)}>Approve</button>
                  </div>
                  <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '15%', wordBreak: 'break-all'}}>
                    <button onClick={()=>finalize(index)}>Finalize</button>
                  </div>
                </div>
              )
          })}
          <br/>
          <div className="row p-0 m-0">
            <div className="col-8">
              <p>Finalized Requests:</p>
            </div>
            <div className="col-4 text-right">
            </div>
          </div>
          {requests.map((arg,index)=>{
            if(arg.complete)
              return(
                <div className="row p-0 m-0">
                  <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '10%', wordBreak: 'break-all'}}>{index}</div>
                  <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '20%', wordBreak: 'break-all'}}>{arg.description}</div>
                  <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '15%', wordBreak: 'break-all'}}>{arg.value}</div>
                  <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '15%', wordBreak: 'break-all'}}>{arg.recipient}</div>
                  <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '10%', wordBreak: 'break-all'}}></div>
                  <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '15%', wordBreak: 'break-all'}}>
                  </div>
                  <div style={{border: '1px solid lightgrey', textAlign: 'center', width: '15%', wordBreak: 'break-all'}}>
                  </div>
                </div>
              )
          })}
          {doing && 
            <p>Processing...</p>
          }
        </div>
      </div>
    </>
  );
}

export default CampaignRequests;