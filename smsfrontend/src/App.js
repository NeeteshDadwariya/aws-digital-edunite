import React, { Component } from 'react';
import './App.css';
import SubmitForm from './SubmitForm';
import {Figure} from 'react-bootstrap';


class App extends Component {
  render() {
    return (
        <div>
        <div align="center" color="yellow" style={{color:'#FEDE00'}}>
            <h1 style={{fontSize:'50px'}}>EDUNITE</h1>
            <Figure align="center">
                <Figure.Image
                    width={250}
                    height={250}
                    src="icon.png"
                    align="center"
                />
            </Figure>
        </div>
        <div className="container">
          <div className="row">
            <h2>HERE Geocoder Autocomplete Validation</h2>
              <br/><br/><br/>
        </div>

        <SubmitForm />

      </div>
        </div>

    );
  }
}

export default App;