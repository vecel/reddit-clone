import '../styles/Signup.css';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Login = () => {

    const initialFormState = {
        username: '',
        password: '',
    }

    const [user, setUser] = useState(initialFormState)
    const [errorMessages, setErrorMessages] = useState([])
    const navigate = useNavigate()

    function handleChange(e) {
        const {name, value} = e.target
        setUser({...user, [name]: value})
    }

    function handleSubmit(e) {
        e.preventDefault()
        
        fetch('http://localhost:8080/api/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include',
            body: JSON.stringify(user),
        })
        .then(response => {
            if (response.ok) {
                navigate('/')
                return
            }
            response.json()
            .then(data => {
                setErrorMessages(data.error)
                setUser(initialFormState)
            })
        })
    }

    return (
        <main className="main">
            <form className="form" method="post" onSubmit={handleSubmit}>
            
                <span className="form__title">Log In</span>
            
                <p className="form__text form__text--gap-bottom">
                    By continuing, you are setting up a Reddit account and agree to our <a>User Agreement</a> and <a>Privacy Policy</a>. 
                </p>
                

                <div className="form__group">
                    <div className="form__field">
                        <label for="login-username" className="form__label">
                            <input id="login-username" className="form__input" type="text" placeholder="Username" name="username" value={user.username || ''} onChange={handleChange}/>
                        </label>
                    </div>
                    <div className="form__field">
                        <label for="login-password" className="form__label">
                            <input id="login-password" className="form__input" type="password" placeholder="Password" name="password" value={user.password || ''} onChange={handleChange} />
                        </label>
                    </div>
                    <p className="form__error-message">{errorMessages}</p>
                    
                    <span className="form__text form__text--gap-bottom">Forgot your <span className="form__link">username</span> or <span className="form__link">password</span>?</span>
                    

                    <div className="form__field form__field--justify-center">
                        <label for="login-button" className="form__label--submit">
                            <input id="login-button" className="form__input form__input--submit form__input--submit-hover" type="submit" value="Log In" />
                        </label>
                    </div>
                </div>

            <span className="form__text">New to Reddit? <a className="form__link" href="register">Sign Up</a></span>
            
        </form>
        </main>
    );
};

export default Login;
