
import React, { FC, MouseEvent, useState } from 'react';

import PropTypes, { InferProps } from 'prop-types';
import { Col, Container, Row } from 'react-bootstrap';
import NavBar from './NavBar';
import Footer from './Footer';

const propTypes = {
    children: PropTypes.node.isRequired,
  };
  
  export type Props = InferProps<typeof propTypes> & {

  };


const Page:FC<Props> = ({children}) => {

    return (
        <Container fluid>


            <Row>
            <Col md={2} xs={2}>
                <NavBar  />
            </Col>

            <Col md={8} xs={8}>
                {children}
            </Col>

            </Row>

            <Footer>
                <p>contact: transtourbsas@gmail.com</p>
            </Footer>

        </Container>


    )

}

export default Page;
