import React, { FC, MouseEvent, useState } from 'react';
import { Col, Form,InputGroup,Row } from 'react-bootstrap';
import { EyeSlash } from 'react-bootstrap-icons';
import CustomModal from './CustomModal';
import Address from './Adrress';


const AddressOrigin:FC = ()=> {
    const [show,setShow] = useState(false);

    const handleShowModal = () :void=> {
       setShow(!show);
    }

    const title = "Origin";


    return(
        <Form.Group>
            <InputGroup.Text id="basic-addon1">
            <EyeSlash  onClick={handleShowModal}/> 
            </InputGroup.Text >
            <CustomModal openModal={show} title={title} key={1}>
                <Address />
            </CustomModal>
        </Form.Group>
    )

}

export default AddressOrigin;