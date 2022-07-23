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
        <div style={{marginLeft:"46%", marginTop:"10%"}}>
            <h4 style={{marginTop:"-57%"}}>Deposit Available Resources(USD):</h4>
            <form style={{marginTop:"1%"}} onSubmit={onFormSubmit}>
                <label>
                  Amount:
                 <input type="number" name="deposit" onChange={handleChange}/>
                </label>
                <br/>
                <Button type='submit' variant="success" style={{marginTop:"1%"}}>Deposit Cash</Button>
            </form>
        </div>
      );
}

