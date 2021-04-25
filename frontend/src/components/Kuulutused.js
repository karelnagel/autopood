import React, {Component} from 'react';
import PropTypes from "prop-types";

class Kuulutused extends Component {

    render() {
        return <div style={kuulutusedColumn} id="kuulutused">{
            this.props.kuulutused.map((kuulutus) => (
                <a href={kuulutus.link} style={kuulutusDiv}>
                    <div style={picture}></div>
                    <div>
                        <div style={flex}>
                            <p style={bold}>{kuulutus.brand} {kuulutus.model}</p>
                            <p style={alignRight}>.     {kuulutus.price}$</p>
                        </div>
                        <div style={flex}>
                            <div>
                                <p>Aasta: {kuulutus.year}</p>
                                <p>Pood: {kuulutus.pood}</p>
                                <p>Type: {kuulutus.type}</p>
                            </div>
                            <div>
                                <p>Body type: {kuulutus.bodyType}</p>
                                <p>Gear type: {kuulutus.gearType}</p>
                                <p>Country: {kuulutus.country}</p>
                            </div>
                            <div>
                                <p>Mileage: {kuulutus.mileage}km</p>
                                <p>Engine size: {kuulutus.engineSize}L</p>
                                <p>Engine kw: {kuulutus.engineKW}kw</p>
                            </div>
                        </div>
                    </div>
                </a>
            ))
        }</div>
    }
}

const kuulutusDiv = {
    display: 'flex',
    background: "#FFFFFF",
    borderRadius: "15px",
    margin: "20px 0",
    padding: "10px",
}
const flex = {
    display: "flex",
}
const bold = {
    fontWeight: "bold",
}
const alignRight = {
    marginLef: "auto",
}
const kuulutusedColumn = {
    backgroundColor: "#C4C4C4",
    width: "-moz-available",
    padding: "20px"
}
const picture = {

    backgroundColor: "#C4C4C4",
    borderRadius: "15px",
    minHeight: "100px",
    minWidth: "150px",
    marginRight: "10px",

}
Kuulutused.propTypes = {
    kuulutused: PropTypes.array.isRequired
}
export default Kuulutused;