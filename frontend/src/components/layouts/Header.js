import React from 'react';
import MenuIcon from '@material-ui/icons/Menu';
function Header(){
    return(
        <header style={header}>
            <h2>Create parameter</h2>
        </header>
    );
}

const header = {
    background:"#3b3e66",
    color: "#fff",
    display:"flex",
    alignItems:'center',
    justifyContent:'space-between',

}
export default Header;