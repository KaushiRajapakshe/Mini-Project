import React, { Component } from 'react';
import axios from 'axios';
import { BrowserRouter as Router } from 'react-router-dom';
import Cart from './Cart.js';
import styled from "styled-components";

const Create = styled.button`
  background-color: #009900;
  color: #fff;
  padding: 6px 16px;
  font-size: 0.875rem;
  border-color: #009900;
  cursor: pointer;
  box-shadow: 0px 3px 1px -2px rgb(0 0 0 / 20%), 0px 2px 2px 0px rgb(0 0 0 / 14%), 0px 1px 5px 0px rgb(0 0 0 / 12%);
  min-width: 64px;
  box-sizing: border-box;
  transition: background-color 250ms cubic-bezier(0.4, 0, 0.2, 1) 0ms,box-shadow 250ms cubic-bezier(0.4, 0, 0.2, 1) 0ms,border 250ms cubic-bezier(0.4, 0, 0.2, 1) 0ms;
  font-family: "Roboto", "Helvetica", "Arial", sans-serif;
  font-weight: 500;
  line-height: 1.75;
  border-radius: 4px;
  letter-spacing: 0.02857em;
  text-transform: uppercase;
  border-width: 1px;
  border-style: solid;
  border-image: initial;
`;

// Mini project productlist api base url
const api = axios.create({
    baseURL: `http://localhost:8080/api/v1/mini/`
})

// React Wishlist function
class Wishlist extends Component {

    // Initialize state values 
    state = {
        data: ''
    }
    componentWillMount() {
        api.get("/cart/all")
            .then(res => {
                this.setState({
                    data: res.data
                })
            })
            .catch(error => {
                console.log("Error")
            })
    }

    handleSearch = (event) => {
        if (event.code === 'Enter') {
            api.get("/product/name/" + event.target.value)
                .then(res => {
                    this.setState({
                        data: res.data
                    }, () => {
                        // this.props.addTotal(this.props.item.id, this.state.data.totPurchaseAmount)
                    });
                })
                .catch(error => {
                    console.log("Error")
                })
        } else {
            this.componentWillMount();
        }
    }

    render() {
        return (

            <Router>
                <div className="container" >
                    <h2 className="center">Cart List</h2>
                    <div style={{ display: "flex", justifyContent: "flex-end", marginRight: "30px" }}>
                    <Create variant="contained" color="primary" style={{ margin: '20px', textAlign: "right", marginLeft: "50px" }} ><b style={{ color: 'white', fontSize: "1.075rem" }}> +  </b> Create cart</Create>
                    </div>
                    <div className="box">
                        {this.state.data.length !== 0 ? this.state.data.map(item => item.active === true ? <Cart item={item} /> : '') : ''}
                    </div>
                </div>
            </Router>
        );
    }
}


export default Wishlist;