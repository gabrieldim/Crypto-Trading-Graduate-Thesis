import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import Login from './login/login';


function App() {
  return (
    <BrowserRouter>
        <Routes>
            <Route exact path='/login' element={<Login/>}/>
        </Routes>
    </BrowserRouter>
  );
}

export default App;
