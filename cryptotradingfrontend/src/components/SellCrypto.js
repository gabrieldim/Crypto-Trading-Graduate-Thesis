import React, { useState } from "react";
import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';
import cryptoTradingRepository from "../repository/cryptoTradingRepository";

export default function SellCrypto() {

  const [error, setErrors] = useState([]);

  const [sellCryptoInfo, setSellCryptoInfo] = useState({
    currencyName: "",
    amountToSell: 0.0
  });

  const handleChange = (event) => {
    setSellCryptoInfo({ ...sellCryptoInfo, [event.target.name]: event.target.value });
  };


  const onFormSubmit = (e) => {
    e.preventDefault();

    setErrors([])
    cryptoTradingRepository.sellCrypto(sellCryptoInfo.currencyName, sellCryptoInfo.amountToSell)
    .catch(error => {
      setErrors("- Something went wrong, the transaction is not completed: " + error.response.data.error)
    });;
}


    return (
      <div style={{marginLeft:"2%"}}>    
            <form style={{marginTop:"1%", marginLeft:"2%"}} onSubmit={onFormSubmit}>
                <label>
                  Cryptocurrency Name: 
                 <input type="text" onChange={handleChange} name="currencyName" />
                </label>
                <br/>
                <br/>
                <label>
                  Amount(USD):
                 <input type="number" onChange={handleChange} name="amountToSell" />
                </label>
                <br/>
                <Button type='submit' style={{margin:"3%"}} variant="danger">Sell Crypto</Button>
            </form>   
            <div style={{color:"red", marginTop:"-60px", marginBottom:"60px", marginLeft:"400px"}}>{error}</div>
        </div>
      );
}
