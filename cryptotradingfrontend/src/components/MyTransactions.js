import React, { useEffect, useState } from "react";
import NavBar from "./NavBar";
import CryptoService from "../repository/cryptoTradingRepository"

export default function MyTransactions(){

    const [notes, setNotes] = useState([]);


    useEffect( () => {
        CryptoService.loggedUserTransactions().then(
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
            <h2>test2</h2>
        </div>
    )

}