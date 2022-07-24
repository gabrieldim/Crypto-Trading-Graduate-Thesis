import React from "react";
import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import Login from './login/login';
import Register from "./register/register";
import HomePage from "./components/HomePage";
import MyCrypto from "./components/MyCrypto";
import MyTransactions from "./components/MyTransactions";
import AllTransactions from "./components/AllTransactions";

function App() {
  return (
    <BrowserRouter>
        <Routes>
            <Route exact path='/register' element={<Register/>}/>
            <Route exact path='/login' element={<Login/>}/>
            <Route exact path='/home' element={<HomePage/>}/>
            <Route exact path='/myCrypto' element={<MyCrypto/>}/>
            <Route exact path='/myTransactions' element={<MyTransactions/>}/>
            <Route exact path='/allTransactions' element={<AllTransactions/>}/>
        </Routes>
    </BrowserRouter>
  );
}

export default App;
