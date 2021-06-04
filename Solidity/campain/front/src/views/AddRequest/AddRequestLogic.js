import {useState} from "react";
import contrato from '../../config/campaign';
import toastr from 'toastr';
import web3 from '../../config/web3';

const AddRequestLogic = (props) => {
  const [description, setDescription] = useState('');
  const [amount, setAmount] = useState('');
  const [recipient, setRecipient] = useState('');
  const [submit, setsubmit] = useState(false);
  
  const Campaign = contrato(props.match.params.address);
  
  const handleChange = (e) => {
    switch (e.target.name) {
      case "description":
        setDescription(e.target.value);
        break;
      case "amount":
        setAmount(e.target.value);
        break;
      case "recipient":
        setRecipient(e.target.value);
        break;
      default: return ""
    }
  }

  const handleSubmit = async () => {
    try {
      setsubmit(true);
      const accounts = await web3.eth.getAccounts();
      await Campaign.methods.createRequest(description, amount, recipient).send({
        from: accounts[0]
      })
      setsubmit(false);
    } catch (error) {
      setsubmit(false);
      console.log('error', error);
      toastr.error("Cound not get data from the campaign");
    }
  }

  return {
    handleChange,
    handleSubmit,
    description,
    amount,
    recipient,
    submit
  };
}

export default AddRequestLogic;