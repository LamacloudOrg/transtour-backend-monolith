import React, { Component, FC, MouseEvent, useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import PropTypes, { InferProps } from 'prop-types';


const CModal = {
  backgroundColor: 'rgba(255, 255, 255, 0.85)',
  position: 'initial',
  bottom: '2rem',
  padding: '0.5rem',
  fontFamily: 'sans-serif',
  fontSize: '1.5rem',
  boxShadow: '0 0 10px rgba(0, 0, 0, 0.3)',
  width: '20rem',
  height:'30rem'
};


const propTypes = {
    openModal: PropTypes.bool.isRequired,
    children: PropTypes.node.isRequired,
    title:PropTypes.string.isRequired,
  };
  
  export type Props = InferProps<typeof propTypes> & {
    openModal: boolean,
    title:string
  };
  

const CustomModal:FC<Props> = ({children,openModal,title}): JSX.Element => {
  const[modalTitle , setTitle] = useState("");

  useEffect(()=>{
    setTitle(title);
},[]);


    return(
        <div
        className="modal show"
        style={{ display: 'block', position: 'initial' }}
      >
        <Modal show={openModal}>
          <Modal.Header closeButton>
            <Modal.Title>{modalTitle} </Modal.Title>
          </Modal.Header>
  
          <Modal.Body>
                {children}
          </Modal.Body>
  
          <Modal.Footer>
            <Button variant="secondary">Close</Button>
            <Button variant="primary">Save changes</Button>
          </Modal.Footer>
        </Modal>
      </div>
    );
}


export default CustomModal;