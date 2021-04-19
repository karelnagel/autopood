import React, {Component, useCallback} from "react";
import ReactDOM from "react-dom";
import Header from '../../../frontend/src/components/layouts/Header';
import RegisterUser from '../../../frontend/src/components/register-user/RegisterUser';
import Users from '../../../frontend/src/components/users/Users';
import axios from "axios";
export class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            users:[
            ]
        }
    }

    componentDidMount() {
        const urlParams = new URLSearchParams(window.location.search);
        axios.get('/api/users/'+urlParams.get('userId')+'/parameters')
            .then(response => this.setState({users:response.data}))
    }

    //Deleting User
    removeUser = (id) =>{
        const urlParams = new URLSearchParams(window.location.search);
        axios.delete('/api/users/'+urlParams.get('userId')+'/parameters/'+id)
            .then(
                response =>this.setState( //Updating UI
                    {users: [...this.state.users.filter(
                            user => user.id !== id
                        )]}
                )
            );
    }

    addUser = (newUser) =>{
        const urlParams = new URLSearchParams(window.location.search);
        axios.post('/api/users/'+urlParams.get('userId')+'/parameters/',newUser)
            .then(
                (response) =>{
                    console.log(response.data);
                    this.setState({users:[...this.state.users,response.data]})
                }
            );
    }

    render() {
        return (
            <div className="container">
                <Header/>
                <RegisterUser addUser={this.addUser}/>
                <Users users={this.state.users} removeUser={this.removeUser}/>
            </div>
        );
    }
}


export default App;

ReactDOM.render(<App />, document.querySelector("#app"));