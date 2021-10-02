import React, { Component } from 'react';
import styled from "@emotion/styled";
import axios from "axios"; 
const Container = styled.div`
  margin-top: 150px;
`;
class SubmitForm extends Component {
  state = {
    name: '',
  };
/* This is where the magic happens 
*/
handleSubmit = event => {
    event.preventDefault();
    const user = {
      name: this.state.name
    }
    axios.post('https://jsonplaceholder.typicode.com/users', { user })
      .then(res=>{
        console.log(res);
        console.log(res.data);
        window.location = "/retrieve" //This line of code will redirect you once the submission is succeed
      })
  }
handleChange = event =>{
    this.setState({ name: event.target.value});
  }
render() {
    return (
      <Container>
        
        <form onSubmit = { this.handleSubmit }>
          <label> Person Name:
            <input type = "text" name = "name" onChange= {this.handleChange}/>
          </label>
          <button type = "submit"> Add </button>
        </form>
    </Container>
    );
  }
}
export default SubmitForm;