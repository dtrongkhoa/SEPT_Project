import axios from "axios";
import {GET_ERRORS, SET_CURRENT_USER} from "./types";
import setJWTToken from "../securityUtils/setJWTToken";
import jwt_decode from "jwt-decode";
const LOGGED_IN_USER = "user-logged-in";


export const createNewUser = (newUser, history) => async dispatch => {
  console.log(JSON.stringify(newUser));
  console.log("Accessing create new user !")
    try{

        await axios.post("http://localhost:8080/api/users/register", newUser);
        history.push("/login");
        dispatch({
            type: GET_ERRORS,
            payload: {}
        });
    }
    catch (err){
      console.log("This is the error : " + JSON.stringify(err.response.data))
        dispatch ({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }

};

export const login = LoginRequest => async dispatch => {
    try {
      // post => Login Request
      const res = await axios.post("http://localhost:8080/api/users/login", LoginRequest);
      // extract token from res.data
      const json_data_incoming = JSON.parse(res.config.data)
      const { token } = res.data;
      // store the token in the localStorage
      localStorage.setItem("jwtToken", token);
      // set our token in header ***
      setJWTToken(token);
      // decode token on React
      const decoded = jwt_decode(token);
     localStorage.setItem(LOGGED_IN_USER, json_data_incoming.username);
      // dispatch to our securityReducer
      dispatch({
        type: SET_CURRENT_USER,
        payload: decoded,
        user: localStorage.getItem(LOGGED_IN_USER)
      });
    } catch (err) {
      dispatch({
        type: GET_ERRORS,
        payload: err.response.data
      });
    }
  };
  
  export const logout = () => dispatch => {
    localStorage.removeItem("jwtToken");
    localStorage.removeItem(LOGGED_IN_USER);
    setJWTToken(false);
    dispatch({
      type: SET_CURRENT_USER,
      payload: {}
    });
  };
  
