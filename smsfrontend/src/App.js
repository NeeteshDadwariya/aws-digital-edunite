import React, { Component } from 'react';
import './App.css';
import SubmitForm from './SubmitForm';


class App extends Component {
  render() {
    return (

      <div className="container">
        <div className="row">
          <h2>HERE Geocoder Autocomplete Validation</h2>
        </div>

        <SubmitForm />

      </div>

    );
  }
}

export default App;