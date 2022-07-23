import React, { useState } from "react";
import Button from 'react-bootstrap/Button';
import cryptoTradingRepository from "../repository/cryptoTradingRepository";

export default function BuyCrypto() {

  const [buyCryptoInfo, setBuyCryptoInfo] = useState({
    currencyName: "",
    amountToBuy: 0.0
  });

  const handleChange = (event) => {
    setBuyCryptoInfo({ ...buyCryptoInfo, [event.target.name]: event.target.value });
  };


  const onFormSubmit = (e) => {
    e.preventDefault();
    console.log(buyCryptoInfo.currencyName + " " + buyCryptoInfo.amountToBuy)
    cryptoTradingRepository.buyCrypto(buyCryptoInfo.currencyName, buyCryptoInfo.amountToBuy);
}

    return (
      <div style={{marginTop:"3%", marginLeft:"2%"}}>
              
            <form style={{marginTop:"1%", marginLeft:"2%"}} onSubmit={onFormSubmit}>
                <label>
                  Cryptocurrency Name: 
                 <input type="text" onChange={handleChange} name="currencyName" />
                </label>
                <br/>
                <br/>
                <label>
                  Amount(USD):
                 <input type="number" onChange={handleChange} name="amountToBuy" />
                </label>
                <br/>
                <Button type='submit' style={{margin:"3%"}}>Buy Crypto</Button>
            </form>

        </div>
      );
}

