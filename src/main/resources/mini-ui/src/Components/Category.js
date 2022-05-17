// Mini project Category react ui
import React, { Component } from 'react';
import Button from '@material-ui/core/Button';
import AddBoxIcon from '@material-ui/icons/AddBox';
import IndeterminateCheckBoxIcon from '@material-ui/icons/IndeterminateCheckBox';
import TextField from '@material-ui/core/TextField';
import { connect } from 'react-redux';

// React Category class
class Category extends Component {

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
        cart: [],
        cloneCartProduct: []
    }

    componentWillMount() {
        this.setState({
            textValue: this.props.item.productCount === undefined ? 0 : this.props.item.productCount,
            count: this.props.item.productCount === undefined ? 0 : this.props.item.productCount,
            clonecartProduct: { ...this.props.item }
        })
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
        });
    }

    getCalculatedPriceCart = (event) => {
        this.setState({
            total: this.state.count * this.props.item.productPrice
        })
    }

    // handle add button change -> product count + 
    handleAddChangeCart = (event) => {
        this.setState({
            count: (this.state.textValue >= 0 && this.props.item.availableCount >= this.state.count + 50) ? this.state.textValue + 50 : this.props.item.productCount,
            textValue: this.props.item.availableCount >= this.state.textValue + 50 ? this.state.textValue + 50 : this.props.item.productCount,
            changed: 'true',
            item: this.props.item
        }, () => {
            this.getCalculatedPriceCart();
        });
    };

    // handle min button change -> product count -
    handleMinChangeCart = (event) => {
        this.setState({
            count: this.state.textValue >= 100 ? this.state.textValue - 50 : 0,
            textValue: this.state.textValue <= 0 ? 0 : this.state.textValue - 50,
            changed: 'true',
            item: this.props.item
        }, () => {
            this.getCalculatedPriceCart();
        });
    }

    render() {
        // returning react Cctegory ui view for a product
        return (
            <div className="category" key={this.props.item.id}>
                <div className="category-image">
                    <img src={this.props.item.imgURL} alt={this.props.item.categoryName} style={{ width: '300px', height: '300px' }} /><br /><br />
                    {this.props.type === 'cartProduct' ?
                        <div>
                            <span className="category-title">Cart Name : {this.props.cart.cartName}</span>
                            <br /><br />
                        </div>
                        : ""
                    }
                    <span className="category-title">{this.props.type === 'category' ? "Category : " : "Product : "} {this.props.item.categoryName === undefined ? this.props.item.productName : this.props.item.categoryName}</span>

                    {this.props.type === 'product' ?
                        <div>
                            <br />
                            <span className="category-title">{"Product Info : " + this.props.item.productInfo.amount + " " + this.props.item.productInfo.detail} </span>
                            <br /><br />
                            <span className="category-title">{"Product Price :  $ " + this.props.item.productPrice} </span>
                            <br /><br />
                            <span className="category-title">{"Available Count : " + this.props.item.productCount + " " + this.props.item.productInfo.detail} </span>
                        </div>
                        :
                        ""
                    }
                </div>
                <div className="category-content">
                    {this.props.type === 'category' ?
                        <div>
                            <Button variant="contained" color="secondary" style={{ margin: '20px', textAlign: "right", marginLeft: "50px" }} >View Products</Button>
                        </div>
                        :
                        <div>
                            {this.props.type !== 'cartProduct' ?
                                <div>
                                    <label for="cart"><span>Choose a Cart : </span></label>
                                    <select name="carts" id="carts">
                                        {this.props.cart.length !== 0 ? this.props.cart.map(cart =>
                                            <option value="d"> {cart.cartName} </option>)
                                            : ""}
                                    </select><br /><br />
                                </div>
                                : ""
                            }
                            {this.props.type === 'product' ?
                                <div>
                                    <AddBoxIcon className="fa fa-plus-circle" style={{ color: 'green', fontSize: 40 }} onClick={this.handleAddChange} />
                                    <TextField id="quantity" defaultValue="0" variant="outlined" size="small" style={{ width: "150px" }} value={this.state.count + " " + this.props.item.productInfo.detail} />
                                    <IndeterminateCheckBoxIcon className="fa fa-plus-circle" style={{ color: 'red', fontSize: 40 }} onClick={this.handleMinChange} /><br />
                                    <Button variant="contained" color="primary" style={{ margin: '20px', textAlign: "right", marginLeft: "50px" }}>Amount : $ {this.state.changed === 'true' ? this.state.total : 0}</Button>
                                </div>
                                :
                                <div>
                                    <span className="category-title">{"Available Count : " + this.props.item.availableCount + " " + this.props.item.productInfo} </span>
                                    <br /><br />
                                    <span className="category-title">Product Price : $ {this.state.changed === 'true' ? this.state.total : this.props.item.productTotalPrice} </span>
                                    <br /><br />
                                    <span className="category-title">Product Count : </span><br /><br />

                                    <AddBoxIcon className="fa fa-plus-circle" style={{ color: 'green', fontSize: 40 }} onClick={this.handleAddChangeCart} />
                                    <TextField id="quantity" defaultValue="0" variant="outlined" size="small" style={{ width: "150px" }} value={this.state.textValue + " " + this.props.item.productInfo} />
                                    <IndeterminateCheckBoxIcon className="fa fa-plus-circle" style={{ color: 'red', fontSize: 40 }} onClick={this.handleMinChangeCart} /><br />
                                    <Button variant="contained" color="primary" style={{ margin: '20px', textAlign: "right", marginLeft: "50px" }}>Update product </Button>
                                </div>
                            }
                        </div>
                    }
                </div>
            </div >
        );
    }
}


// export default (Category)
export default (Category)