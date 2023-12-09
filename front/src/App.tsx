import { useState } from 'react'
import Login from './components/Loging'
import { Button, Col, Container, Form, InputGroup, Row } from 'react-bootstrap'

import 'bootstrap/scss/bootstrap.scss';
import Travel from './components/Travel';
import NavBar from './components/NavBar';
import Page from './components/Page';






function App() {
  const [count, setCount] = useState(0)

  return (
    
          <Page>
            <Travel />
          </Page>
    
  )
}

export default App
