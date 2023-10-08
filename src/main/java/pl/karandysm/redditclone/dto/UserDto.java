package pl.karandysm.redditclone.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import pl.karandysm.redditclone.dto.constraints.FieldsValueMatch;

@FieldsValueMatch(field = "password", fieldMatch = "passwordMatch", message = "Passwords do not match")
public class UserDto {

	@NotNull(message = "Username must not be null")
	@Size(min = 4, message = "Username must be at least 4 characters long")
	private String username;

	@NotNull(message = "Email must not be null")
	@Email(message = "Invalid email adress")
	private String email;

	@NotNull(message = "Password must not be empty")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	@Pattern(regexp = "^(?=.*[A-Z]).{8,}$", message = "Password must contain at least one capital letter")
	@Pattern(regexp = "^(?=.*\\d).{8,}$", message = "Password must contain at least one digit")
	private String password;

	private String passwordMatch;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordMatch() {
		return passwordMatch;
	}

	public void setPasswordMatch(String passwordMatch) {
		this.passwordMatch = passwordMatch;
	}

	@Override
	public String toString() {
		return "UserDto [username=" + username + ", email=" + email + ", password=" + password + ", passwordMatch="
				+ passwordMatch + "]";
	}

}