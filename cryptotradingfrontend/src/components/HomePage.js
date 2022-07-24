import React from "react";
import Graph from "./Graph";
import CashChanges from "./CashChanges"
import NavBar from "./NavBar";

export default function HomePage(){

    return(
        <>
            <NavBar/>


            {/*TODO: */}
            <Graph/>

            {/* buy, sell, add available resources, show current owned data */}
            <CashChanges/>
            



        </>
    );
}