package pl.karandysm.redditclone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "USERS")
public class User {
	
	/**
	 * Na razie niezbyt bezpiecznie pobierane jest haslo z formsa
	 */

	@Id
	@GeneratedValue
	private Long id;
	
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
	
//	private int passwordHash;

	public User() {

	}

	public User(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", username=" + username + "]";
	}

}
