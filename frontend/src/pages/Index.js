import React, {Component} from "react";
import Header from "../components/layouts/Header";

export class Index extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="index">
                <Header/>
                <p>Parim auto ostu portaal maailmas</p>
            </div>
        );
    }
}
