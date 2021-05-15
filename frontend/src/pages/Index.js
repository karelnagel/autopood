import React, {Component} from "react";
import Header from "../components/Header";
import {Button} from "@material-ui/core";
import logo from '../components/logoroundCW.png';
import Background from '../components/newbg.jpg';
export class Index extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
                <div style={main}>
                    <div style={fancycolor}>
                    <div style={content}>
                        <img style={logostyle} src={logo}/>
                        <h1>CarWatch</h1>
                        <br/>
                        <p style={txt1}>Meie abiga saate autot ostes parima tehingu!
                            <br/>
                        </p>
                        <br/>
                        <Button style={btn} variant='contained' href="http://m.me/CarVVatch">Tee oma konto siin</Button>
                        <br/>
                        <br/>
                        <Button style={btn} variant='contained' href="main">Otsi</Button>
                    </div>
                    </div>
                </div>
        );
    }
}
const txt1={
    color:"#e0e0e0",
    fontSize:"22px",
    fontFamily:"monospace"
}
const btn={
}
const logostyle={
    height: "200px",
    width: "200px",
    margin: "5%",
}
const fancycolor={
    height:"100vh",
    width:"100vw",
    backgroundImage: "linear-gradient(to right, rgba(100,50,0,0.4), rgba(0,0,0,0.4))",
}
const main={
    backgroundImage: "url(" + Background + ")",
    height:"100%",
    width:"100%",
    textAlign:"center",
    backgroundRepeat: 'no-repeat',
    backgroundSize: "cover",
    justifyContent: "center",
}
const content={
    margin:'auto',
}