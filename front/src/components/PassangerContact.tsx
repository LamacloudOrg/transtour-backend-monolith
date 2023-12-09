import React, { FC, MouseEvent, useState } from 'react';
import { Col, Form,InputGroup,Row } from 'react-bootstrap';
import { EyeSlash } from 'react-bootstrap-icons';
import CustomModal from './CustomModal';
import Passanger from './Passanger';


const PassangerContact:FC = ()=> {
    const [show,setShow] = useState(false);

    const handleShowModal = () :void=> {
       setShow(!show);
    }

    const title ="Contact Information";


    return(
        <Form.Group>
            <InputGroup.Text id="basic-addon1">
            <EyeSlash  onClick={handleShowModal}/> 
            </InputGroup.Text >
            <CustomModal openModal={show} title={title} key={1}>
                <Passanger />
            </CustomModal>
          </Form.Group>
    )

}

export default PassangerContact;