import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import personReducer from "./personReducer";
import securityReducer from "./securityReducer";
import productReducer from './productReducer';
import cartReducer from './cartReducer';

const appReducers = combineReducers({
  errors: errorReducer,
  person: personReducer,
  security: securityReducer,
  productReducer,
  cartReducer
});

export default appReducers;
// export default combineReducers({
//   errors: errorReducer,
//   person: personReducer,
//   security: securityReducer,
//   productReducer 
// });

