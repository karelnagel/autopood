import React, {Component} from 'react';
import PropTypes from "prop-types";

class RegisterUser extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: 0,
            name: '',
            type: '',
            brand: '',
            model: '',
            fuelType: '',
            country: '',
            minPrice: 0.0,
            maxPrice: 0.0,
            maxMileage: 0.0,
            minMileage: 0.0,
            maxYear: 0.0,
            minYear: 0.0,
            minEngineKW: 0.0,
            maxEngineKW: 0.0,
            minEngineSize: 0.0,
            maxEngineSize: 0.0,
        }
        //If you dont use arrow function you will have to manually bind like this
        //If you dont bind you wont be able to access items in the state of this component because it wont be recognised in lifecycle
        //this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    onChange = (e) => {
        //This is what you do for individual target
        //this.setState({name:e.target.value});
        //But if you have plenty
        this.setState({[e.target.name]: e.target.value})
    }

    onSubmit(e) {
        e.preventDefault();
        //Copying state object to newUser
        let newUser = this.state;
        this.props.addUser(newUser);
        //Resetting the fields
        this.setState({
            id: 0,
            name: '',
            type: '',
            brand: '',
            model: '',
            fuelType: '',
            country: '',
            minPrice: 0.0,
            maxPrice: 0.0,
            maxMileage: 0.0,
            minMileage: 0.0,
            maxYear: 0.0,
            minYear: 0.0,
            minEngineKW: 0.0,
            maxEngineKW: 0.0,
            minEngineSize: 0.0,
            maxEngineSize: 0.0,
        });
    }

    render() {
        return (
            <form onSubmit={this.onSubmit}>
                <input
                    type="text"
                    name="name"
                    placeholder="Name"
                    value={this.state.name}
                    onChange={this.onChange}
                />
                <br/>

                <input
                    type="text"
                    name="type"
                    placeholder="Type"
                    value={this.state.type}
                    onChange={this.onChange}
                />
                <br/>
                <input
                    type="text"
                    name="brand"
                    placeholder="Brand"
                    value={this.state.brand}
                    onChange={this.onChange}
                />
                <br/>

                <input
                    type="text"
                    name="model"
                    placeholder="Model"
                    value={this.state.model}
                    onChange={this.onChange}
                />
                <br/>
                <input
                    type="text"
                    name="fuelType"
                    placeholder="Fuel type"
                    value={this.state.fuelType}
                    onChange={this.onChange}
                />
                <br/>
                <input
                    type="text"
                    name="country"
                    placeholder="Country"
                    value={this.state.country}
                    onChange={this.onChange}
                />
                <div style={{display:'flex' }}>
                    <input
                        type = "number"
                        name = "minPrice"
                        placeholder="Min price"
                        style={leftInput}
                        value={this.state.minPrice}
                        onChange={this.onChange}
                    />

                    <input
                        type = "number"
                        name = "maxPrice"
                        placeholder="Max price"
                        style={rightInput}
                        value={this.state.maxPrice}
                        onChange={this.onChange}
                    />
                </div>
                <div style={{display:'flex' }}>
                    <input
                        type = "number"
                        name = "minYear"
                        placeholder="Min year"
                        style={leftInput}
                        value={this.state.minYear}
                        onChange={this.onChange}
                    />

                    <input
                        type = "number"
                        name = "maxYear"
                        placeholder="Max year"
                        style={rightInput}
                        value={this.state.maxYear}
                        onChange={this.onChange}
                    />
                </div>
                <div style={{display:'flex' }}>
                    <input
                        type = "number"
                        name = "minMileage"
                        placeholder="Min mileage"
                        style={leftInput}
                        value={this.state.minMileage}
                        onChange={this.onChange}
                    />

                    <input
                        type = "number"
                        name = "maxMileage"
                        placeholder="Max mileage"
                        style={rightInput}
                        value={this.state.maxMileage}
                        onChange={this.onChange}
                    />
                </div>
                <div style={{display:'flex' }}>
                    <input
                        type = "number"
                        name = "minEngineKW"
                        placeholder="Min engine kw"
                        style={leftInput}
                        value={this.state.minEngineKW}
                        onChange={this.onChange}
                    />

                    <input
                        type = "number"
                        name = "maxEngineKW"
                        placeholder="Max engine kw"
                        style={rightInput}
                        value={this.state.maxEngineKW}
                        onChange={this.onChange}
                    />
                </div>
                <div style={{display:'flex' }}>
                    <input
                        type = "number"
                        name = "minEngineSize"
                        placeholder="Min engine size"
                        style={leftInput}
                        value={this.state.minEngineSize}
                        onChange={this.onChange}
                    />

                    <input
                        type = "number"
                        name = "maxEngineSize"
                        placeholder="Max engine size"
                        style={rightInput}
                        value={this.state.maxEngineSize}
                        onChange={this.onChange}
                    />
                </div>

                <span style={rightInput}></span>
                <br/>
                <input
                    type="submit"
                    value="Submit"
                    className="btn"
                />
                <br/>
            </form>
        )
    }
}

const leftInput = {
    flex: '5',
    padding: '5px',
    margin: '10px 10px 0px 0px'
}

const rightInput = {
    flex: '5',
    padding: '5px',
    margin: '10px 0px 0px 10px'
}

RegisterUser.propTyoes = {
    addUser: PropTypes.func.isRequired,
}

export default RegisterUser;