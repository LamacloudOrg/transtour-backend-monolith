import React, { FC, MouseEvent, useState } from 'react';
import { Col, Form, Row } from 'react-bootstrap';



const Taxes:FC = ()=> {

    return(
        <>
        <Row>
         <Form.Group  controlId="formMount" as={Col}>
          <Form.Label>monto</Form.Label>
          <Form.Control type='number'/>
        </Form.Group>

        <Form.Group  controlId="formParking" as={Col}>
          <Form.Label>estacionamiento</Form.Label>
          <Form.Control type='number'/>
        </Form.Group>

        <Form.Group  controlId="formReturn" as={Col}>
          <Form.Label>costo vuelta</Form.Label>
          <Form.Control type='number'/>
        </Form.Group>

        </Row>

        <Row>
        <Form.Group  controlId="formTotal" as={Col}>
          <Form.Label>total</Form.Label>
          <Form.Control type='number' />
        </Form.Group>
        </Row>
        </>
    )
}

export default Taxes;