import React from "react"
import SellCrypto from "./SellCrypto";
import BuyCrypto from "./BuyCrypto";
import AddMoneyInWallet from "./AddMoneyInWallet";
import WithdrawMoney from "./WithdrawMoney";

export default function CashChanges(){

    return(
        <>
            <div>
                <div style={{marginTop:"1%"}}>
                    <h4 style={{marginLeft:"30%"}}>Make Transaction:</h4>
                        <BuyCrypto/>
                        <SellCrypto/>
 
                </div>
                <div>
                    <h4 style={{marginLeft:"30%"}}>Deposit Available Resources:</h4>
                        <AddMoneyInWallet/>
                    <h4 style={{marginLeft:"30%"}}>Withdraw Money:</h4>
                        <WithdrawMoney/>
                </div>
            </div>
        </>
    )

}