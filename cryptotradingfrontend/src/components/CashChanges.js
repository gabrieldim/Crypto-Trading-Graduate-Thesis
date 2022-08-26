import React from "react"
import SellCrypto from "./SellCrypto";
import BuyCrypto from "./BuyCrypto";


export default function CashChanges(){

    return(
        <>
                <div style={{marginTop:"1%"}}>
                    <h4 style={{marginLeft:"30%"}}>Make Transaction:</h4>
                        <BuyCrypto/>
                        <SellCrypto/>
                </div>
        </>
    )

}