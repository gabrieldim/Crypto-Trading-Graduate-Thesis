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
            <form style={{marginLeft:"3%"}} onSubmit={onFormSubmit} >
                <label>
                  Amount(USD):
                 <input type="number" name="deposit" onChange={handleChange}/>
                </label>
                <br/>
                <Button type='submit' variant="success" style={{margin:"2%"}}>Deposit Cash</Button>
            </form>
        </div>
      );
}

