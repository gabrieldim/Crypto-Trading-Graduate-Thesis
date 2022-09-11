import React, { useState } from "react";
import cryptoTradingRepository from "../repository/cryptoTradingRepository";

export default function WithdrawMoney() {


    const [amount, setAmount] = useState(0.0);
    const [error, setErrors] = useState([]);
    const [redirectURL, setRedirectURL] = useState("");


    function handleChange(event) {
        setAmount(event.target.value);
        console.log("amount: " + event.target.value)
        cryptoTradingRepository.getLoggedUser().then(
          (response) => {
              const loggedUser = response.data;
              setRedirectURL(`http://localhost:8091/api/generatePDF?amount=${event.target.value}&username=${loggedUser}`)
          }
      )
    }


    const onFormSubmit = (e) => {

      e.preventDefault();
      cryptoTradingRepository.generatePDF(amount).then(
        (response) => {
            setErrors([])
            generate(response);
        }
      )
      .catch(error => {
        setErrors("- You don't have enough available resources to complete this action!" /*+ error.response.data.error*/)
      });

  }

  function generate(generateBytes) {
    console.log("generate: " + generateBytes)
    const url = window.URL.createObjectURL(new Blob([generateBytes], {type:'application/pdf'}));
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', 'Invoice-Payment.pdf');
    document.body.appendChild(link);
    link.click(); 
    }


    return(
        <div>
            <form style={{marginLeft:"3%"}} onSubmit={onFormSubmit} >
                <label>
                  Amount(USD):
                 <input type="number" name="amount" style={{marginBottom:"8%"}} onChange={handleChange}/>
                </label>
                <br/>             
                <a href={redirectURL}  className="button">Withdraw Money</a>
            </form>
            <div style={{color:"red", marginTop:"-60px", marginBottom:"60px", marginLeft:"400px"}}>{error}</div>
        </div>
    )
}