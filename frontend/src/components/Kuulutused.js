import React, {Component} from 'react';
import PropTypes from "prop-types";


class Kuulutused extends Component {

    render() {
        const isMobile = window.innerWidth < 1024;
        return <div style={kuulutusedColumn} className='full-width' id="kuulutused">{
            this.props.kuulutused.length != 0 ?
                this.props.kuulutused.map((kuulutus) => (
                    <a href={kuulutus.link} style={isMobile ? kuulutusDivPhone : kuulutusDiv} key={kuulutus.id}>
                        {isMobile ? null :
                            <img className="picture" src={kuulutus.picture ? kuulutus.picture : "/logo.png"}
                                 style={picture}></img>}
                        <div>
                            <div style={flex}>
                                <p style={bold}>{kuulutus.brand} {kuulutus.model}</p>
                                <p style={price}>{kuulutus.price}€</p>
                            </div>
                            <div style={flex}>
                                {isMobile ? <img src={kuulutus.picture ? kuulutus.picture : "/logo.png"}
                                                 style={picturePhone}></img> : null}
                                <div style={isMobile ? null : flex}>
                                    <div style={section}>
                                        {kuulutus.year ? <p>Aasta: {kuulutus.year}</p> : null}
                                        {kuulutus.pood ? <p>Pood: {kuulutus.pood}</p> : null}
                                        {kuulutus.type ? <p>Type: {kuulutus.type}</p> : null}
                                    </div>
                                    <div style={section}>
                                        {kuulutus.bodyType ? <p>Body type: {kuulutus.bodyType}</p> : null}
                                        {kuulutus.gearType ? <p>Gear type: {kuulutus.gearType}</p> : null}
                                        {kuulutus.country ? <p>Country: {kuulutus.country}</p> : null}
                                    </div>
                                    <div style={section}>
                                        {kuulutus.mileage ? <p>Mileage: {kuulutus.mileage}km</p> : null}
                                        <p>Engine: {kuulutus.engineSize}L {kuulutus.engineKW}kw</p>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </a>
                )) : <h3 style={startMessage}>Sisesta soovitud parameetrid ja vajuta otsi!</h3>
        }</div>
    }
}

const startMessage = {
    textAlign: "center"
}
const section = {
    maxWidth: '200px',
    marginRight: '15px',
}
const kuulutusDiv = {
    display: 'flex',
    background: "#FFFFFF",
    borderRadius: "15px",
    margin: "20px 20px",
    position: 'relative',
}
const price = {
    position: "absolute",
    right: '10px',
    color: 'green',
    fontWeight: 'bold'
}
const kuulutusDivPhone = {
    display: 'flex',
    background: "#FFFFFF",
    margin: "10px 0",
    padding: "10px",
    position: 'relative'
}
const flex = {
    display: "flex",
}
const bold = {
    fontWeight: "bold",
    width: '70%',
    whiteSpace: 'inherit',
    lineHeight: 'inherit',
}
const kuulutusedColumn = {
    backgroundColor: "#C4C4C4",
    overflowY: "hidden",
}
const picture = {
    backgroundColor: "#C4C4C4",
    borderRadius: "15px",
    marginRight: "20px",
    objectFit: "cover",
}
const picturePhone = {
    backgroundColor: "#C4C4C4",
    borderRadius: "15px",
    height: "150px",
    width: "180px",
    marginRight: "10px",
    objectFit: "cover",
}
Kuulutused.propTypes = {
    kuulutused: PropTypes.array.isRequired
}
export default Kuulutused;