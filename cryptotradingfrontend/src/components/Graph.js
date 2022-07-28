import React, { useEffect, useState } from "react";
import CryptoService from "../repository/cryptoTradingRepository"

export default function Graph() {

    const [notes, setNotes] = useState([]);
    const [symbol, setSymbol] = useState([]);

    useEffect( () => {
        CryptoService.allCrypto().then(
            (response) => {
                const allNotes = response.data;
                setNotes(allNotes)

            }
        )
        .catch(error => console.error(`Error: ${error}`))
        
    }, []);

    useEffect( () => {
        CryptoService.allCryptoSymbols().then(
            (response) => {
                const allNotes = response.data;
                setSymbol(allNotes)

            }
        )
        .catch(error => console.error(`Error: ${error}`))
        
    }, []);

    console.log(notes.data)

    return(
        <>
            <select>
            <option selected value="BTC">BTC</option>
                {symbol.map(s => (
                    <option value={s}>{s}</option>
                ))}                
            </select>

        </>
    )
    
  }

