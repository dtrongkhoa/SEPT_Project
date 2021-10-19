import React, {Component} from 'react'

import {connect} from 'react-redux';
import Products from './../components/Layout/Products';

import ProductItem from './../components/Layout/ProductItem';
import PropTypes from 'prop-types';
import {actAddToCart} from '../actions/index';
import axios from 'axios';

class ProductsContainer extends Component {
    state = {
        products: []
      }
    
    componentDidMount() {
    var self = this;
    axios.get(`http://localhost:8090/books/trending`)
        .then(res => {
        self.setState({products: res.data})
        }
        )
    }
    render() {
        console.log(this.state.products.length)
        var {products_array} = this.props;
        if(this.state.products.length !== 0){
            products_array = this.state.products;
            console.log(products_array)
        }
        return (
            <Products>
                {this.showProducts(products_array)}
            </Products>
        )
    }
    showProducts(products_array){
        var result = null;
        var {addToCart} = this.props;
        if (products_array.length > 0 ){
            result = products_array.map((product, index) => {
                
                return <ProductItem key={index} product={product} addToCart = {addToCart}/>
            })
        }
        return result;
    }
}

ProductsContainer.propTypes = {
    products_array : PropTypes.arrayOf(
        PropTypes.shape({
            isbn : PropTypes.number.isRequired,
            name: PropTypes.string.isRequired,
            image: PropTypes.string.isRequired,
            price: PropTypes.number.isRequired,
            inventory: PropTypes.number.isRequired,

        })
    ).isRequired
}
const mapStateToProps = state => {
    return {
        products_array : state.productReducer
    }
}

const mapDispathToProps = (dispatch, props) => {
    return {
        addToCart: (product) => {
            dispatch(actAddToCart(product,1));
        }
    }
}

export default connect(mapStateToProps, mapDispathToProps)(ProductsContainer);
