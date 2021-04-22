import React, {Component} from "react";
import Header from '../../../frontend/src/components/layouts/Header';
import Parameters from '../../../frontend/src/components/Parameters';
import axios from "axios";
import Kuulutused from "../components/Kuulutused";

export class SearchPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            kuulutused: []
        }
    }

    componentDidMount() {
        const paraId = new URLSearchParams(window.location.search).get("paraId");
        if (paraId) {
            axios.get('/api/kuulutused?paraId=' + paraId)
                .then(
                    (response) => {
                        console.log(response.data);
                        this.setState({kuulutused: response.data})
                    }
                );
        }
    }

    search = (parameter) => {
        const paraId = new URLSearchParams(window.location.search).get('paraId');
        axios.post('/api/kuulutused/', parameter)
            .then(
                (response) => {
                    console.log(response.data);
                    this.setState({kuulutused: response.data})
                }
            );

    }

    render() {
        const paraId = new URLSearchParams(window.location.search).get("paraId");
        if (paraId) {
            return (
                <div className="container">
                    <Header/>
                    <Kuulutused kuulutused={this.state.kuulutused}/>
                </div>
            );
        } else {
            return (
                <div className="container">
                    <Header/>
                    <Parameters postParameter={this.search}/>
                    <Kuulutused kuulutused={this.state.kuulutused}/>
                </div>
            );
        }
    }
}
