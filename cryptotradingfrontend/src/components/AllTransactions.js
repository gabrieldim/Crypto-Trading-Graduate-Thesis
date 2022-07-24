import React, { useEffect, useState } from "react";
import NavBar from "./NavBar";
import CryptoService from "../repository/cryptoTradingRepository"

export default function AllTransactions(){

    const [notes, setNotes] = useState([]);


    useEffect( () => {
        CryptoService.allTransactions().then(
            (response) => {
                const allNotes = response.data;
                setNotes(allNotes)
            }
        )
        .catch(error => console.error(`Error: ${error}`))
        
    }, []);

    return(
        <div>
            <NavBar/>
            
            <div>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th style={{color:"purple"}}>Transaction Number</th>
                        <th style={{color:"purple"}}>Traded Crypto</th>
                        <th style={{color:"purple"}}>Crypto Amount</th>
                        <th style={{color:"purple"}}>Crypto USD</th>
                        <th style={{color:"purple"}}>Transaction Type</th>
                        <th style={{color:"purple"}}>Date</th>
                    </tr>
                </thead>
                <tbody>
                        
                        {notes.map(note => (
                            <tr key={note.id}>
                                <td>{note.id}</td>
                                <td>{note.tradedCryptoName}</td>
                                <td>{note.amountInCrypto}</td>
                                <td>{note.amountInUsd}</td>
                                <td>{note.transactionBoughtSold}</td>
                                <td>{note.date}</td>
                            </tr>
                        ))}
                </tbody>
            </table>
        </div>
        </div>
    )

}