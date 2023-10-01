package pl.karandysm.redditclone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "\"USER\"")
public class User {

	@Id
	@GeneratedValue
	private Long id;
	private String email;
	private String username;
	private int passwordHash;

	public User() {

	}

	public User(String email, String username, int passwordHash) {
		super();
		this.email = email;
		this.username = username;
		this.passwordHash = passwordHash;
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

	@Override
	public String toString() {
		return "User [email=" + email + ", username=" + username + "]";
	}

	
}
