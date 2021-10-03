import React, { Component } from 'react';
import axios from "axios";
import {Button, Form, Col, Row} from 'react-bootstrap';

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
        <Form>
            <Form.Group as={Row}>
                <Form.Label column sm={2}>
                Street No.:
                </Form.Label>
                    <Col sm={2}>
                    <Form.Control type="text" name="name" placeHolder="Enter Street No." onChange={this.handleNameChange} />
                    </Col>
            </Form.Group>
            <br/>
            <Form.Group as={Row}>
                <Form.Label column sm={2}>
                    Street Name:
                </Form.Label>
                <Col sm={2}>
                    <Form.Control type="text" name="street" placeHolder="Enter Street Name" onChange={this.handleStreetChange} />
                </Col>
            </Form.Group>
            <br/>
            <Form.Group as={Row}>
                <Form.Label column sm={2}>
                    City:
                </Form.Label>
                <Col sm={2}>
                    <Form.Control type="text" name="city" placeHolder="Enter City Name" onChange={this.handleCityChange} />
                </Col>
            </Form.Group>
            <br/>
            <Form.Group as={Row}>
                <Form.Label column sm={2}>
                    Postal Code:
                </Form.Label>
                <Col sm={2}>
                    <Form.Control type="text" name="postal" placeHolder="Enter Postal Code" onChange={this.handlePostalChange} />
                </Col>
            </Form.Group>
            <br/>
            <Form.Group as={Row}>
                <Form.Label column sm={2}>
                    Availability:
                </Form.Label>
                <Col sm={2}>
                    <Form.Control type="text" name="availability" placeHolder="Enter free systems" onChange={this.handleAvailabilityChange} />
                </Col>
            </Form.Group>
            <br/>
            <Col sm={2}>
                <Button variant="dark" type="submit">Add</Button>
            </Col>
        </Form>
    )
  }
}