import React, {Component} from 'react'
import '../../CSS/style.css'
import {actAddToCart} from '../../actions/index';
import {connect} from 'react-redux';



class Product extends Component {
    render() {
        var json = this.props.location.param1;
        // var {addToCart} = this.props.location.addToCart;
        return (
            <div className="container">
                {/* <div className = "card" > */}
                    <div className="container-fliud">
                        <div className='wrapper row'>

                            <div className='preview col-md-6 justify-content-center'>
                                <div className="preview-pic tab-conten">
                                    <div class="tab-pane active " id="pic-1">
                                        <img src={json.coverImage}/>
                                    </div>
                                </div>
                                
                            </div>

                            <div className='details col-md-6'>
                                <h1 class="product-title">{json.title}</h1>
                                <h5 className='product-author'>Author: {json.authorFirst} {json.authorLast}</h5>
							
                                <p class="product-description">
                                    {json.description}
                                </p>

                                <h4 class="price">Current price: <span>${json.price !== undefined ? json.price: '29.99'}</span></h4>
                                <div class="action">
                                <a  className="btn btn-primary" type="submit" 
                                    //  onClick= {() => this.props.location.state.addToCart(json)}
                                    >
                                        Add to Cart
                                        </a>
                                   
                                    
                                </div>

						    </div>
                        
                        </div>
                    </div>
                {/* </div> */}
            </div>

        
        )
        
    }
    addToCart =  (json) => {
        this.addToCart(json);
    }
    
}

// const mapStateToProps = state => {
//     return {
//         products_array : state.productReducer
//     }
// }
// const mapDispathToProps = (dispatch, props) => {
//     return {
//         addToCart: (json) => {
//             dispatch(actAddToCart(json,1));
//         }
//     }
// }

export default (Product);
