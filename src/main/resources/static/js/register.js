const form = document.getElementById("signup")
const username = document.getElementById("signup-username")
const password = document.getElementById("signup-password")
const confirmationPassword = document.getElementById("confirm-signup-password")


username.addEventListener("input", (event) => {
	if(username.value.length < 4) {
		username.setCustomValidity("Username must be at least 4 characters long.")
	} else {
		username.setCustomValidity("")
	}
})

password.addEventListener("input", (event) => {
	if(password.value.length < 8) {
		password.setCustomValidity("Password must be at least 8 characters long.")
	} else if(!/[A-Z]/.test(password.value)) {
		password.setCustomValidity("Password must contain at least one capital letter.")
	} else if(!/[0-9]/.test(password.value)) {
		password.setCustomValidity("Password must contain at least one number.")
	} else {
		password.setCustomValidity("")
	}
})

confirmationPassword.addEventListener("input", (event) => {
	if(confirmationPassword.value != password.value) {
		confirmationPassword.setCustomValidity("Your password and confirmation password don't match.")
	} else {
		confirmationPassword.setCustomValidity("")
	}
})