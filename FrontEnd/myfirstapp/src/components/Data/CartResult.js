import React, { Component } from 'react'

class CartResult extends Component {
    render() {
        var {cart} = this.props;
        return (
            <tr>
                <td colSpan="3"></td>
                <td>
                    <h4><strong>Total</strong></h4>
                </td>
                <td>
                    <h4><strong>${this.totalAmount(cart)}</strong></h4>
                </td>
                <td colSpan="3">
                    <button type="button" className="btn btn-primary waves-effect waves-light">Complete Purchase
                    <i className="fa fa-angle-right right"></i>
                    </button>
                </td>
                <td colSpan="3">
                    <button type="button" className="btn btn-primary waves-effect waves-light">Pay by Paypal
                    <i className="fa fa-angle-right right"></i>
                    </button>
                </td>
            </tr>
        )
    }
    totalAmount = (cart) => {
        var total =0;
        if (cart.length >0){
            for (var i=0; i< cart.length; i++){
                total += cart[i].product.price * cart[i].quantity;
            }
        }
        total = Number(total.toFixed(2));
        return total;
    }
}
export default CartResult;