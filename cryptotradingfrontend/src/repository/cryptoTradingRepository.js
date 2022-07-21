import axios from '../custom-axios/axios';

const cryptoTradingRepository = {

    login: (username, password) => {
        return axios.post("/api/login", {
            "username": username,
            "password": password
        });
    },
    register: (username, password,repeatPassword,firstName,lastName,role) => {
        return axios.post("/register", {
            "username": username,
            "password": password,
            "repeatPassword" : repeatPassword,
            "firstName" : firstName,
            "lastName" : lastName,
            "role" : role
        });
    },
    allCrypto: () => {
      return axios.get("/api/crypto");
    }

}
export default cryptoTradingRepository;