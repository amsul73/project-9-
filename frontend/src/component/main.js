import React from 'react';
import logo from '../logo.svg';
import '../App.css';

function Main(props) {
    return (
        <div className='App'>
            <p>Main</p>
            <img src={logo} className="App-logo" alt="logo" />
        </div>
    );
}

export default Main;