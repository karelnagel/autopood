import React, {Component} from "react";
import Header from "../components/layouts/Header";
import {Button} from "@material-ui/core";

export class Index extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
                <div style={main} className='fancy-color'>
                    <div style={content}>
                        <h1>CarWatch</h1>
                        <br/>
                        <br/>
                        <p>Leia endale soodsaim auto.</p>
                        <br/>
                        <Button variant='contained' href="http://m.me/CarVVatch">Tee oma konto siin</Button>
                        <br/>
                        <br/>
                        <Button variant='contained' href="main">Otsi</Button>
                    </div>
                </div>
        );
    }
}
const main={
    height:"100vh",
    textAlign:"center",
    display:'flex',
}
const content={
    margin:'auto'
}