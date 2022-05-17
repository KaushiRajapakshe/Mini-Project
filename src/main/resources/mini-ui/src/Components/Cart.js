// Mini project Cart react ui
import React, { Component } from 'react';
import Button from '@material-ui/core/Button';
import styled from "styled-components";
import DeleteIcon from '@material-ui/icons/Delete';

const Delete = styled.button`
  background-color: red;
  color: #fff;
  padding: 10px 0px 3px 0px;
  font-size: 0.875rem;
  border-color: red;
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

// React Cart class
class Cart extends Component {

    // Initialize state values 
    state = {
        data: '',
        item: [],
        value: 'single',
        textValue: 0,
        price: 0,
        changed: 'false',
        total: 0,
        count: 0
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

    render() {
        // returning react Cctegory ui view for a product
        return (
            <div className="card" key={this.props.item.id}>
                <div style={{ display: "flex", justifyContent: "flex-end", padding: "10px" }}>
                    <Delete>
                        <DeleteIcon> </DeleteIcon>
                    </Delete>
                </div>
                <div className="card-image">
                    <span className="card-title">{"Cart name : "} {this.props.item.cartName}</span><br /><br />
                    <span className="card-title ">{"Cart added date : "} {new Intl.DateTimeFormat('en-US', { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit' }).format(new Date(this.props.item.addedDate))}</span>
                    <br />
                </div>
                <div style={{ display: "flex", justifyContent: "flex-end", fontWeight: "400", margin: "10px" }}>
                    <Button variant="contained" color="primary" style={{ textAlign: "right", justifyContent: "flex-end" }} >View Products</Button>
                </div>
            </div>
        );
    }
}

// export default (Card)
export default (Cart)