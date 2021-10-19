import React, { Component } from 'react'

import CartItem from './CartItem';

import Product from '../Layout/ProductItem';
import addToCart from '../Layout/ProductItem';


class Cart extends Component {
    
    render() {
        
        return (
            <div className="container">
            <section className="section">
                <div className="table-responsive">
                    <table className="table product-table">
                        <thead>
                            <tr>
                                <th></th>
                                <th>Products</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                                <th>Remove Items</th>
                            </tr>
                        </thead>
                        <tbody>
                        {this.props.children}
                        
                        </tbody>
                    </table>
                </div>

            </section>
            </div>
        );
    
    }
}
export default Cart;