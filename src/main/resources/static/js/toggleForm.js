const signupForm = document.getElementById("signup")
const loginForm = document.getElementById("login")
const inputs = document.getElementsByTagName("input")

/**
 * Removes text from form's input fields
 * It is important to check whether inputType isn't submit, without
 * that line function clears text from form's submit buttons
 */
function clearFormFields() {
    for (input of inputs) {
        const inputType = input.getAttribute("type")
        if (inputType == "checkbox") {
            input.checked = false
        }
        if (inputType != "submit") {
            input.value = ""
        }
    }
}

/**
 * Hides form with id: signup and displays form with id: login
 * This is onclick action of span with id: show-login-form
 */
function showLoginForm() {
    clearFormFields()
    signupForm.setAttribute("hidden", "")
    loginForm.removeAttribute("hidden")
}

/**
 * Hides form with id: login and displays form with id: signup
 * This is onclick action of span with id: show-signup-form
 */
function showSignupForm() {
    clearFormFields()
    loginForm.setAttribute("hidden", "")
    signupForm.removeAttribute("hidden")
}