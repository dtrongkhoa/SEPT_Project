import React, {Component} from 'react'
import { useState } from 'react';


class CartItem extends Component {
    constructor(props){
        super(props);
        this.state={
            quantity: 1
        }
    }
    render() {
        var {item} = this.props;
        var {quantity} = item.quantity > 0 ? item : this.state;
        console.log(quantity);
        return (
            <tr>
                <th className="all-cart">
                <img src={item.product.coverImage}
                    alt={item.product.coverImage} className="img-fluid z-depth-0" id="img-cart"/>
                </th>
                <td>
                    <h5 className="title" >{item.product.title}</h5>
                </td>
                <td className="price" >${item.product.price}</td>

                <td className="center-on-small-only">
                    <span className="qty">{quantity}</span>
                    <div className="btn-group radio-group" data-toggle="buttons">
                        <label className = "btn btn-sm btn-primary btn-rounded waves-effect waves-light" 
                        onClick= {() => this.onUpdateQuantity(item.product, item.quantity - 1 )}>
                            <a>-</a>
                        </label>
                        <label className = "btn btn-sm btn-primary btn-rounded waves-effect waves-light"
                        onClick= {() => this.onUpdateQuantity(item.product, item.quantity+1)}>
                            <a>+</a>
                        </label>
                    </div>
                </td>
                <td>${this.showSubTotal(item.product.price, item.quantity)}</td>
                <td>
                    <button type = "button" className="btn btn-sm btn-primary waves-effect waves-light" data-toggle="buttons"
                        title="" data-original-title="Remove item" onClick={()=> {this.delete(item.product)}} >
                            X
                    </button>
                </td>
                
            </tr>
        )

        
    };

    onUpdateQuantity = (product, quantity) => {
        if (quantity > 0){
            this.setState({
                quantity: quantity
            });
            this.props.onUpdateProductInCart(product, quantity);
        }
    }
    delete(product){
        console.log(product);
        var {actDeleteProductInCart} = this.props;
        actDeleteProductInCart(product);   
    }
    showSubTotal(price, quantity){
        var rel = Number ((price*quantity).toFixed(2));
        return rel;
      }
}
export default CartItem;