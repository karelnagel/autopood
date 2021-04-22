import React, {Component} from 'react';
import PropTypes from "prop-types";

class Kuulutused extends Component {

    render() {
        return this.props.kuulutused.map((kuulutus) => (
            <div>
                <div style={kuulutusElement}>

                    <p style={oneKuulutus}>{kuulutus.brand}</p>
                    <p style={oneKuulutus}>{kuulutus.model}</p>
                    <p style={oneKuulutus}>{kuulutus.year}</p>
                    <p style={oneKuulutus}>{kuulutus.country}</p>
                    <p style={oneKuulutus}>{kuulutus.price}</p>

                </div>

                <a href={kuulutus.link}>{kuulutus.link}</a>
                <br/>
                <br/>
            </div>
        ));
    }
}

const kuulutusElement = {
    display: 'flex',
    justifyContent: 'space-between',

}
const oneKuulutus = {
    display: 'flex',
    justifyContent: 'space-between',

}
Kuulutused.propTypes = {
    kuulutused: PropTypes.array.isRequired
}
export default Kuulutused;