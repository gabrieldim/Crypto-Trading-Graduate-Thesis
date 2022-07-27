import React, { useEffect, useState } from "react";
import NavBar from "./NavBar";
import CryptoService from "../repository/cryptoTradingRepository"

export default function MyCrypto(){

    const [notes, setNotes] = useState([]);
    const [user, setUser] = useState("");

    useEffect( () => {
        CryptoService.userOwnedCrypto().then(
            (response) => {
                const allNotes = response.data;
                setNotes(allNotes)
            }
        )
        .catch(error => console.error(`Error: ${error}`))
        
    }, []);

    useEffect( () => {
        CryptoService.getLoggedUser().then(
            (response) => {
                const loggedUser = response.data;
                setUser(loggedUser)
            }
        )
        .catch(error => console.error(`Error: ${error}`))
        
    }, []);

    console.log(notes)

    return(
        <div>
            <NavBar/>
            <div style={{margin:"2%", fontSize:"18px"}}>
                Current logged user - <b> {user} </b> <br/>
                Cryptocurrencies owned by the given user:
            </div>

            <div>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th style={{color:"purple"}}>Currency Name</th>
                        <th style={{color:"purple"}}>Currency Amount USD</th>
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