import React, { useState } from "react";
import cryptoTradingRepository from "../repository/cryptoTradingRepository";
import Button from 'react-bootstrap/Button';

export default function WithdrawMoney() {


    const [amount, setAmount] = useState(0.0);
    const [bytes, setBytes] = useState("");
    const [error, setErrors] = useState([]);

    function handleChange(event) {
        setAmount(event.target.value);
    }


    const onFormSubmit = (e) => {

      e.preventDefault();
      cryptoTradingRepository.generatePDF(amount).then(
        (response) => {
            setErrors([])
            const returnedBytes = response.data;
            setBytes(returnedBytes)
            generate();
        }
      )
      .catch(error => {
        setErrors("- You don't have enough available resources to complete this action!" /*+ error.response.data.error*/)
      });

  }

  function generate() {
    console.log("generate: " + bytes)
    const url = window.URL.createObjectURL(new Blob([bytes]));
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', 'Invoice-Payment.pdf');
    document.body.appendChild(link);
    link.click();
    }


    return(
        <div>
            <h4 style={{marginLeft:"3%"}}>Withdraw Money:</h4>
            <form style={{marginLeft:"3%"}} onSubmit={onFormSubmit} >
                <label>
                  Amount(USD):
                 <input type="number" name="amount" onChange={handleChange}/>
                </label>
                <br/>
                <Button type='submit' style={{margin:"3%", backgroundColor:"grey"}}>Withdraw Money</Button>
            </form>
            <div style={{color:"red", marginTop:"-60px", marginBottom:"60px", marginLeft:"400px"}}>{error}</div>
        </div>
    )
}