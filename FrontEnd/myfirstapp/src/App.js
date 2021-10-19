import React, { Component } from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddPerson from "./components/Persons/AddPerson";
import { Provider } from "react-redux";
import store from "./store";

import Cart from "./components/Data/Cart";

import Register from "./components/UserManagement/Register";
import Login from "./components/UserManagement/Login";
import ForgotPassword from "./components/UserManagement/ForgotPassword";


import jwt_decode from "jwt-decode";
import setJWTToken from "./securityUtils/setJWTToken";
import { SET_CURRENT_USER } from "./actions/types";
import { logout } from "./actions/securityActions";
import SecuredRoute from "./securityUtils/SecureRoute";
import CartContainer from "./containers/CartContainer";
import Product from "./components/Layout/Product";
import Profile from "./components/UserManagement/Profile";
import ContactUs from "./components/OtherPages/ContactUs";
import AboutUs from "./components/OtherPages/AboutUs";
import AdminPage from "./components/Admin/AdminPage";

const jwtToken = localStorage.jwtToken;

if (jwtToken) {
  setJWTToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decoded_jwtToken
  });

  const currentTime = Date.now() / 1000;
  if (decoded_jwtToken.exp < currentTime) {
    store.dispatch(logout());
    window.location.href = "/";
  }
}
const loginUser = (username) => {
  console.log("Logging in the user");
}
class App extends Component {

  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Header username={localStorage.getItem("user-logged-in")}/>

            <Route exact path="/" component={Dashboard}/>
            <Route exact path="/register" component={Register} />
            <Route exact path="/login" component={Login} />
            <Route exact path="/forgotpassword" component={ForgotPassword} />
            <Route exact path="/cart" component={CartContainer} />
            <Route exact path="/product" component={Product} />
            <Route exact path="/profile" component={Profile} />
            <Route exact path="/contact-us" component={ContactUs} />
            <Route exact path="/about-us" component={AboutUs} />
            <Route exact path="/admin" component={AdminPage} />
            {
              //Private Routes
            }
            <Route exact path="/dashboard" component={Dashboard} />
            <Route exact path="/addPerson" component={AddPerson} />
          
          </div>
        </Router>
      </Provider>
    );
  }
}
export default App;