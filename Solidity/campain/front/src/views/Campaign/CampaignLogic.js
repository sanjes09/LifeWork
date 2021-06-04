import {useState, useEffect} from "react";
import contrato from '../../config/campaign';
import toastr from 'toastr';
import web3 from '../../config/web3';

const CampaignLogic = (props) => {
  const [contributors, setContributors] = useState('0');
  const [minimumC, setMinimumC] = useState('0');
  const [campaignBalance, setCampaignBalance] = useState('0');
  const [requests, setRequests] = useState('0');
  const [manager, setManager] = useState('0');
  const [amount, setAmount] = useState('');
  const [submit, setsubmit] = useState(false);
  
  const Campaign = contrato(props.match.params.address);

  const getData = async () => {
    try {
      let data = await Campaign.methods.getBasicData().call();
      setMinimumC(data[0]);
      setCampaignBalance(data[1]);
      setRequests(data[2]);
      setContributors(data[3]);
      setManager(data[4]);
    } catch (error) {
      console.log('error', error);
      toastr.error("Cound not get data from the campaign");
    }
  }

  const handleChange = (e) => {
    setAmount(e.target.value);
  }

  const contribute = async () => {
    try {
      setsubmit(true);
      const accounts = await web3.eth.getAccounts();
      await Campaign.methods.contribute().send({
        from: accounts[0],
        value: amount
      })
      getData();
      setsubmit(false);
    } catch (error) {
      setsubmit(false);
      console.log('error', error);
      toastr.error("Cound not get data from the campaign");
    }
  }

  return {
    getData,
    handleChange,
    contribute,
    contributors,
    minimumC,
    campaignBalance,
    requests,
    manager,
    amount,
    submit
  };
}

export default CampaignLogic;