import React, {Component} from "react";
import Header from '../../../frontend/src/components/layouts/Header';
import Parameters from '../../../frontend/src/components/Parameters';
import Users from '../../../frontend/src/components/Users';
import axios from "axios";

export class UserPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            parameters: []
        }
    }

    componentDidMount() {
        const userId = new URLSearchParams(window.location.search).get("userId");
        axios.get('/api/users/' + userId + '/parameters')
            .then(response => this.setState({parameters: response.data}))
    }

    //Deleting User
    deleteParameter = (id) => {
        const userId = new URLSearchParams(window.location.search).get("userId");
        axios.delete('/api/users/' + userId + '/parameters/' + id)
            .then(
                response => this.setState( //Updating UI
                    {
                        parameters: [...this.state.parameters.filter(p => p.id !== id)]
                    }
                )
            );
    }

    addParameter = (newParameter) => {
        const id = newParameter.id
        const userId = new URLSearchParams(window.location.search).get("userId");
        if (id === 0) {
            axios.post('/api/users/' + userId + '/parameters/', newParameter)
                .then(
                    (response) => {
                        console.log(response.data);
                        this.setState({parameters: [...this.state.parameters, response.data]})
                    }
                );
        } else {
            axios.put('/api/users/' + userId + '/parameters/' + id, newParameter)
                .then(
                    (response) => {
                        console.log(response.data);
                        this.setState({parameters: [...this.state.parameters.filter(p => p.id !== id), response.data]})
                    }
                );
        }
    }

    render() {
        return (
            <div className="container">
                <Header/>
                <Parameters parameters={this.state.parameters} postParameter={this.addParameter}/>
                <Users users={this.state.parameters} removeUser={this.deleteParameter}/>
            </div>
        );
    }
}
