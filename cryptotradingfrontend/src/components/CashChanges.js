import React from "react"
import SellCrypto from "./SellCrypto";
import BuyCrypto from "./BuyCrypto";
import AddMoneyInWallet from "./AddMoneyInWallet";

export default function CashChanges(){

    return(
        <>
            <div style={{marginTop:"3%", marginLeft:"45%"}}>
                
                <h4 >Make Transaction:</h4>
                <form style={{marginTop:"1%", marginLeft:"2%"}}>
                    <label>
                      Cryptocurrency Name: 
                     <input type="text" name="currencyName" />
                    </label>
                    <br/>
                    <br/>
                    <label>
                      Amount(USD):
                     <input type="number" name="amount"/>
                    </label>
                </form>


                <BuyCrypto/>
                <SellCrypto/>


                <AddMoneyInWallet/>

            
            </div>
        </>
    )

}