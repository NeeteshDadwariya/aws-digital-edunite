import React, { Component } from 'react';
import axios from "axios"; 

export default class SubmitForm extends React.Component {
  state = {
    name: '',
    street: '',
    city: '',
    postal:'',
    availability: ''
  }

  handleNameChange = event => {
    this.setState({ name: event.target.value });
  }

  handleStreetChange = event => {
    this.setState({ street: event.target.value });
  }

  handleCityChange = event => {
    this.setState({ city: event.target.value });
  }

  handleAvailabilityChange = event => {
    this.setState({ postal: event.target.value });
  }

  handlePostalChange = event => {
    this.setState({ availability: event.target.value });
  }

  handleSubmit = event => {
    event.preventDefault();

    const user = {
      name: this.state.name,
      street: this.state.street,
      city: this.state.city,
      postal: this.state.postal,
      availability: this.state.availability
    };

    axios.post(`https://jsonplaceholder.typicode.com/users`, { user })
      .then(res => {
        console.log(res);
        console.log(res.data);
      })
  }

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <label>
            Street No.:
            <input type="text" name="name" onChange={this.handleNameChange} />
          </label>
            <br/> <br/>
          <label>
            Street Name:
            <input type="text" name="street" onChange={this.handleStreetChange} />
          </label>
          <br/> <br/>
          <label>
            City:
            <input type="text" name="city" onChange={this.handleCityChange} />
          </label>
          <br/> <br/>
          <label>
            Postal Code:
            <input type="text" name="postal" onChange={this.handlePostalChange} />
          </label>
          <br/> <br/>
          <label>
            Availability:
            <input type="text" name="availability" onChange={this.handleAvailabilityChange} />
          </label>
          <br/> <br/>
          <button type="submit">Add</button>
        </form>
      </div>
    )
  }
}