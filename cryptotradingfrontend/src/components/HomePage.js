import React from "react";
import Graph from "./Graph";
import CashChanges from "./CashChanges"
import NavBar from "./NavBar";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

export default function HomePage(){

    return(
        <>
            <NavBar/>

            
            <Container>
                <Row>
                     <Col><Graph/></Col>
                     <Col><CashChanges/></Col>
                 </Row>
            </Container>

        </>
    );
}