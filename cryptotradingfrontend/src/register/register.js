import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import CryptoService from "../repository/cryptoTradingRepository"
import '../style/style.css'

const Register = (props) => {

    const navigate = useNavigate();

    const [error, setErrors] = useState([]);

    const [formData, updateFormData] = React.useState({
        username: "",
        password: "",
        repeatPassword: "",
        firstName: "",
        lastName: "",
        role: "",
        creditCardNumbers: ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        setErrors([])
        CryptoService.register(formData.username, formData.password, formData.repeatPassword, formData.firstName, formData.lastName, formData.role, formData.creditCardNumbers).then(resp => {
            console.log(resp)
            navigate('/login');
        })
        .catch(error => {
            setErrors(error.response.data.error + " - Check the credentials, otherwise the username is already taken!")
          });

    }
    console.log(formData.creditCardNumbers)
    return (
        <div style={{marginLeft:"35%",marginTop:"1%"}}>
            <div className="col-md-5 center">
                <h2>Welcome To Block Trader</h2>
                <h2 style={{marginLeft:"35%"}}><img src={require('../images/logo.png')} style={{height:"100px"}} alt='logo' /></h2> 
                <h2>Register Form</h2>    
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name"><b>Username </b></label>
                        <input type="text"
                               className="form-control"
                               name="username"
                               required
                               placeholder="Enter username"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group space">
                        <label htmlFor="price"><b>Password</b></label>
                        <input type="password"
                               className="form-control"
                               name="password"
                               placeholder="Enter password"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group space">
                        <label htmlFor="price"><b>Repeat Password</b></label>
                        <input type="password"
                               className="form-control"
                               name="repeatPassword"
                               placeholder="Enter same password"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group space">
                        <label htmlFor="price"><b>Firstname</b></label>
                        <input type="text"
                               className="form-control"
                               name="firstName"
                               placeholder="Enter firstname"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group space">
                        <label htmlFor="price"><b>Lastname</b></label>
                        <input type="text"
                               className="form-control"
                               name="lastName"
                               placeholder="Enter lastname"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group space">
                        <label htmlFor="price"><b>Credit Card</b></label>
                        <input type="text"
                               className="form-control"
                               name="creditCardNumbers"
                               placeholder="Enter credit card data"
                               required
                               maxLength={16}
                               minLength={16}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group space">
                        <label htmlFor="price"><b>Role</b></label>
                        <input type="text"
                               className="form-control"
                               name="role"
                               placeholder="Enter Role - ROLE_ADMIN/USER"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary space"><b>Submit</b></button>
                    <div style={{color:"red"}}>{error}</div>
                </form>
            </div>
            <div style={{marginBottom:"30px"}}>
                <hr></hr>
               <b> Have account? </b> <a href='/login' style={{color:"lightBlue", margin:"20px"}}><b>Sign in here</b></a>
            </div>
            
        </div>
    )
}

export default Register;
