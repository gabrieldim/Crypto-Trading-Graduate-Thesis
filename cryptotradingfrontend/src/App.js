import React from "react";
import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import Login from './login/login';
import Register from "./register/register";
import HomePage from "./components/HomePage";

function App() {
  return (
    <BrowserRouter>
        <Routes>
            <Route exact path='/register' element={<Register/>}/>
            <Route exact path='/login' element={<Login/>}/>
            <Route exact path='/home' element={<HomePage/>}/>
        </Routes>
    </BrowserRouter>
  );
}

export default App;
