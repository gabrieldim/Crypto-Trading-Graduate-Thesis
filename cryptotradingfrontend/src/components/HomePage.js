import React from "react";
import Graph from "./Graph";
import CashChanges from "./CashChanges"

export default function HomePage(){

    return(
        <>
            
            <Graph/>

            {/* buy, sell, add available resources, show current owned data */}
            <CashChanges/>
            



        </>
    );
}