import React, {Component} from 'react';
import PropTypes from "prop-types";
import {Button, InputLabel, MenuItem, Select, TextField} from "@material-ui/core";

class Search extends Component {
    constructor(props) {
        super(props);
        this.state = emptyParameter
        this.onSubmit = this.onSubmit.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    componentWillReceiveProps(nextProps) {
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
            const element = document.getElementById("user");
            element.scrollIntoView();
        } else if (button === "search") {
            this.props.otsiKuulutusi(newParameter);
            const element = document.getElementById("kuulutused");
            element.scrollIntoView();
        } else {
            this.props.deleteParameter(newParameter.id)
            newParameter = emptyParameter;
            const element = document.getElementById("user");
            element.scrollIntoView();
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
                        className='full-width'
                        value={this.state.name}
                        onChange={this.onChange}
                    />
                    <br/>

                    <TextField
                        type="text"
                        name="type"
                        label="Type"
                        style={input}
                        className='full-width'
                        value={this.state.type}
                        onChange={this.onChange}
                    />
                    <br/>
                    <TextField
                        type="text"
                        name="brand"
                        label="Brand"
                        style={input}
                        className='full-width'
                        value={this.state.brand}
                        onChange={this.onChange}
                    />
                    <br/>

                    <TextField
                        type="text"
                        name="model"
                        label="Model"
                        style={input}
                        className='full-width'
                        value={this.state.model}
                        onChange={this.onChange}
                    />
                    <br/>
                    <TextField
                        type="text"
                        name="fuelType"
                        label="Fuel type"
                        style={input}
                        className='full-width'
                        value={this.state.fuelType}
                        onChange={this.onChange}
                    />
                    <br/>
                    <br/>
                    <InputLabel style={selectInput} id="country-select-label">Country</InputLabel>
                    <Select
                        displayEmpty
                        labelId="country-select-label"
                        name="country"
                        style={selectInput}
                        className='full-width'
                        value={this.state.country}
                        onChange={this.onChange}
                    >
                        <MenuItem value="">Kõik</MenuItem>
                        <MenuItem value='Estonia'>Eesti</MenuItem>
                        <MenuItem value='Finland'>Soome</MenuItem>
                    </Select>

                    <br/>
                    <div style={{display: 'flex'}}>
                        <TextField
                            type="number"
                            name="minPrice"
                            label="Min price"
                            style={input}
                            className='full-width'
                            value={this.state.minPrice}
                            onChange={this.onChange}
                        />

                        <TextField
                            type="number"
                            name="maxPrice"
                            label="Max price"
                            style={input}
                            className='full-width'
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
                            className='full-width'
                            value={this.state.minYear}
                            onChange={this.onChange}
                        />

                        <TextField
                            type="number"
                            name="maxYear"
                            label="Max year"
                            style={input}
                            className='full-width'
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
                            className='full-width'
                            value={this.state.minMileage}
                            onChange={this.onChange}
                        />

                        <TextField
                            type="number"
                            name="maxMileage"
                            label="Max mileage"
                            style={input}
                            className='full-width'
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
                            className='full-width'
                            value={this.state.minEngineKW}
                            onChange={this.onChange}
                        />

                        <TextField
                            type="number"
                            name="maxEngineKW"
                            label="Max engine kw"
                            style={input}
                            className='full-width'
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
                            className='full-width'
                            value={this.state.minEngineSize}
                            onChange={this.onChange}
                        />

                        <TextField
                            type="number"
                            name="maxEngineSize"
                            label="Max engine size"
                            style={input}
                            className='full-width'
                            value={this.state.maxEngineSize}
                            onChange={this.onChange}
                        />

                    </div>
                    <br/>
                    <div style={buttons}>
                        {userId ? <Button type="submit" name="save">
                            {this.state.id == 0 ? "Save" : "Update"}
                        </Button> : null}
                        {
                            this.state.id == 0 ? null :
                                <Button type="submit" name="delete">
                                    Delete
                                </Button>
                        }
                        <Button type="submit" name="search">
                            Search
                        </Button>

                    </div>
                    <br/>
                </form>
            </div>
        )
    }

}

const buttons = {
    justifyContent: 'space-between',
    display: 'flex',
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
const input = {
    margin: '5px 10px',
}
const selectInput = {
    margin: '0 10px',
}
Search.propTyoes = {
    addParameter: PropTypes.func.isRequired,
    updateSearch: PropTypes.func.isRequired,
    otsiKuulutusi: PropTypes.func.isRequired,
    deleteParameter: PropTypes.func.isRequired,
}

export default Search;