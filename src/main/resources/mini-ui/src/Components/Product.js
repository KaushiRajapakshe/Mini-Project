// Mini project Category react ui
import React, { Component } from 'react';
import { connect } from 'react-redux';
import { addToCart, addTotal } from './actions/cartActions';
import Button from '@material-ui/core/Button';

// React Product class
class Product extends Component {

    // Initialize state values 
    state = {
        data: '',
        item: [],
        type: '',
        value: 'single',
        textValue: 0,
        price: 0,
        changed: 'false',
        total: 0,
        count: 0,
        cart: []
    }

    // handle radio button change
    handleRadioChange = (event) => {
        this.setState({
            value: event.target.value,
            count: 0,
            textValue: 0,
            changed: 'false'
        })
    };

    getCalculatedPrice = (event) => {
        this.setState({
            total: this.state.count * this.props.item.productPrice
        })
        this.props.addTotal(this.props.item.id, this.state.total)
    }

    // handle add button change -> product count + 
    handleAddChange = (event) => {
        this.setState({
            count: (this.state.textValue >= 0 && this.props.item.productCount >= this.state.count + 50) ? this.state.textValue + 50 : this.props.item.productCount,
            textValue: this.props.item.productCount >= this.state.textValue + 50 ? this.state.textValue + 50 : this.props.item.productCount,
            changed: 'true',
            item: this.props.item
        }, () => {
            this.getCalculatedPrice();
            this.props.addToCart(this.props.item.id);
        });
    };

    // handle min button change -> product count -
    handleMinChange = (event) => {
        this.setState({
            count: this.state.textValue >= 100 ? this.state.textValue - 50 : 0,
            textValue: this.state.textValue <= 0 ? 0 : this.state.textValue - 50,
            changed: 'true',
            item: this.props.item
        }, () => {
            this.getCalculatedPrice();
            this.props.addToCart(this.props.item.id);
        });
    }

    render() {
        console.log(this.props.item)
        // returning react Category ui view for a product
        return (
            <div className="category" key={this.props.item.id}>
                <div className="category-image">
                    <img src={this.props.item.imgURL} alt={this.props.item.categoryName} style={{ width: '300px', height: '300px' }} /><br /><br />
                    <span className="category-title">{"Cart name : "} {this.props.cartName}</span><br /><br />
                    <span className="category-title">{"Product : "} {this.props.item.productName}</span>
                    <br /><br />
                    <div>
                        <span className="category-title">{"Product count : " + this.props.item.productCount + " " + this.props.item.productInfo} </span>
                        <br /><br />
                        <span className="category-title">{"Product price : $ " + this.props.item.productTotalPrice} </span>
                    </div><br />
                    <div style={{ display: "flex", justifyContent: "flex-end" }}>
                        <Button variant="contained" color="primary" style={{ margin: '20px', textAlign: "right", marginLeft: "50px", justifyContent: "flex-end" }} >Update Product</Button>
                    </div>
                    <br />
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        total: state.total
    }
}
const mapDispatchToProps = (dispatch) => {

    return {
        addToCart: (id) => { dispatch(addToCart(id)) },
        addTotal: (id, total) => { dispatch(addTotal(id, total)) }
    }
}

// export default (Product)
export default connect(mapStateToProps, mapDispatchToProps)(Product)