import React from "react"
import SellCrypto from "./SellCrypto";
import BuyCrypto from "./BuyCrypto";
import AddMoneyInWallet from "./AddMoneyInWallet";
import WithdrawMoney from "./WithdrawMoney";

export default function CashChanges(){

    return(
        <>
            <div style={{marginTop:"1%"}}>
                <div style={{border:"2px solid black", marginLeft:"50%", marginRight:"2%"}}>
                <h4 style={{marginLeft:"30%"}}>Make Transaction:</h4>
                    <BuyCrypto/>
                    <SellCrypto/>

                </div>   
            </div>

        <div style={{border:"2px solid black", marginLeft:"50%", marginRight:"2%"}}>
            <AddMoneyInWallet/>
            <WithdrawMoney/>
        </div>
        
        </>
    )

}