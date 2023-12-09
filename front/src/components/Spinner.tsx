import styled, { keyframes } from 'styled-components'

const rotate = keyframes`
  from {
    transform :rotate(0deg);
  }
  to {
    transform :rotate(360deg);
  }
`
const Spinner = styled.div`
  z-index:5000;
  opacity:0.80;
  max-width:50px;
  margin-left: 200px;
  width:50px;
  height:50px;
  border: 12px solid black;
  border-top: 12px solid grey;
  border-radius: 50%;
  animation-name: ${rotate};
  animation-duration: 1s;
  animation-timing-function: ease;
  animation-delay: 1s;
  animation-play-state: running;
  animation-iteration-count: infinite;

`

export { Spinner }