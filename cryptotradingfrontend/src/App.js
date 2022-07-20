import React from "react";
import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import Login from './login/login';
import Register from "./register/register";


function App() {
  return (
    <BrowserRouter>
        <Routes>
            <Route exact path='/register' element={<Register/>}/>
            <Route exact path='/login' element={<Login/>}/>
            <Route exact path='/home' element={( <div><h2 style={{color:"red"}}>test</h2></div>)}/>
        </Routes>
    </BrowserRouter>
  );
}

export default App;
