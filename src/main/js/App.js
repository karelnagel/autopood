import React, {Component} from "react";
import ReactDOM from "react-dom";
import Header from '../../../frontend/src/components/layouts/Header';
import RegisterUser from '../../../frontend/src/components/register-user/RegisterUser';
import Users from '../../../frontend/src/components/users/Users';
import axios from "axios";

export class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            users: []
        }
    }

    componentDidMount() {
        const urlParams = new URLSearchParams(window.location.search);
        axios.get('/api/users/' + urlParams.get('userId') + '/parameters')
            .then(response => this.setState({users: response.data}))
    }

    //Deleting User
    removeUser = (id) => {
        const urlParams = new URLSearchParams(window.location.search);
        axios.delete('/api/users/' + urlParams.get('userId') + '/parameters/' + id)
            .then(
                response => this.setState( //Updating UI
                    {
                        users: [...this.state.users.filter(user => user.id !== id)]
                    }
                )
            );
    }

    addUser = (newUser) => {
        const id = newUser.id
        const urlParams = new URLSearchParams(window.location.search);
        if (id === 0) {
            axios.post('/api/users/' + urlParams.get('userId') + '/parameters/', newUser)
                .then(
                    (response) => {
                        console.log(response.data);
                        this.setState({users: [...this.state.users, response.data]})
                    }
                );
        } else {
            axios.put('/api/users/' + urlParams.get('userId') + '/parameters/' + id, newUser)
                .then(
                    (response) => {
                        console.log(response.data);
                        this.setState({users: [...this.state.users.filter(user => user.id !== id), response.data]})//imo peaks kustutama vana useri 'ra aga js
                    }
                );
        }
    }

    render() {
        return (
            <div className="container">
                <Header/>
                <RegisterUser users={this.state.users} addUser={this.addUser}/>
                <Users users={this.state.users} removeUser={this.removeUser}/>
            </div>
        );
    }
}


export default App;

ReactDOM.render(<App/>, document.querySelector("#app"));