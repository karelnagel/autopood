import React, {Component} from 'react';
import PropTypes from "prop-types";
import {Button, TextField} from "@material-ui/core";

class Search extends Component {
    constructor(props) {
        super(props);
        this.state = emptyParameter
        this.onSubmit = this.onSubmit.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    componentWillReceiveProps(nextProps) {
        console.log(nextProps)
        if (nextProps.parameter)
            this.state = nextProps.parameter;
        else
            this.state = emptyParameter;
    }

    onChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    onSubmit(e) {
        e.preventDefault();
        let newParameter = this.state;
        const button = e.nativeEvent.submitter.name;
        if (button === "save") {
            this.props.addParameter(newParameter);
        } else if (button==="search"){
            this.props.otsiKuulutusi(newParameter);
        }
        else {
            this.props.deleteParameter(newParameter.id)
            newParameter = emptyParameter;
        }
        this.props.updateSearch(newParameter);
    }

    render() {
        const userId = new URLSearchParams(window.location.search).get('userId');
        return (
            <div style={searchColumn} id="search">
                <form onSubmit={this.onSubmit}>
                    <TextField
                        type="text"
                        name="name"
                        label="Name"
                        style={input}
                        value={this.state.name}
                        onChange={this.onChange}
                    />
                    <br/>

                    <TextField
                        type="text"
                        name="type"
                        label="Type"
                        style={input}
                        value={this.state.type}
                        onChange={this.onChange}
                    />
                    <br/>
                    <TextField
                        type="text"
                        name="brand"
                        label="Brand"
                        style={input}
                        value={this.state.brand}
                        onChange={this.onChange}
                    />
                    <br/>

                    <TextField
                        type="text"
                        name="model"
                        label="Model"
                        style={input}
                        value={this.state.model}
                        onChange={this.onChange}
                    />
                    <br/>
                    <TextField
                        type="text"
                        name="fuelType"
                        label="Fuel type"
                        style={input}
                        value={this.state.fuelType}
                        onChange={this.onChange}
                    />
                    <br/>
                    <TextField
                        type="text"
                        name="country"
                        label="Country"
                        style={input}
                        value={this.state.country}
                        onChange={this.onChange}
                    />
                    <div style={{display: 'flex'}}>
                        <TextField
                            type="number"
                            name="minPrice"
                            label="Min price"
                            style={input}
                            style={leftInput}
                            value={this.state.minPrice}
                            onChange={this.onChange}
                        />

                        <TextField
                            type="number"
                            name="maxPrice"
                            label="Max price"
                            style={input}
                            style={rightInput}
                            value={this.state.maxPrice}
                            onChange={this.onChange}
                        />
                    </div>
                    <div style={{display: 'flex'}}>
                        <TextField
                            type="number"
                            name="minYear"
                            label="Min year"
                            style={input}
                            style={leftInput}
                            value={this.state.minYear}
                            onChange={this.onChange}
                        />

                        <TextField
                            type="number"
                            name="maxYear"
                            label="Max year"
                            style={input}
                            style={rightInput}
                            value={this.state.maxYear}
                            onChange={this.onChange}
                        />
                    </div>
                    <div style={{display: 'flex'}}>
                        <TextField
                            type="number"
                            name="minMileage"
                            label="Min mileage"
                            style={input}
                            style={leftInput}
                            value={this.state.minMileage}
                            onChange={this.onChange}
                        />

                        <TextField
                            type="number"
                            name="maxMileage"
                            label="Max mileage"
                            style={input}
                            style={rightInput}
                            value={this.state.maxMileage}
                            onChange={this.onChange}
                        />
                    </div>
                    <div style={{display: 'flex'}}>
                        <TextField
                            type="number"
                            name="minEngineKW"
                            label="Min engine kw"
                            style={input}
                            style={leftInput}
                            value={this.state.minEngineKW}
                            onChange={this.onChange}
                        />

                        <TextField
                            type="number"
                            name="maxEngineKW"
                            label="Max engine kw"
                            style={input}
                            style={rightInput}
                            value={this.state.maxEngineKW}
                            onChange={this.onChange}
                        />
                    </div>
                    <div style={{display: 'flex'}}>
                        <TextField
                            type="number"
                            name="minEngineSize"
                            label="Min engine size"
                            style={input}
                            style={leftInput}
                            value={this.state.minEngineSize}
                            onChange={this.onChange}
                        />

                        <TextField
                            type="number"
                            name="maxEngineSize"
                            label="Max engine size"
                            style={input}
                            style={rightInput}
                            value={this.state.maxEngineSize}
                            onChange={this.onChange}
                        />

                    </div>
                    <br/>
                    {userId ? <Button type="submit" name="save">
                        {this.state.id == 0 ? "Save" : "Update"}
                    </Button>:null}
                    <Button type="submit" name="search">
                        Search
                    </Button>
                    {
                        this.state.id == 0 ? null :
                        <Button type="submit" name="delete">
                            Delete
                        </Button>
                    }

                    <br/>
                </form>
            </div>
        )
    }

}

const searchColumn = {
    minWidth: '300px',
    background: '#EBEBEB',
    padding: "10px"
}
const emptyParameter = {
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
const leftInput = {
    flex: '5',
    padding: '5px',
    margin: '10px 10px 0px 0px',
    textAlign: 'center',
}

const rightInput = {
    flex: '5',
    padding: '5px',
    margin: '10px 0px 0px 10px'
}
const input = {
    width: "-moz-available",
}
Search.propTyoes = {
    addParameter: PropTypes.func.isRequired,
    updateSearch: PropTypes.func.isRequired,
    otsiKuulutusi: PropTypes.func.isRequired,
    deleteParameter: PropTypes.func.isRequired,
}

export default Search;