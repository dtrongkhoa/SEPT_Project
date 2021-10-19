import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import classnames from "classnames";
import { login } from "../../actions/securityActions";
import '../../CSS/style.css';

class Login extends Component {
  constructor() {
    super();
    this.state = {
      username: "",
      password: "",
      errors: {}
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentDidMount() {
    if (this.props.security.validToken) {
      window.location.href = "/";
    }
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.security.validToken) {
      window.location.href = "/";
    }

    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }
  onSubmit(e) {
    e.preventDefault();
    const LoginRequest = {
      username: this.state.username,
      password: this.state.password
    };
    this.props.login(LoginRequest);
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  render() {
    const { errors } = this.state;
    return (
      <form className = "box " onSubmit={this.onSubmit} >
        <h1>Login</h1>
        <input className={classnames("form-control form-control-lg", {"is-invalid": errors.username})} type ="email" name="username" placeholder="Email" onChange={this.onChange} />
        {errors.username && (
                    <div className="invalid-feedback">{errors.username}</div>
        )}

        <input className={classnames("form-control form-control-lg", {"is-invalid": errors.password})} type ="password" name="password" placeholder="Password"  onChange={this.onChange} />
        {errors.password && (
                    <div className="invalid-feedback">{errors.password}</div>
        )}
        <input type ="submit" value="Login"/>

        <a href = "forgotpassword">Forgot Password?</a>
        <p>Don't have an account? <a href ="register">  Sign up</a> today!</p>
        
      </form>
    );
  }
}

Login.propTypes = {
  login: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
  security: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  security: state.security,
  errors: state.errors
});

export default connect(
  mapStateToProps,
  { login }
)(Login);
