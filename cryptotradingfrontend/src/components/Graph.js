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
    const [selectedSymbol, setSelectedSymbol] = useState("BTC");
    
    const [cryptoName, setCryptoName] = useState("");
    const [marketCap, setMarketCap] = useState("");

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


    useEffect( () => {
        // setInterval(() => {
        // CryptoService.allCrypto("BTC").then(
        //     (response) => {
        //         const allNotes = response.data;
        //         setNotes(allNotes)
        //         setMarketCap(allNotes[12].marketCap)
        //         setCryptoName(allNotes[12].name)
        //         setData({
        //             labels:["1h", "55min", "50min", "45min", "40min", "35min", "30min", "25min", "20min", "15min", "10min", "5min", "now"],
        //             datasets:[
        //                 {
        //                     label: "BTC",
        //                     data : allNotes.map(c => c.price),
        //                     backgroundColor: 'yellow',
        //                     borderColor: 'green'
        //                 }
        //             ]
        //          })
        //     }
        // )
        // .catch(error => console.error(`Error: ${error}`))
        // console.log("the graph is updated!")
    }/*, 300000)}, []*/); // on every 5 minutes make a api call to update the graph

    useEffect( () => {
        CryptoService.allCryptoSymbols().then(
            (response) => {
                const allNotes = response.data;
                setSymbol(allNotes)

            }
        )
        .catch(error => console.error(`Error: ${error}`))
        
    }, []);

    const handleChange = (event) => {
        setSelectedSymbol(event.target.value);
        CryptoService.allCrypto(event.target.value).then(
            (response) => {
                const allNotes = response.data;
                setNotes(allNotes)
                setMarketCap(allNotes[12].marketCap)
                setCryptoName(allNotes[12].name)
                setData({
                    labels:["1h", "55min", "50min", "45min", "40min", "35min", "30min", "25min", "20min", "15min", "10min", "5min", "now"],
                    datasets:[
                        {
                            label: event.target.value,
                            data : allNotes.map(c => c.price),
                            backgroundColor: 'yellow',
                            borderColor: 'green'
                        }
                    ]
                 })
            }
        )
        .catch(error => console.error(`Error: ${error}`))
      };

    console.log("notes: " + notes)

    return(
        <>
        <div style={{margin:"2%"}}><b>Select Crypto:    </b> 
            <select onClick={handleChange}>
            <option defaultValue="BTC">BTC</option>
                {symbol.map(s => (
                    <option value={s} key={s} >{s}</option>
                ))}                
            </select>
            <p>
                The results shown in the graph below are in <b>USD</b>.
            </p>
        </div>

            <div style={{width:"600px", height:"600px"}}>
                <Line data={data}></Line>
            </div>
            
            <div style={{marginTop:"-200px"}}>
               <h4> Cryptocurrency Name: <b> {cryptoName} </b></h4>
               <h4> Cryptocurrency Symbol: <b> {selectedSymbol} </b></h4>
               <h4> Market Cap: <b> {marketCap} </b></h4>
            </div>
        </>
    )
    
  }
