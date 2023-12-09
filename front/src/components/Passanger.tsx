import React, { FC, MouseEvent, useState } from 'react';
import { Form } from 'react-bootstrap';





const Passanger:FC = ()=> {


    return(

        <>
        <Form.Group  controlId="formGridName">
          <Form.Label>name</Form.Label>
          <Form.Control />
        </Form.Group>

        <Form.Group  controlId="formGridLastName">
          <Form.Label>lastName</Form.Label>
          <Form.Control />
        </Form.Group>

        <Form.Group  controlId="formGridPhone">
          <Form.Label>phone</Form.Label>
          <Form.Control />
        </Form.Group>

        <Form.Group  controlId="formGridEmail">
          <Form.Label>email</Form.Label>
          <Form.Control />
        </Form.Group>

        </>

    )

}


export default Passanger;