import React from 'react';
import MenuIcon from '@material-ui/icons/Menu';
function Header(){
    return(
        <header style={header}>
            <MenuIcon/>
            <h2>Create parameter</h2>
            <p></p>
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