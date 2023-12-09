
import {AxiosRequestConfig} from "axios";
import Axios from "./Axios";
import { useEffect, useState } from "react";

export function usePostApi(
    url:string,
    data:any|null,
    options?:AxiosRequestConfig,

    ):Promise<{success:boolean,error:any}>{

    
    return new Promise<any>(async (res, rej) => {
        setTimeout(()=>{},2000);
        try {
            await Axios.post(url, data, options);
            res({sucess:true,error:null});

        } catch (error) {
            rej({sucess:false,error:null});
        }

    });

 }
