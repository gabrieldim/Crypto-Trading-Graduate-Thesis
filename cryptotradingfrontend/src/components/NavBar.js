import React from 'react';
import {
  Nav,
  NavLink,
  Bars,
  NavMenu,
  NavBtn,
  NavBtnLink
} from './NavBarElements';

const NavBar = () => {
  return (
    <>
      <Nav>
        <NavLink to='/home'>
          <img src={require('../images/logo.png')} style={{height:"80px"}} alt='logo' />
        </NavLink>
        <Bars />
        <NavMenu>
          <NavLink to='/myCrypto'>
           <b> My Cryptocurrencies </b>
          </NavLink>
          <NavLink to='/myTransactions'>
          <b> My Transactions </b>
          </NavLink>
          <NavLink to='/allTransactions'>
          <b> All Transactions </b>
          </NavLink>

        </NavMenu>
        <NavBtn>
          <NavBtnLink to='/logout'>Log out</NavBtnLink>
        </NavBtn>
      </Nav>
    </>
  );
};

export default NavBar;