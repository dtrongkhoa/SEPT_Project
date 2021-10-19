import React, {Component} from 'react'
import '../../CSS/style.css'



class Products extends Component {
    render() {
       
        return (
            <div className="container justify-content-center">
                <div className="row">
                    {this.props.children}

                </div>
            </div>
        )
    }
}



export default Products;
