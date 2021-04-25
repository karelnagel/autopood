import React, {Component} from 'react';
import PropTypes from "prop-types";

class Kuulutused extends Component {

    render() {
        const isMobile = window.innerWidth < 480;
        return <div style={kuulutusedColumn} className='full-width' id="kuulutused">{
            this.props.kuulutused.map((kuulutus) => (
                <a href={kuulutus.link} style={isMobile ? kuulutusDivPhone : kuulutusDiv} key={kuulutus.id}>
                    {isMobile ? null : <img src="https://picsum.photos/200" style={picture}></img>}
                    <div>
                        <div style={flex}>
                            <p style={bold}>{kuulutus.brand} {kuulutus.model}</p>
                            <p style={price}>{kuulutus.price}€</p>
                        </div>
                        <div style={flex}>
                            {isMobile ? <img src="https://picsum.photos/150" style={picturePhone}></img> : null}
                            <div style={isMobile?null : flex}>
                                <div style={section}>
                                    {kuulutus.year ? <p>Aasta: {kuulutus.year}</p>:null}
                                    {kuulutus.pood ? <p>Pood: {kuulutus.pood}</p>:null}
                                        {kuulutus.type ? <p>Type: {kuulutus.type}</p>:null}
                                </div>
                                <div style={section}>
                                    {kuulutus.bodyType ? <p>Body type: {kuulutus.bodyType}</p>:null}
                                    {kuulutus.gearType ? <p>Gear type: {kuulutus.gearType}</p>:null}
                                    {kuulutus.country ? <p>Country: {kuulutus.country}</p>:null}
                                </div>
                                <div style={section}>
                                    {kuulutus.mileage ? <p>Mileage: {kuulutus.mileage}km</p>:null}
                                    <p>Engine: {kuulutus.engineSize}L {kuulutus.engineKW}kw</p>
                                </div>
                            </div>

                        </div>
                    </div>
                </a>
            ))
        }</div>
    }
}
const section={
    width:'200px',
    marginRight:'20px',
}
const kuulutusDiv = {
    display: 'flex',
    background: "#FFFFFF",
    borderRadius: "15px",
    margin: "20px 20px",
    padding: "10px",
    position:'relative'
}
const price={
    position: "absolute",
    right:'10px',
    color: 'green',
    fontWeight: 'bold'
}
const kuulutusDivPhone = {
    display: 'flex',
    background: "#FFFFFF",
    margin: "10px 0",
    padding: "10px",
    position:'relative'
}
const flex = {
    display: "flex",
}
const bold = {
    fontWeight: "bold",
    width: '80%',
    whiteSpace: 'inherit',
lineHeight: 'inherit',
}
const alignRight = {
    marginLef: "auto",
}
const kuulutusedColumn = {
    backgroundColor: "#C4C4C4",
}
const picture = {
    backgroundColor: "#C4C4C4",
    borderRadius: "15px",
    height: "200px",
    width: "200px",
    marginRight: "10px",

}
const picturePhone = {
    backgroundColor: "#C4C4C4",
    borderRadius: "15px",
    height: "150px",
    width: "150px",
    marginRight: "10px",
}
Kuulutused.propTypes = {
    kuulutused: PropTypes.array.isRequired
}
export default Kuulutused;