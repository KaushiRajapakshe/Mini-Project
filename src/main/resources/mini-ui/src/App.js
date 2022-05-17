// Application related Mini project main react ui
import React from 'react';
import './App.css';
import { BrowserRouter as Router } from 'react-router-dom';

import Header from './Components/Header.js';
import Footer from './Components/Footer.js';

function App() {
  return (
    <Router>
      <div className="main">
        <div className="App">
          <Header />
          <section className="line"></section>
          <Footer />
        </div>
      </div>
    </Router>
  );
}

export default App;
