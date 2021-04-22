import React from "react";
import ReactDOM from "react-dom";
import {UserPage} from '../../../frontend/src/pages/UserPage';
import {Index} from '../../../frontend/src/pages/Index';
import {SearchPage} from "../../../frontend/src/pages/SearchPage";

const userId = new URLSearchParams(window.location.search).get("userId");
const paraId = new URLSearchParams(window.location.search).get("paraId");
const page = new URLSearchParams(window.location.search).get("page");
if (userId)
    ReactDOM.render(<UserPage/>, document.querySelector("#app"));
else if (paraId || page)
    ReactDOM.render(<SearchPage/>, document.querySelector("#app"));
else
    ReactDOM.render(<Index/>, document.querySelector("#app"));