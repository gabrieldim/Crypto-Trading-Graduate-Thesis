import React, { useEffect, useState } from "react";
import NoteTimeline from "./NoteTimeline";
import CryptoService from "../repository/cryptoTradingRepository"

export default function Graph() {

    const [notes, setNotes] = useState([]);


    useEffect( () => {
        CryptoService.allCrypto().then(
            (response) => {
                const allNotes = response.data.data;
                setNotes(allNotes)
            }
        )
        .catch(error => console.error(`Error: ${error}`))
        
    }, []);

    return(
        <>
        <NoteTimeline notes={notes}/>
        </>
    )
    
  }

