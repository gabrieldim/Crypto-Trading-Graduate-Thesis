import React, { useState } from "react";
import cryptoTradingRepository from "../repository/cryptoTradingRepository";
import Button from 'react-bootstrap/Button';

export default function AddMoneyInWallet() {

    const [deposit, setDepositAmount] = useState(0.0);

    function handleChange(event) {
      setDepositAmount(event.target.value);
    }

    const onFormSubmit = (e) => {
      e.preventDefault();
      cryptoTradingRepository.addMoney(deposit);
  }

  
    return (
        <div>
            <h4 style={{marginLeft:"60%"}}>Deposit Available Resources:</h4>
            <form style={{marginLeft:"52%"}} onSubmit={onFormSubmit} >
                <label>
                  Amount(USD):
                 <input type="number" name="deposit" onChange={handleChange}/>
                </label>
                <br/>
                <Button type='submit' variant="success" style={{margin:"3%"}}>Deposit Cash</Button>
            </form>
        </div>
      );
}

