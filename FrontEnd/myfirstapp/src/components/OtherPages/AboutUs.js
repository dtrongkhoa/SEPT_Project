import React, { Component } from 'react';
import "../../CSS/style.css";

class AboutUs extends Component {
    render() {
        return (
            <section className='container'>
            <div className="row">

                {/* Person 1 */}
                <div className="container col-sm-6 col-lg-4 col-md-6 " id='about'>
                    <div class="row">
                        <img className="" src='https://trangtienich.com/wp-content/uploads/2019/12/anh-dai-dien-facebook-doc-2.jpg' width='20' height='auto'
                            alt='harry_ava'></img>
                        <div class="column">
                            <div className="container"></div>
                                <h1>Harry Bhatia</h1>
                                <h2>Admin</h2>
                                
                                <p>insert email here</p>
                        </div>
                    </div>
                </div>

                {/* Person 2 */}
                <div className="container col-sm-6 col-lg-4 col-md-6">
                    <div class="row">
                        <img className="" src='https://st.quantrimang.com/photos/image/072015/22/avatar.jpg' width='20' height='280'
                            alt='khoa_ava'></img>
                        
                        <div class="column">
                            <div className="container"></div>
                                <h1>Trong Khoa Dang</h1>
                                <h2>Admin</h2>
                                <p>s3846329@student.rmit.edu.au</p>
                        </div>
                    </div>
                </div>

                <div className="container col-sm-6 col-lg-4 col-md-6" id='about'>
                    <div class="row">
                        <img className="" src='https://trangtienich.com/wp-content/uploads/2019/12/anh-dai-dien-facebook-doc-12.jpg' width='20' height='auto'
                            alt='harry_ava'></img>
                        <div class="column">
                            <div className="container"></div>
                                <h1>Andy Ho Ming Moy</h1>
                                <h2>Admin</h2>
                                
                                <p>insert email here</p>
                        </div>
                    </div>
                </div>

                <div className="container col-sm-6 col-lg-4 col-md-6">
                    <div class="row">
                        <img className="" src='https://lucloi.vn/wp-content/uploads/2020/03/90089820_577901579485437_7060413956694736896_n.jpg' width='20' height='290'
                            alt='vanessa_ava'></img>
                        <div class="column">
                            <div className="container"></div>
                                <h1>Vanessa Nguyen</h1>
                                <h2>Admin</h2>
                                <p>insert email here</p>
                        </div>
                    </div>
                </div>

            </div>
            </section>
        )
    }
}
export default AboutUs 