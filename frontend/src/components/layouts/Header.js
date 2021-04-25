import React from 'react';
import {IconButton} from "@material-ui/core";
import DriveEtaIcon from '@material-ui/icons/DriveEta';
function Header() {
    return (
        <header style={header}>
            <a href='/'>
                <p>Car Watch</p>
            </a>
            <IconButton href="/main" style={icon}>
                <DriveEtaIcon />
            </IconButton>
        </header>
    );
}

const header = {
    display: 'flex',
    textAlign:'center',
    padding: '0 15px',
    fontWeight: 'bold',
    fontSize:'24px',
}
const icon={
    marginLeft:'auto',
    color:'black',
}
export default Header;