import axios from "axios";

const instance = axios.create({
    baseURL: 'http://localhost:8091',
    headers: {
        'Access-Control-Allow-Origin' : '*',
        'Authorization': localStorage.getItem("JWT"),
        responseType: 'blob'
    }
})

export default instance;
