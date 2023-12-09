import axios,{AxiosRequestConfig,AxiosResponse} from "axios";

const Axios = axios.create({
baseURL: "http://localhost:8080",
headers: {
'Content-type': 'application/json',
Accept: 'application/json',
},
});

export default Axios;