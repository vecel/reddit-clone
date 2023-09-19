package pl.karandysm.redditclone.model;

public class User {

	private String email;
	private String username;
	private int passwordHash;

	public User() {

	}

	public User(String email, String username) {
		super();
		this.email = email;
		this.username = username;
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
