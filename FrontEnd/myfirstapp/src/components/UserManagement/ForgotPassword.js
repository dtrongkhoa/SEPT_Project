import React, { Component } from 'react'


class ForgotPassword extends Component {
    render() {
        return (
            
        <form className ="box">
        <h1>What is your email?</h1>
        
        <input type ="email" placeholder="Email address"/>
      
        
        <input type ="submit" value="Submit"/>
        </form>
                
            
        )
    }
}

export default ForgotPassword;