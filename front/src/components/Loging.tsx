
import React, { FC, MouseEvent, useState } from 'react';

import { Button, Form, InputGroup, Row } from "react-bootstrap";
import { EyeSlash } from 'react-bootstrap-icons';
import { Spinner } from './Spinner';
import { usePostApi } from '../hooks/usePostApí';


const Login:FC = ()=> {

  const [passwordType, setPasswordType] = useState("password");
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [authenticated, setAuthenticated] = useState<null|boolean>(null);

    
  const togglePassword =()=>{
    passwordType==="password" ? setPasswordType("text") : setPasswordType("password")
   
  }

  const reset: ReturnType<typeof setTimeout> = setTimeout(() => {
    setAuthenticated(null);
    setLoading(false);
  }, 1000);

  const handleSubmit =  (e: MouseEvent<HTMLButtonElement>)=> {
    e.preventDefault();
    setLoading(true);
    const loginInfo = {
      'dni':userName,
      'password':password
    }
    
    usePostApi('/api/v1/oauth',loginInfo,{}).then(()=>{
      setLoading(false);
      setAuthenticated(true);
     
    }).catch(({})=>{
      setAuthenticated(false);
      reset;
    //setError(true);
   });
   

  }

  return (
    <>
    
    <Form>

      <Form.Group>
      <Form.Label>Username</Form.Label>
      <InputGroup className="mb-3">
    
      <Form.Control
        placeholder="Username"
        aria-label="Username"
        aria-describedby="basic-addon1"
        type="text"
        value={userName}
        onChange={(e)=>setUserName(e.target.value)}
        
      />

    </InputGroup>
    </Form.Group>

    <Form.Group>
    <Form.Label>Password</Form.Label>
      <InputGroup className="mb-3">
    
      <Form.Control
        placeholder="password"
        aria-label="password"
        aria-describedby="basic-addon1"
        type={passwordType}
        value={password}
        onChange={(e)=>setPassword(e.target.value)}
        
      />
        <InputGroup.Text id="basic-addon1">
          <EyeSlash  onClick={togglePassword}/> 
        </InputGroup.Text>
      </InputGroup>
    </Form.Group>


    <Form.Group>
        <Button type="button" variant="primary" onClick={handleSubmit}>Submit</Button>
    </Form.Group>

    </Form>

    {loading &&
     <Spinner/>  
    
    }
    {
      authenticated === true && <InputGroup.Text id="basic-addon1">Se logeo correctamente</InputGroup.Text>
    }

    {
      authenticated === false && <InputGroup.Text id="basic-addon1">Se logeo Incorrectamente</InputGroup.Text>
    }

   
    </>
  );
};

export default Login;