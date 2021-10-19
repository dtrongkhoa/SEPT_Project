import React, { Component, useState } from 'react'

import Person from './Persons/Person'
import CreatePersonButton from './Persons/CreatePersonButton';
import "../CSS/style.css"
import ProductsContainer from '../containers/ProductsContainer';
import CartContainer from '../containers/CartContainer';

class Dashboard extends Component {

    constructor(props){
        super(props)
        this.state = {
            username: "",
            id: "",
            fullName: ""
        }
    }
    render() {
        return (
            <><form className="search-box-dash">
                <input className="form-control form-control-lg" type="search" placeholder="Search for a book here..." aria-label="Search" />
                <button className="btn btn-light my-sm-0" type="submit">Search</button>
            </form><div className="container">
                    <ProductsContainer />
                    {/* <CartContainer/> */}
                </div></>
        )
    }
}
export default Dashboard;
