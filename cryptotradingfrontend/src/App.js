import React from "react";
import {
  BrowserRouter,
  Routes,
  Route,
  Navigate
} from "react-router-dom";
import Login from './login/login';
import Register from "./register/register";
import HomePage from "./components/HomePage";
import MyCrypto from "./components/MyCrypto";
import MyTransactions from "./components/MyTransactions";
import AllTransactions from "./components/AllTransactions";
import PageNotFound from "./components/PageNotFound";
import Balance from "./components/Balance";

function App() {
  return (
    <BrowserRouter>
        <Routes>
            <Route exact path='/' element={<Navigate to ="/register" />}/>
            <Route exact path='/register' element={<Register/>}/>
            <Route exact path='/login' element={<Login/>}/>
            <Route exact path='/home' element={<HomePage/>}/>
            <Route exact path='/myCrypto' element={<MyCrypto/>}/>
            <Route exact path='/myTransactions' element={<MyTransactions/>}/>
            <Route exact path='/allTransactions' element={<AllTransactions/>}/>
            <Route exact path='/balance' element={<Balance/>}/>
            <Route exact path='*' element={<PageNotFound/>}/>
        </Routes>
    </BrowserRouter>
  );
}

export default App;
