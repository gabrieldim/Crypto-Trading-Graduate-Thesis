import React from "react";
import AddMoneyInWallet from "./AddMoneyInWallet";
import NavBar from "./NavBar";
import WithdrawMoney from "./WithdrawMoney";


export default function Balance(){

return (
    <>    
        <NavBar/>

        <div style={{marginTop: "1%"}}>
            <h4 style={{marginLeft:"3%"}}>Deposit Available Resources:</h4>
                <AddMoneyInWallet/>
            <h4 style={{marginLeft:"3%"}}>Withdraw Money:</h4>
                <WithdrawMoney/>
        </div>
    </>
)

}