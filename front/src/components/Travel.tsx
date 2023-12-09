import React, { FC, MouseEvent, useState } from 'react';
import { Button, Col, Form,InputGroup,Row } from 'react-bootstrap';
import AddressOrigin from './AddressOrigin';
import AddressDestiny from './AddressDestiny';
import PassangerContact from './PassangerContact';
import Taxes from './Taxes';




const Travel:FC = ()=> {

    const [date, setDate] = useState<any>(new Date());

    return(
        <Form>

            <Row className="mb-3">
                <AddressOrigin />
            </Row>
            <Row className="mb-3">
                <AddressDestiny />
            </Row>
            <Row className="mb-3">
                <PassangerContact/>
            </Row>

            <Row className="mb-3">
                <Taxes/>
            </Row>
            <Row className="mb-3">
            <Button variant="secondary">Close</Button>
            <Button variant="primary">Save changes</Button>
            </Row>
        </Form>
    );
}


export default Travel;