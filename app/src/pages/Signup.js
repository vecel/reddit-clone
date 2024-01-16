import '../styles/Signup.css'
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Signup() {

    const endpoint = "http://localhost:8080/api/register"
    const initialFormState = {
        username: '',
        email: '',
        password: '',
        passwordMatch: ''
    }
    const initialErrorsState = {
        username: '',
        email: '',
        password: '',
        passwordMatch: ''
    }

    const [user, setUser] = useState(initialFormState)
    const [invalidFields, setInvalidFields] = useState(initialErrorsState)
    const navigate = useNavigate()
    
    function handleChange(e) {
        const {name, value} = e.target
        setUser({...user, [name]: value})
    }

    function handleSubmit(e) {
        e.preventDefault()
        
        fetch(endpoint, {
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
                console.log(data)
                let errors = {}
                if (data.errors) {
                    data.errors.forEach(error => {
                        /* passwordMatch error field is undefined because of custom annotation in backend, 
                        so we have to handle it separately. That is temporary solution.
                        */
                        if (error.field === undefined) {
                            errors.passwordMatch = error.defaultMessage
                            return
                        }
                        errors[error.field] = errors[error.field] ? errors[error.field] + '\n' + error.defaultMessage : error.defaultMessage
                    })
                }
                return errors
            })
            .then(errors => {
                let hasErrors = false
                for (const key in errors) {
                    if (errors[key] !== '') {
                        hasErrors = true
                        break
                    }
                }
                setInvalidFields(errors)
                if (!hasErrors) {
                    navigate('/')
                }
            })
            
        })

        setUser(initialFormState)
    }

    return (
        <main className="main">
            <form className="form" autoComplete="off" method="post" onSubmit={handleSubmit}>
                <span className="form__title">
                    Sign Up
                </span>

                <p className="form__text form__text--gap-bottom">
                    By continuing, you are setting up a Reddit account and agree to our <a>User Agreement</a> and <a>Privacy Policy</a>. 
                </p>

                <label htmlFor="notifications" className="form__label-newsletter">
                    <input id="notifications" type="checkbox" />I agree to get emails about cool stuff on Reddit 
                </label>

                <div className="form__group">
                    <div className="form__field">
                        <label htmlFor="signup-username" className="form__label">
                            <input id="signup-username" className="form__input" type="text" placeholder="Username" required autoComplete="new-password" name="username" value={user.username || ''} onChange={handleChange}/>
                        </label>
                    </div>
                    <p className="form__error-message">{invalidFields.username}</p>
                    <div className="form__field">
                        <label htmlFor="signup-email" className="form__label">
                            <input id="signup-email" className="form__input" type="email" placeholder="Email" required autoComplete="new-password" name="email" value={user.email || ''} onChange={handleChange}/>
                        </label>
                    </div>
                    <p className="form__error-message">{invalidFields.email}</p>
                    <div className="form__field">
                        <label htmlFor="signup-password" className="form__label">
                            <input id="signup-password" className="form__input" type="password" placeholder="Password" required autoComplete="new-password" name="password" value={user.password || ''} onChange={handleChange}/>
                        </label>
                    </div>
                    <p className="form__error-message">{invalidFields.password}</p>
                    <div className="form__field">
                        <label htmlFor="confirm-signup-password" className="form__label">
                            <input id="confirm-signup-password" className="form__input" type="password" placeholder="Confirm password" required autoComplete="new-password" name="passwordMatch" value={user.passwordMatch || ''} onChange={handleChange}/>
                        </label>
                    </div>
                    <p className="form__error-message">{invalidFields.passwordMatch}</p>
                    <div className="form__field form__field--justify-center">
                        <label htmlFor="signup-button" className="form__label--submit">
                            <input id="signup-button" className="form__input form__input--submit form__input--submit-hover" type="submit" value="Sign Up" />
                        </label>
                    </div>
                </div>

                <span className="form__text">Already a redditor? <a className="form__link" href="login">Log in</a></span>
                
            </form>
        </main>

    );
};

export default Signup;
