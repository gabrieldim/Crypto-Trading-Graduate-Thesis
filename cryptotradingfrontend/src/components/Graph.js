import React, { useEffect, useState } from "react";
import CryptoService from "../repository/cryptoTradingRepository"
import { Line } from 'react-chartjs-2';
import { Chart as ChartJS, Title, Tooltip, LineElement, Legend, CategoryScale, LinearScale, PointElement} from "chart.js"

ChartJS.register(
    Title, Tooltip, LineElement, Legend, CategoryScale, LinearScale, PointElement
)

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

    console.log("test" + notes.data)


    const [data, setData] = useState({
        labels:["1h", "55min", "50min", "45min", "40min", "35min", "30min", "25min", "20min", "15min", "10min", "5min", "now"],
        datasets:[
            {
                label: "Crypto Name",
                data : [23,20,30,42,51,82,31,59,61,93,12,18,23],
                backgroundColor: 'yellow',
                borderColor: 'green'
            }
        ]
     });



    return(
        <>
        <div style={{margin:"2%"}}><b>Select Crypto:    </b> 
            <select>
            <option selected value="BTC">BTC</option>
                {symbol.map(s => (
                    <option value={s}>{s}</option>
                ))}                
            </select>
        </div>


            <div style={{width:"600px", height:"600px"}}>
                <Line data={data}></Line>
            </div>
        </>
    )
    
  }

