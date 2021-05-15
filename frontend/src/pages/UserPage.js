import React, {Component} from "react";
import Header from '../components/Header';
import User from '../components/User';
import axios from "axios";
import Search from "../components/Search";
import Kuulutused from "../components/Kuulutused";

export class UserPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: {
                parameters: []
            },
            kuulutused: []
        }
    }

    componentDidMount() {
        const userId = new URLSearchParams(window.location.search).get("userId");
        const paraId = new URLSearchParams(window.location.search).get("paraId");
        if (userId) {
            axios.get('/api/users/' + userId)
                .then(response => {
                    this.setState({
                        user: response.data,
                        parameter: response.data.parameters.find(x => x.id == paraId)
                    })
                })
            if (paraId) {
                axios.get('/api/kuulutused?paraId=' + paraId)
                    .then((response) => {
                            this.setState({kuulutused: response.data})
                        }
                    );
            }
        }
    }


    updateSearch = (newParameter) => {
        this.setState({parameter: newParameter})
        if (newParameter){
            this.otsiKuulutusi(newParameter)
        }
    }

    deleteParameter = (id) => {
        const userId = new URLSearchParams(window.location.search).get("userId");
        axios.delete('/api/users/' + userId + '/parameters/' + id)
            .then(response => {
                    const updatedUser = this.state.user;
                    updatedUser.parameters = [...this.state.user.parameters.filter(p => p.id !== id)];
                    this.setState({user: updatedUser})
                }
            );
    }

    addParameter = (newParameter) => {
        const id = newParameter.id
        const userId = new URLSearchParams(window.location.search).get("userId");
        if (id === 0) {
            axios.post('/api/users/' + userId + '/parameters/', newParameter)
                .then(
                    (response) => {
                        const updatedUser = this.state.user;
                        updatedUser.parameters = [...this.state.user.parameters, response.data];

                        this.setState({user: updatedUser})
                    }
                );
        } else {
            axios.put('/api/users/' + userId + '/parameters/' + id, newParameter)
                .then(
                    (response) => {
                        const updatedUser = this.state.user;
                        updatedUser.parameters = [...this.state.user.parameters.filter(p => p.id !== id), response.data];
                        this.setState({user: updatedUser})
                    }
                );
        }
    }
    otsiKuulutusi = (parameter) => {
        if (!parameter.sortBy)
            parameter.sortBy = "datedesc"
        axios.post('/api/kuulutused/?sortBy='+parameter.sortBy, parameter)
            .then(
                (response) => {
                    this.setState({kuulutused: response.data})
                }
            );

    }

    render() {
        const userId = new URLSearchParams(window.location.search).get("userId");
        return (
            <div className="container">
                <Header/>
                <main>
                    {userId ?
                        <User style={column} user={this.state.user} updateSearch={this.updateSearch} selected={this.state.parameter ? this.state.parameter.id : 0}/> : null}
                    <Search style={column} parameter={this.state.parameter} updateSearch={this.updateSearch}
                            otsiKuulutusi={this.otsiKuulutusi} addParameter={this.addParameter}
                            deleteParameter={this.deleteParameter}/>
                    <Kuulutused kuulutused={this.state.kuulutused}/>
                </main>
            </div>
        );
    }
}

const column = {
    display: "inline-block"
}

