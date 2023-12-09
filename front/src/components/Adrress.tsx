import React, { FC, MouseEvent, useState } from 'react';
import { Col, Form,Row } from 'react-bootstrap';


const Address:FC = ()=> {

    return(
        <>

        <Form.Group  controlId="formGridState">
          <Form.Label>State</Form.Label>
          <Form.Select defaultValue="Choose...">
            <option>Choose...</option>
            <option>Bueno Aires</option>
            <option>Santafe</option>
          </Form.Select>
        </Form.Group>

        <Form.Group  controlId="formGridCity">
          <Form.Label>City</Form.Label>
          <Form.Select defaultValue="Choose...">
            <option>Choose...</option>
            <option>La Plata</option>
            <option>Rosario</option>
          </Form.Select>
        </Form.Group>

        <Form.Group  controlId="formGridZip">
          <Form.Label>Zip</Form.Label>
          <Form.Control />
        </Form.Group>

        <Form.Group >
            <Form.Label>Street</Form.Label>
            <Form.Control></Form.Control>    
        </Form.Group> 

        <Form.Group >
            <Form.Label>More</Form.Label>
            <Form.Control></Form.Control>
        </Form.Group>
     
      </>
    );
}


export default Address;