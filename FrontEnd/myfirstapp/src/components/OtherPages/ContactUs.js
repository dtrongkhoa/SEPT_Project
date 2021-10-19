import React, { Component } from 'react';

class ContactUs extends Component {
    render() {
        return (
            <section className="container">
                {/* Section details */}
                <h2 className="h1-responsive font-weight-bold text-center my-4">Contact us</h2>
                <p className="text-center w-responsive mx-auto mb-5">
                    If you have any suggestions or would like to get in touch, 
                    please use the form to contact us. Any and all feedback is much appreciated. </p>

                {/* Page structure - using a grid */}
                <div className="row">

                    <div className="col-md-9 mb-md-0 mb-5">
                        <form id="contact-form" name="contact-form" action="mail.php" method="POST">
                        {/* First row */}
                        <div className="row">

                            {/* Column 1 for sender's name */}
                            <div className="col-md-6">
                                <div className="md-form mb-0">
                                    <input type="text" id="name" name="name" className="form-control"/>
                                    <label for="name" className="">Your name</label>
                                </div>
                            </div>

                            {/* Column 2 for email address */}
                            <div className="col-md-6">
                                <div className="md-form mb-0">
                                    <input type="text" id="email" name="email" className="form-control"/>
                                    <label for="email" className="">Your email</label>
                                </div>
                            </div>

                        </div>

                        {/* Second row */}
                        <div className="row">

                            {/* Field for email subject line */}
                            <div className="col-md-6">
                                <div className="md-form mb-0">
                                    <input type="text" id="subject" name="subject" className="form-control"/>
                                    <label for="subject" className="">Subject</label>
                                </div>
                            </div>
                        </div>

                        {/* Third row */}
                        <div className="row">
                            {/* A column for text input in a set width */}
                            <div className="col-md-6">

                                <div className="md-form">
                                    <textarea type="text" id="message" name="message" className="form-control md-textarea"/>
                                    <label for="message">Your message</label>
                                </div>

                            </div>
                        </div> 

                    </form>

                        {/* Send email button */}
                        <div>
                            <a className="btn btn-primary">Send</a>
                        </div>
                        <div className="status"></div>

                    </div>

                    {/* List of website contact methods */}
                    <div className="col-md-3 text-center">
                        <ul className="list-unstyled mb-0">
                            <li><i className="fas fa-phone m6-4 fa-2x"></i>
                                <p>sample-email@mail.com</p>
                            </li>
                        </ul>
                    </div>
                </div>
            </section>
        )
    }
}
export default ContactUs 