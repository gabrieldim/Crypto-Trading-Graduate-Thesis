import React from "react";
import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';

export default function SellCrypto() {
    function greetUser() {
        console.log("Hi there, user!");
      }
    return (
        <div onClick={greetUser()} style={{marginLeft:"17%", marginTop: "-4.4%"}}>
          <Button onClick={greetUser} variant="danger">Sell Crypto</Button>
        </div>
      );
}
