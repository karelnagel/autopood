import React from "react";
import ReactDOM from "react-dom";
import {UserPage} from '../../../frontend/src/pages/UserPage';
import {Index} from '../../../frontend/src/pages/Index';

const route = window.location.pathname
if (route==="/main")
    ReactDOM.render(<UserPage/>, document.querySelector("#app"));
else
    ReactDOM.render(<Index/>, document.querySelector("#app"));