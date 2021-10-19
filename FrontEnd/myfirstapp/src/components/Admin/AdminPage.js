import React, { Component } from 'react'
import "../../CSS/style.css";
class AdminPage extends Component {
    render() {
        return (
            <div className='container'>
                <div className='row'>
                    <div className="col-sm-6 col-lg-6 col-md-6 " id = 'admin-div-button'>
                    <button type="button" class="btn btn-secondary btn-lg btn-block btn-outline-info admin-button">Edit Users</button>
                    </div>

                    <div className="col-sm-6 col-lg-6 col-md-6" id = 'admin-div-button'>
                    <button type="button" class="btn btn-secondary btn-lg btn-block btn-outline-info admin-button">Activity</button>
                    </div>

                    <div className="col-sm-6 col-lg-6 col-md-6" id = 'admin-div-button'>
                    <button type="button" class="btn btn-secondary btn-lg btn-block btn-outline-info admin-button">Activity</button>
                    </div>

                    
                    
                    
                </div>                
            </div>
        )
    }
}
export default AdminPage;