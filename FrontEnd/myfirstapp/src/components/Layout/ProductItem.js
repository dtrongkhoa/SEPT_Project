import React, { Component } from 'react'
import { Link } from "react-router-dom";

class ProductItem extends Component {
    constructor(props){
        super(props);
    }
    render() {
        var {product} = this.props;
        if(!product){
            return null;
        }
        return (
            
            <div className="col-sm-6 col-lg-4 col-md-6 product-item">
                <div className = "card" >
                    <img className="card-img-top" src={product.coverImage}
                            alt={product.title}></img>
                    <div className="card-body">
                        <h5 className="card-title" name="name">{product.title}</h5>
                        <p className="card-text" name="price">
                            ${product.price !== undefined ? product.price: '29.99'}
                            </p>
                            <a className="card-text">
                                    <Link  to={{ 
                                                pathname: "/product", 
                                                param1: product,
                                                // state: {addToCart: this.props.addToCart}
                                                }}
                                            >Read More</Link>
                                
                            </a>
                        <a  className="btn btn-primary" type="submit" onClick= {() => this.addToCart(product)}>Add to Cart</a>
                    </div>
                </div>
            </div>
        )
    }

    addToCart =  (product) => {
        this.props.addToCart(product);
    }
}
export default ProductItem;