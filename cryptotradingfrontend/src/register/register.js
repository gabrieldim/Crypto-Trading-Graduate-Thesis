import React from 'react';
import { useNavigate } from 'react-router-dom';
import CryptoService from "../repository/cryptoTradingRepository"
import '../style/style.css'
import '../index.css'

const Register = (props) => {

    const navigate = useNavigate();

    const [formData, updateFormData] = React.useState({
        username: "",
        password: "",
        repeatPassword: "",
        firstName: "",
        lastName: "",
        role: ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        CryptoService.register(formData.username, formData.password, formData.repeatPassword, formData.firstName, formData.lastName, formData.role).then(resp => {
            console.log(resp)
            // props.onLogin()
            navigate('/login');
        })

    }

    return (
        <div className="row mt-5 login-form">
            <div className="col-md-5 center">
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
                        <label htmlFor="price"><b>Role</b></label>
                        <input type="text"
                               className="form-control"
                               name="role"
                               placeholder="Enter Role"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary space"><b>Submit</b></button>
                </form>
            </div>
            <div>
                <hr></hr>
                Have account? <a href='/login' style={{color:"lightBlue"}}>Sign in here</a>
            </div>
        </div>
    )
}

export default Register;
