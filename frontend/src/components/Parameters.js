import React, {Component} from 'react';
import PropTypes from "prop-types";

class Parameters extends Component {
    emptyParameter() {
        return {
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
    }

    constructor(props) {
        super(props);

        this.state = this.emptyParameter()

        this.onSubmit = this.onSubmit.bind(this);
    }

    componentWillReceiveProps(nextProps) {
        const paraId = new URLSearchParams(window.location.search).get('paraId');
        if (paraId && nextProps.parameters) {
            const parameter = nextProps.parameters.find(x => x.id == paraId);
            if (parameter) {
                if (this.state) {
                    console.log(paraId);
                    this.state = parameter;
                }
            }
        }
    }

    onChange = (e) => {
        this.setState({[e.target.name]: e.target.value})
    }

    onSubmit(e) {
        e.preventDefault();

        let newParameter = this.state;
        this.props.postParameter(newParameter);
        const page = new URLSearchParams(window.location.search).get('page');
        if (!page)
            this.setState(this.emptyParameter());
    }

    render() {
        const paraId = new URLSearchParams(window.location.search).get('paraId');
        const userId = new URLSearchParams(window.location.search).get('userId');
        return (
            <form onSubmit={this.onSubmit}>
                {paraId ? <h1>Editing {this.state.id}</h1> : <div></div>}
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
                <div style={{display: 'flex'}}>
                    <input
                        type="number"
                        name="minPrice"
                        placeholder="Min price"
                        style={leftInput}
                        value={this.state.minPrice}
                        onChange={this.onChange}
                    />

                    <input
                        type="number"
                        name="maxPrice"
                        placeholder="Max price"
                        style={rightInput}
                        value={this.state.maxPrice}
                        onChange={this.onChange}
                    />
                </div>
                <div style={{display: 'flex'}}>
                    <input
                        type="number"
                        name="minYear"
                        placeholder="Min year"
                        style={leftInput}
                        value={this.state.minYear}
                        onChange={this.onChange}
                    />

                    <input
                        type="number"
                        name="maxYear"
                        placeholder="Max year"
                        style={rightInput}
                        value={this.state.maxYear}
                        onChange={this.onChange}
                    />
                </div>
                <div style={{display: 'flex'}}>
                    <input
                        type="number"
                        name="minMileage"
                        placeholder="Min mileage"
                        style={leftInput}
                        value={this.state.minMileage}
                        onChange={this.onChange}
                    />

                    <input
                        type="number"
                        name="maxMileage"
                        placeholder="Max mileage"
                        style={rightInput}
                        value={this.state.maxMileage}
                        onChange={this.onChange}
                    />
                </div>
                <div style={{display: 'flex'}}>
                    <input
                        type="number"
                        name="minEngineKW"
                        placeholder="Min engine kw"
                        style={leftInput}
                        value={this.state.minEngineKW}
                        onChange={this.onChange}
                    />

                    <input
                        type="number"
                        name="maxEngineKW"
                        placeholder="Max engine kw"
                        style={rightInput}
                        value={this.state.maxEngineKW}
                        onChange={this.onChange}
                    />
                </div>
                <div style={{display: 'flex'}}>
                    <input
                        type="number"
                        name="minEngineSize"
                        placeholder="Min engine size"
                        style={leftInput}
                        value={this.state.minEngineSize}
                        onChange={this.onChange}
                    />

                    <input
                        type="number"
                        name="maxEngineSize"
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
                    value={paraId ? "Update" : "Save"}
                    className="btn"
                />
                <br/>
                <br/>
                {paraId ? <a href={"/?userId="+userId}>New parameter</a> : null}

                <br/>
            </form>
        )
    }

}

const leftInput = {
    flex: '5'
    ,
    padding: '5px'
    ,
    margin: '10px 10px 0px 0px'
}

const rightInput =
    {
        flex: '5',
        padding: '5px',
        margin: '10px 0px 0px 10px'
    }

Parameters.propTyoes =
    {
        postParameter: PropTypes.func.isRequired,
    }

export default Parameters;