import React, {Component} from 'react';
import PropTypes from "prop-types";

class Parameter extends Component {
    render() {
        const userId = new URLSearchParams(window.location.search).get('userId');
        return (
            <div className={this.props.selected === this.props.parameter.id ? 'fancy-color' : null} style={infoStyle}
                 onClick={(e) => {
                     this.props.updateSearch(this.props.parameter)
                 }}>
                <div style={line}>
                    <p>{this.props.parameter.brand} {this.props.parameter.model}</p>
                    <p style={alignEnd}>{this.props.parameter.minPrice} - {this.props.parameter.maxPrice} $</p>
                </div>
                <div style={line}>
                    <p>{this.props.parameter.minEngineKW} - {this.props.parameter.maxEngineKW} kw</p>
                    <p style={alignEnd}>{this.props.parameter.minYear} - {this.props.parameter.maxYear} aasta</p>
                </div>
            </div>
        );
    }
}

const infoStyle = {
    background: '#C4C4C4',
    borderRadius: '5px',
    padding: '10px',
    lineHeight: '0px',
    margin: '10px 0',
}
const infoStyleSelected = {
    background: 'blue',
    borderRadius: '5px',
    padding: '10px',
    lineHeight: '0px',
    margin: '10px 0',
}
const line = {
    display: 'flex'
}
const alignEnd = {
    marginLeft: 'auto',
}
const selected = {
    color: "blue",
}

Parameter.propTypes = {
    parameter: PropTypes.object.isRequired,
    updateSearch: PropTypes.func.isRequired,
    selected: PropTypes.number.isRequired
}
export default Parameter;