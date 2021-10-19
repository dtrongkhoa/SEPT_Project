  import React, { Component } from "react";
import { createNewUser } from "../../actions/securityActions";
import * as PropTypes from 'prop-types'
import { connect } from "react-redux";
import classnames from "classnames";
import '../../CSS/style.css';
class Register extends Component {
  constructor() {
    super();

    this.state = {
      username: "",
      fullName: "",
      password: "",
      confirmPassword: "",
      errors: {}
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  onSubmit(e) {
    e.preventDefault();
    const newUser = {
      username: this.state.username,
      fullName: this.state.fullName,
      password: this.state.password,
      confirmPassword: this.state.confirmPassword
    };
    this.props.createNewUser(newUser, this.props.history);
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  componentWillReceiveProps(nextProps) {
    this.setState({ errors: nextProps.errors })
  }

  render() {
    const { errors } = this.state; 
    return (
      < div className="container" id="regis-body">
      <form class ="box" onSubmit={this.onSubmit}>
        <h1>Sign Up</h1>
        <div>
          <input type="text" className={classnames("form-control form-control-lg", {"is-invalid": errors.fullName})} placeholder="Full name" name="fullName" value={this.state.fullName} onChange={this.onChange}/>
          {errors.fullName && (
                      <div className="invalid-feedback">{errors.fullName}</div>
                      )}
        </div>

        <div>
          <input type ="email" className={classnames("form-control form-control-lg", {"is-invalid": errors.username})} placeholder="Email" name="username" value={this.state.username} onChange={this.onChange}/>
          {errors.username && (
                      <div className="invalid-feedback">{errors.username}</div>
                      )}
        </div>

        <div>
          <input type ="password" className={classnames("form-control form-control-lg", {"is-invalid": errors.password})} placeholder="Password" name="password" value={this.state.password} onChange={this.onChange} />
          {errors.password && (
                    <div className="invalid-feedback">{errors.password}</div>
                  )}
        </div>

        <div>
          <input type ="password" className={classnames("form-control form-control-lg", {"is-invalid": errors.confirmPassword})} placeholder="Re-enter password" name="confirmPassword" value={this.state.confirmPassword} onChange={this.onChange}/>
          {errors.confirmPassword && (
                    <div className="invalid-feedback">{errors.confirmPassword}</div>
                  )}
        </div>

        <div>
        <input type ="submit" value="Sign Up"/>
        </div>

    </form>
    </div>
    );
  }
}

Register.propTypes = {
  createNewUser: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  errors: state.errors
});
export default connect(
  mapStateToProps,
  { createNewUser }
)(Register);