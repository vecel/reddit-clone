import '../styles/Header.css';
import { Search, AccountCircle, Logout } from '@mui/icons-material';
import { useState } from 'react';

function Header({loggedUser}) {

    const [logged, setLogged] = useState(loggedUser !== null)

    function logout() {
        fetch('http://localhost:8080/api/logout', {
            method: 'GET',
            credentials: 'include'
        })
        .then(response => setLogged(false))
    }

  return (
    <header className="header">

        <div className="header__title">
            <div className="header__title-logo"></div>
            <span className="header__title-text">Redd<span className="header__title-text header__title-text--primary-color">i</span>tClone</span>
        </div>
    
        <div className="header__search">
            <label for="search" className="header__search-bar header__search-bar--hover">
                <Search className="header__search-icon"/>
                <input id="search" className="header__search-input" placeholder="Search Reddit Clone" />
            </label>
        </div>
    
        {logged ?
            <div className="header__user">
                <AccountCircle className="header__user-icon md-36"/>
                <span className="header__user-username">{loggedUser.username}</span>
                <div className="header__user-sign-out" onClick={logout}>
                    <Logout />
                </div>
            </div>  :
            <div className="header__login">
                <a className="header__login-anchor header__login-anchor--outlined header__login-anchor--outlined-hover" href="login">Log In</a>
                <a className="header__login-anchor header__login-anchor--prominent header__login-anchor--prominent-hover" href="register">Sign Up</a>
            </div>
        }
    

    </header>

  );
}

export default Header;