import {useState} from "react";
import Factory from '../../config/contract';
import web3 from '../../config/web3';
import toastr from 'toastr';

const CreateCampaignLogic = () => {

  const [amount, setAmount] = useState("");
  const [submit, setSubmit] = useState(false);

  const handleChange = (e) => {
    setAmount(e.target.value)
  }

  const handleClick = async () => {
    try {
      setSubmit(true);
      const accounts = await web3.eth.getAccounts();
      const response = await Factory.methods.createCampaign(amount).send({
        from: accounts[0]
      });
      if(response.status){
        toastr.success("Created");
      }else{
        toastr.error("Unknown error");
      }
      setSubmit(false);
    } catch (error) {
      toastr.error(error.message)
      setSubmit(false);
    }
  }

  return {
    handleChange,
    handleClick,
    amount,
    submit
  };
}

export default CreateCampaignLogic;