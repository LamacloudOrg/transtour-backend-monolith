
import {AxiosRequestConfig,AxiosResponse} from "axios";
import { useEffect, useState } from "react";
import Axios from "./Axios";

export function useGetApi<T>(url:string,options?:AxiosRequestConfig):{loading:boolean,data:T|null,error:any} {
    const [loading, setLoading] = useState(true);
    const [data, setData] = useState<T|null>(null);
    const [error,setError]= useState<any>(null);

    const fetchData= async ()=>{
        setLoading(true);

        try{
            const response:AxiosResponse<T> = await Axios.get(url,options)
            setData(response.data);
        }catch(error){
            setError(error);
        }

        setLoading(false);

    }

    useEffect(()=>{
        fetchData()
    },[]);

    return {loading,data,error}
 }