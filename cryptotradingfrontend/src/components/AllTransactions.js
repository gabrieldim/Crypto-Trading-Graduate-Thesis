import React, { useEffect, useState } from "react";
import NavBar from "./NavBar";
import CryptoService from "../repository/cryptoTradingRepository"

export default function AllTransactions(){

    const [notes, setNotes] = useState([]);


    useEffect( () => {
        CryptoService.allTransactions().then(
            (response) => {
                const allNotes = response.data.data;
                setNotes(allNotes)
            }
        )
        .catch(error => console.error(`Error: ${error}`))
        
    }, []);

    return(
        <div>
            <NavBar/>
            
        </div>
    )

}