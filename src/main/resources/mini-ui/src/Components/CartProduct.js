// Mini project CartProduct react ui
import React, { Component } from 'react';
import axios from 'axios';
import { BrowserRouter as Router } from 'react-router-dom';
import Category from './Category.js';
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

// React CardProduct function
class CardProduct extends Component {

    // Initialize state values 
    state = {
        data: '',
        product: [],
        type: 'cartProduct'
    }
    componentWillMount() {
        this.loadData();
    }

    loadData = () => {
        api.get("/cart/1")
            .then(res => {
                this.setState({
                    data: res.data,
                    product: res.data.cardProduct
                })
            })
            .catch(error => {
                console.log("Error")
            })
    }

    render() {
        return (
            <Router>
                <div className="container" >
                    <h2 className="center">Cart Products</h2>
                    <div style={{ display: "flex", justifyContent: "flex-end", marginRight: "30px" }}>
                     <Create variant="contained" color="primary" style={{ margin: '20px', textAlign: "right", marginLeft: "50px" }} ><b style={{ color: 'white', fontSize: "1.075rem" }}> +  </b> Add product to cart</Create>
                     </div>
                    <div className="box">
                        {this.state.product.length !== 0 ? this.state.product.map(item => item.active === true ? <Category item={item} type={this.state.type} cart={this.state.data} /> : '') : ''}
                    </div>
                </div>
            </Router>
        );
    }
}

export default CardProduct;