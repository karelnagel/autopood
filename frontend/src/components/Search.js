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
            this.setState(nextProps.parameter)
        else
            this.setState(emptyParameter);
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
                    <InputLabel style={selectInput} id="type-select-label">Tüüp</InputLabel>
                    <Select
                        displayEmpty
                        labelId="type-select-label"
                        name="type"
                        style={selectInput}
                        className='full-width'
                        value={this.state.type}
                        onChange={this.onChange}
                    >
                        <MenuItem value="">Kõik</MenuItem>
                        <MenuItem value='car'>Auto</MenuItem>
                        <MenuItem value='van'>Buss</MenuItem>
                    </Select>
                    <br/>
                    <TextField
                        type="text"
                        name="brand"
                        label="Mark"
                        style={input}
                        className='full-width'
                        value={this.state.brand}
                        onChange={this.onChange}
                    />
                    <br/>

                    <TextField
                        type="text"
                        name="model"
                        label="Mudel"
                        style={input}
                        className='full-width'
                        value={this.state.model}
                        onChange={this.onChange}
                    />
                    <br/>
                    <br/>
                    <InputLabel style={selectInput} id="country-select-label">Riik</InputLabel>
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
                    <br/>
                    <InputLabel style={selectInput} id="fuel-select-label">Kütus</InputLabel>
                    <Select
                        displayEmpty
                        labelId="fuel-select-label"
                        name="fuelType"
                        style={selectInput}
                        className='full-width'
                        value={this.state.fuelType}
                        onChange={this.onChange}
                    >
                        <MenuItem value="">Kõik</MenuItem>
                        <MenuItem value='Petrol'>Bensiin</MenuItem>
                        <MenuItem value='Diesel'>Diisel</MenuItem>
                        <MenuItem value='Hybrid'>Hübriid</MenuItem>
                        <MenuItem value='Gas'>Gaas</MenuItem>
                        <MenuItem value='Electric'>Elekter</MenuItem>
                    </Select>

                    <br/>
                    <br/>
                    <InputLabel style={selectInput} id="gear-select-label">Käigukast</InputLabel>
                    <Select
                        displayEmpty
                        labelId="fuel-select-label"
                        name="gearType"
                        style={selectInput}
                        className='full-width'
                        value={this.state.gearType}
                        onChange={this.onChange}
                    >
                        <MenuItem value="">Kõik</MenuItem>
                        <MenuItem value='Automatic'>Automaat</MenuItem>/
                        <MenuItem value='Manual'>Manuaal</MenuItem>
                    </Select>

                    <br/>
                    <br/>
                    <InputLabel style={selectInput} id="body-select-label">Kere</InputLabel>

                    <Select
                        displayEmpty
                        labelId="body-select-label"
                        name="bodyType"
                        style={selectInput}
                        className='full-width'
                        value={this.state.bodyType}
                        onChange={this.onChange}
                    >
                        <MenuItem value="">Kõik</MenuItem>
                        <MenuItem value='Sedan'>Sedaan</MenuItem>/
                        <MenuItem value='Hatchback'>Luukpära</MenuItem>
                        <MenuItem value='Cabriolet'>Kabriolett</MenuItem>
                        <MenuItem value='Touring'>Buss</MenuItem>
                        <MenuItem value='Station wagon'>Universaal</MenuItem>
                        <MenuItem value='Minivan'>Väike buss</MenuItem>
                        <MenuItem value='MPV'>Linnamaastur</MenuItem>
                        <MenuItem value='Commercial vehicle'>Tarbesõiduk</MenuItem>
                        <MenuItem value='Moped car'>Mopeedauto</MenuItem>
                        <MenuItem value='Caravan'>Karavan</MenuItem>
                        <MenuItem value='Coupé'>Kupee</MenuItem>
                        <MenuItem value='All-terrain'>Maastik</MenuItem>
                    </Select>

                    <br/>
                    <br/>
                    <div style={{display: 'flex'}}>
                        <TextField
                            type="number"
                            name="minPrice"
                            label="Min hind"
                            style={input}
                            className='full-width'
                            value={this.state.minPrice}
                            onChange={this.onChange}
                        />

                        <TextField
                            type="number"
                            name="maxPrice"
                            label="Max hind"
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
                            label="Min aasta"
                            style={input}
                            className='full-width'
                            value={this.state.minYear}
                            onChange={this.onChange}
                        />

                        <TextField
                            type="number"
                            name="maxYear"
                            label="Max aasta"
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
                            label="Min läbisõit"
                            style={input}
                            className='full-width'
                            value={this.state.minMileage}
                            onChange={this.onChange}
                        />

                        <TextField
                            type="number"
                            name="maxMileage"
                            label="Max läbisõit"
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
                            label="Min mootori kw"
                            style={input}
                            className='full-width'
                            value={this.state.minEngineKW}
                            onChange={this.onChange}
                        />

                        <TextField
                            type="number"
                            name="maxEngineKW"
                            label="Max mootori kw"
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
                            label="Min mootori L"
                            style={input}
                            className='full-width'
                            value={this.state.minEngineSize}
                            onChange={this.onChange}
                        />

                        <TextField
                            type="number"
                            name="maxEngineSize"
                            label="Max mootori L"
                            style={input}
                            className='full-width'
                            value={this.state.maxEngineSize}
                            onChange={this.onChange}
                        />

                    </div>
                    <br/>
                    <InputLabel style={selectInput} id="sortBy-select-label">Sordi</InputLabel>
                    <Select
                        displayEmpty
                        labelId="sortBy-select-label"
                        name="sortBy"
                        style={selectInput}
                        className='full-width'
                        value={this.state.sortBy}
                        onChange={this.onChange}
                    >
                        <MenuItem value="date">Vanemad kuulutused enne</MenuItem>
                        <MenuItem value='datedesc'>Uuemad kuulutused enne</MenuItem>
                        <MenuItem value='price'>Odavamad enne</MenuItem>
                        <MenuItem value='pricedesc'>Kallimad enne</MenuItem>
                        <MenuItem value='year'>Vanemad autod enne</MenuItem>
                        <MenuItem value='yeardesc'>Uuemad autod enne</MenuItem>
                        <MenuItem value='mileage'>Väiksema läbisõiduga enne</MenuItem>
                        <MenuItem value='mileagedesc'>Suurema läbisõiduga enne</MenuItem>
                    </Select>
                    <br/>
                    <div style={buttons}>
                        {userId ? <Button type="submit" name="save">
                            {this.state.id == 0 ? "Salvesta" : "Uuenda"}
                        </Button> : null}
                        {
                            this.state.id == 0 ? null :
                                <Button type="submit" name="delete">
                                    Kustuta
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
    gearType: '',
    bodyType: '',
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
    sortBy: "datedesc",

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