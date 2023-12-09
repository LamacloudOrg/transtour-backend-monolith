import { Navigate } from "react-router-dom";
import useAuth from "./UseAuth";

export default function RequireAuth({ children }:any) {
    const { authed }:any = useAuth();
  
    return authed === true ? children : <Navigate to="/login" replace />;
  }