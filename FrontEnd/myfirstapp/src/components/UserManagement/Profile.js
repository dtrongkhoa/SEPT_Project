import React, { Component } from "react";
import { createNewUser } from "../../actions/securityActions";
import * as PropTypes from 'prop-types'
import { connect } from "react-redux";
import classnames from "classnames";
import '../../CSS/style.css';
import axios from 'axios';

class Profile extends Component {
  delete_accout = () => {
    var email  = localStorage.getItem("user-logged-in")
    axios.delete(`http://localhost:8080/api/users/delete/`+email);
    localStorage.removeItem("user-logged-in");
    window.location.href = "/"
}
  render() {
    return (
    <div>
      <h2 className="display-4">Welcome to your Profile</h2>
      <div className="col-md-4 animated fadeIn">
              <div className="card">
                <div className="card-body">
                  <h5 className="card-title">
                           Logged in as: {localStorage.getItem("user-logged-in")}
                  </h5>
                  <button onClick={this.delete_accout} className="btn btn-primary" value="change!" >DELETE ACCOUNT</button>
                </div>
              </div>
        </div>
    </div>
    );
  }
}
export default connect()(Profile);