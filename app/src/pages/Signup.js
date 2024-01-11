import '../styles/Signup.css'

function Signup() {
    return (
        <main className="main">
        <form className="form" action="#" autocomplete="off">
            <span className="form__title">
                Sign Up
            </span>

            <p className="form__text form__text--gap-bottom">
                By continuing, you are setting up a Reddit account and agree to our <a>User Agreement</a> and <a>Privacy Policy</a>. 
            </p>

            <label for="notifications" className="form__label-newsletter">
                <input id="notifications" type="checkbox" />I agree to get emails about cool stuff on Reddit 
            </label>

            <div className="form__group">
				<div className="form__field">
                    <label for="signup-username" className="form__label">
                        <input id="signup-username" className="form__input" type="text" placeholder="Username" required autocomplete="new-password" />
                    </label>
                </div>
                <p className="form__error-message"></p>
                <div className="form__field">
                    <label for="signup-email" className="form__label">
                        <input id="signup-email" className="form__input" type="email" placeholder="Email" required autocomplete="new-password" />
                    </label>
                </div>
                <p className="form__error-message"></p>
                <div className="form__field">
                    <label for="signup-password" className="form__label">
                        <input id="signup-password" className="form__input" type="password" placeholder="Password" required autocomplete="new-password" />
                    </label>
                </div>
                <p className="form__error-message"></p>
                <div className="form__field">
                    <label for="confirm-signup-password" className="form__label">
                        <input id="confirm-signup-password" className="form__input" type="password" placeholder="Confirm password" required autocomplete="new-password" />
                    </label>
                </div>
                <p className="form__error-message"></p>
                <div className="form__field form__field--justify-center">
                    <label for="signup-button" className="form__label--submit">
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
