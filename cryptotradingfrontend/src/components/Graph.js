import React, { useEffect, useState } from "react";
import CryptoService from "../repository/cryptoTradingRepository"

export default function Graph() {

    const [notes, setNotes] = useState([]);


    useEffect( () => {
        CryptoService.allCrypto().then(
            (response) => {
                const allNotes = response.data;
                setNotes(allNotes)
            }
        )
        .catch(error => console.error(`Error: ${error}`))
        
    }, []);

    return(
        <>
            
        </>
    )
    
  }

