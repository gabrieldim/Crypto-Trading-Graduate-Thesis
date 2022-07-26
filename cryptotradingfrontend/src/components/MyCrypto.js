import React, { useEffect, useState } from "react";
import NavBar from "./NavBar";
import CryptoService from "../repository/cryptoTradingRepository"

export default function MyCrypto(){

    const [notes, setNotes] = useState([]);


    useEffect( () => {
        CryptoService.userOwnedCrypto().then(
            (response) => {
                const allNotes = response.data;
                setNotes(allNotes)
            }
        )
        .catch(error => console.error(`Error: ${error}`))
        
    }, []);

    console.log(notes)

    return(
        <div>
            <NavBar/>
            <div>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th style={{color:"purple"}}>Currency Name</th>
                        <th style={{color:"purple"}}>Currency Amount</th>
                    </tr>
                </thead>
                <tbody>
                        
                        {notes.map(note => (
                            <tr key={note.id}>
                                <td>{note.currencyHeldName}</td>
                                <td>{note.currencyHeldAmount}</td>
                            </tr>
                        ))}
                </tbody>
            </table>
        </div>
        </div>
    )

}