import React from 'react';
import '../App.css';

import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import image from '../image/mini.png';
import cart from '../image/shopping-cart.png';

import Home from "./Home.js";
import Wishlist from "./Wishlist.js";
import Login from "./Login.js";
import CardProduct from "./CartProduct.js";
import Register from './Register';

// React Header function
function Header() {

    const useStyles = makeStyles((theme) => ({
        title: {
            marginRight: theme.spacing(2),
            color: "black!important",
            textAlign: 'left',
            flex: 1,
            marginLeft: '10px'
        },
        navButton: {
            textAlign: 'right',
            alignSelf: 'stretch',
            color: 'black'
        },
        navIcon: {
            width: '60px'
        },
        cartIcon: {
            padding: '5px',
            width: '30px'
        }
    }));

    const classes = useStyles();

    // returning react header ui view component
    return (
        <Router>
            <AppBar className="appbar" position="static">
                <Toolbar className="toolbar">
                    <Typography>
                        <img src={image} className={classes.navIcon} />
                    </Typography>
                    <Typography variant="h6" className={classes.title}>
                        Welcome!
                    </Typography>
                    <Button component={Link} to='/' color="inherit" className={classes.navButton}>Home</Button>
                    <Button component={Link} to='/wishlist' color="inherit" className={classes.navButton}> <img src={cart} className={classes.cartIcon} /></Button>
                    <Button component={Link} to='/card-product' color="inherit" className={classes.navButton}>Card Product</Button>
                    <Button component={Link} to='/card-product' color="inherit" className={classes.navButton}>Update Card Product</Button>
                    <Button component={Link} to='/card-product' color="inherit" className={classes.navButton}>Create Card</Button>
                    <Button component={Link} to='/register' color="inherit" className={classes.navButton}>Register</Button>
                    <Button component={Link} to='/card-product' color="inherit" className={classes.navButton}>Card Product</Button>
                    <Button component={Link} to='/login' color="inherit" className={classes.navButton}>Login</Button>
                    <Button component={Link} to='/' color="inherit" className={classes.navButton}>Log out</Button>
                </Toolbar>
            </AppBar>
            {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
            <Switch>
                <Route path="/wishlist">
                    <Wishlist />
                </Route>
                <Route path="/login">
                    <Login />
                </Route>
                <Route path="/card-product">
                    <CardProduct />
                </Route>
                <Route path="/register">
                    <Register />
                </Route>
                <Route path="/">
                    <Home />
                </Route>
            </Switch>
        </Router>
    );

}

export default Header;