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
    },
    buyCrypto: (currencyName, amountToBuy) => {
        return axios.post("/api/buyCrypto", {
            "currencyName": currencyName,
            "amountToBuy": amountToBuy
        });
    },
    sellCrypto: (currencyName, amountToSell) => {
        console.log(currencyName + " " + amountToSell)
        return axios.post("/api/sellCrypto", {
            "currencyName": currencyName,
            "amountToSell": amountToSell
        });
    },
    addMoney: (deposit) => {
        return axios.post("/api/addMoney", {
            "deposit": deposit,
        });
    }
    

}
export default cryptoTradingRepository;