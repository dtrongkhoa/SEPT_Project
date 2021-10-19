import React, {Component} from 'react'
import {connect} from 'react-redux';
import PropTypes from 'prop-types';
import Cart from './../components/Data/Cart';
import CartItem from './../components/Data/CartItem';
import CartResult from './../components/Data/CartResult';
import {actDeleteProductInCart, actUpdateProductInCart} from '../actions/index';


class CartContainer extends Component {
    render() {
        var {cart} = this.props;
        console.log(cart);
        return (
            <Cart>
                {this.showCartItem(cart)}
                {this.showTotalAmount(cart)}
            </Cart>
        )
    }
    showTotalAmount = (cart) => {
        var result = null;
        if (cart.length>0){
            result= <CartResult cart={cart}/>
        }
        return result;
    }
    showCartItem = (cart) => {
        var result = null;
        var {actDeleteProductInCart, onUpdateProductInCart} = this.props;
        if (cart.length > 0){
            result = cart.map((item, index) => {
                if(item.product.price === undefined){
                    item.product.price = 29.99;
                }
                return (
                    <CartItem key={index}
                     item={item} 
                     actDeleteProductInCart={actDeleteProductInCart}
                     onUpdateProductInCart= {onUpdateProductInCart}/>
                );
            });
        }
        return result;
    }
}
CartContainer.propTypes = {
    cart : PropTypes.arrayOf(PropTypes.shape({
        product: PropTypes.shape({
            isbn: PropTypes.number.isRequired,
            name: PropTypes.string.isRequired,
            image: PropTypes.string.isRequired,
            price: PropTypes.number.isRequired,
            inventory: PropTypes.number.isRequired,
        }).isRequired,
        quantity: PropTypes.number.isRequired
    })
        
    ).isRequired
}



const mapStateToProps = state => {
    return {
        cart: state.cartReducer
    }
}

const mapDispatchToProps = (dispatch, props) => {
    return {
        actDeleteProductInCart: (product)=>{
            dispatch(actDeleteProductInCart(product))
        },

        onUpdateProductInCart: (product, quantity) => {
            dispatch(actUpdateProductInCart(product, quantity));
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(CartContainer);