import {useState, useEffect} from "react";
import contrato from '../../config/campaign';
import web3 from '../../config/web3';
import toastr from 'toastr';

const CampaignRequestsLogic = (props) => {
  const [requests, setRequests] = useState([]);
  const [count, setCount] = useState(0);
  const [doing, setDoing] = useState(false);
  
  const Campaign = contrato(props.match.params.address);

  const getData = async () => {
    try {
      let data = await Campaign.methods.getRequests().call();
      let count = await Campaign.methods.approversCount().call();
      setCount(count);
      setRequests(data);
    } catch (error) {
      console.log('error', error);
      toastr.error(error.message);
    }
  }

  const approve = async (index) => {
    try {
      setDoing(true)
      const accounts = await web3.eth.getAccounts();
      await Campaign.methods.approveRequest(index).send({
        from: accounts[0]
      });
      getData();
      setDoing(false)
    } catch (error) {
      setDoing(false)
      console.log('error', error);
      toastr.error(error.message);
    }
  }

  const finalize = async (index) => {
    try {
      setDoing(true)
      const accounts = await web3.eth.getAccounts();
      await Campaign.methods.finalizeRequest(index).send({
        from: accounts[0]
      });
      getData();
      setDoing(false)
    } catch (error) {
      setDoing(false)
      console.log('error', error);
      toastr.error(error.message);
    }
  }
  
  return {
    getData,
    approve,
    finalize,
    requests,
    doing,
    count
  };
}

export default CampaignRequestsLogic;