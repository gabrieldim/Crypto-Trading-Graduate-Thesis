import axios from '../custom-axios/axios';

const cryptoTradingRepository = {

    login: (username, password) => {
        return axios.post("/login", {
            "username": username,
            "password": password
        });
    }

}
export default cryptoTradingRepository;