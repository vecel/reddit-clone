import '../styles/Header.css';
import { Search, AccountCircle, Logout } from '@mui/icons-material';

function Header() {
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
    
        <div className="header__login">
            <a className="header__login-anchor header__login-anchor--outlined header__login-anchor--outlined-hover" href="login">Log In</a>
            <a className="header__login-anchor header__login-anchor--prominent header__login-anchor--prominent-hover" href="register">Sign Up</a>
        </div>
    
        <div className="header__user">
            <AccountCircle className="header__user-icon md-36"/>
            <span className="header__user-username">Wez username z sesji</span>
            <a className="header__user-sign-out" href="/logout">
                <Logout />
            </a>
        </div> 
    </header>

  );
}

export default Header;