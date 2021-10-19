import React, { Component } from 'react'
import '../../CSS/style.css'
import product from './ProductItem'
// import {navigateTo} from './Product'

import {logout} from "../../actions/securityActions";

 class Header extends Component {
    render() {
        return (
            <div>
            <nav className="navbar navbar-expand-md navbar-dark navbar-custom" id="nav-bar">
            <div className="container">
                <a className="navbar-brand-custom" href="dashboard">
                    BOOKEROO
                </a>
                <div className="navbar-header">
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#mobile-nav">
                    <span className="navbar-toggler-icon" />
                    
                </button>
                </div>
    
                <div className="collapse navbar-collapse justify-content-center" id="mobile-nav" >
                    <ul className="navbar-nav mr-auto mx-auto">
                        <li className="nav-item">
                            <a className="navbar-brand nav-link active" href="/dashboard">
                                Home
                            </a>
                        </li>
                        <li className="nav-item">
                        <a className="nav-link" href="/about-us">
                                About
                            </a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">
                                Services
                            </a>
                        </li>
                        {this.props.username === 'admin@admin.com' ? 
                        <li className="nav-item">
                        <a className="nav-link" href="/contact-us">
                                Admin
                            </a>
                        </li>
                        :
                        <li className="nav-item">
                        <a className="nav-link" href="/admin">
                                Contact
                            </a>
                        </li>
                        }
                        
                        <li className="nav-item">
                            <a className="nav-link" href="cart" 
                            // onClick={() => navigateTo('cart')}
                             >
                                Cart 
                            </a>
                        </li>
                    </ul>
                    <ul className="navbar-nav ml-auto navbar-right">
                    {this.props.username === null ? 
                    
                    <><li className="nav-item">
                                        <a className="nav-link " href="register">
                                            Sign Up
                                        </a>
                                    </li><li className="nav-item ml-auto">
                                            <a className="nav-link" href="login">
                                                Login
                                            </a>
                                        </li></>
                    :
                    <><li className="nav-item">
                                        <a className="navbar-brand" href="dashboard">
                                            Welcome {this.props.username}
                                        </a>
                                    </li>
                                    <li className="nav-item">
                                            <a className="nav-link" href="/profile">
                                                My Profile
                                            </a>
                                        </li>
                                    <li className="nav-item">
                                            <a className="nav-link" href="/" onClick={logout()}>
                                                Logout
                                            </a>
                                        </li></>
                    }
                    </ul>
                </div>
            </div>
        </nav>
            </div>
        )
    }
}
export default Header;