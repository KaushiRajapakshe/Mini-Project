import React, { Component } from 'react';
import axios from 'axios';
import { BrowserRouter as Router } from 'react-router-dom';
import Category from './Category.js';
import Search from '@material-ui/icons/Search';

// Mini project productlist api base url
const api = axios.create({
    baseURL: `http://localhost:8080/api/v1/mini/`
})

// React home function
class Home extends Component {

    // Initialize state values 
    state = {
        data: '',
        type: '',
        cart: []
    }
    componentWillMount() {
        api.get("/category/all")
            .then(res => {
                this.setState({
                    data: res.data,
                    type: 'category',
                    cart: []
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
                        data: res.data,
                        type: 'product'
                    });
                })
                .catch(error => {
                    console.log("Error")
                })
            api.get("/cart/all")
                .then(res => {
                    this.setState({
                        data: this.state.data,
                        type: 'category',
                        cart: res.data
                    })
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
                    <h2 className="center">{this.state.type == 'product' ? "Related Product" : "Product Categories"}</h2>
                    <div className="discount-box">
                        <div className='search'>
                            <Search fontSize="large"></Search>
                            <input className='search-box'
                                type="text"
                                id="header-search"
                                placeholder="Search product"
                                name="s"
                                onKeyDown={this.handleSearch}
                            />
                        </div>
                    </div>
                    <div className="box">
                        {this.state.data.length !== 0 ? this.state.data.map(item => item.active === true ? <Category item={item} type={this.state.type} cart={this.state.cart} /> : '') : ''}
                    </div>
                </div>
            </Router>
        );
    }
}

export default Home;