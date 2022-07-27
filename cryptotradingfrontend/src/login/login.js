import React from 'react';
import { useNavigate } from 'react-router-dom';
import CryptoService from "../repository/cryptoTradingRepository"

const Login = (props) => {

    const history = useNavigate();
    const [formData, updateFormData] = React.useState({
        username: "",
        password: ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        CryptoService.login(formData.username, formData.password).then(resp => {
            localStorage.setItem("JWT", resp.data);
            history("/home");
        })

    }

    return (
        <div style={{marginLeft:"35%",marginTop:"1%"}}>
            <div className="col-md-5">
            <h2>Login Form</h2> 
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name"><b>Username</b></label>
                        <input type="text"
                               className="form-control"
                               name="username"
                               required
                               placeholder="Enter username"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price"><b>Password</b></label>
                        <input type="password"
                               className="form-control"
                               name="password"
                               placeholder="Enter password"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div style={{marginTop:"2%"}}>
                        <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Login;
