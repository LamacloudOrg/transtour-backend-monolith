

import PropTypes, { InferProps } from 'prop-types';
import { Col, Container, Row } from 'react-bootstrap';
import NavBar from './NavBar';
import { FC } from 'react';

const propTypes = {
    children: PropTypes.node.isRequired,
  };
  
  export type Props = InferProps<typeof propTypes> & {

  };

const style:React.CSSProperties = {
    backgroundColor: "#F8F8F8",
    borderTop: "1px solid #E7E7E7",
    textAlign: "center",
    padding: "20px",
    position: "fixed",
    left: "0",
    bottom: "0",
    height: "60px",
    width: "100%",
};

const Footer:FC<Props> = ({children}) => {
        return (
            <Row style ={style}>
                {children}
            </Row>
        );
    }


export default Footer;