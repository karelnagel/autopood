import React, {Component} from 'react';
import Parameter from "./Parameter";
import PropTypes from "prop-types";
import {Button} from "@material-ui/core";

class User extends Component {

    render() {
        const user = this.props.user;
        return <div style={userSection} id='user'>
            <h2 style={welcomeMessage}>Tere {user.firstName} {user.lastName}!</h2>
            <br/>
            <p>Sinu salvestatud otsingud:</p>
            {this.props.user.parameters.map((parameter) => (
                <Parameter parameter={parameter} key={parameter.id} selected={this.props.selected} updateSearch={this.props.updateSearch}/>
            ))}
            <Button style={button} onClick={(e) =>{
                this.props.updateSearch(null)
            }}>LISA UUS
            </Button>
        </div>
    }

}

const userSection = {
    minWidth: '300px',
    padding: '15px'
}
const welcomeMessage = {
    textAlign: 'center',
    lineHeight: '0px',
}
const button ={
    marginTop: 'auto',
    marginLeft:'auto',
    marginRight:'auto',
}

User.propTypes = {
    user: PropTypes.object.isRequired,
    updateSearch:PropTypes.func.isRequired,
    selected:PropTypes.number.isRequired
}

export default User;