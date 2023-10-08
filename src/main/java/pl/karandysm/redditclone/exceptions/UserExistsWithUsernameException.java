package pl.karandysm.redditclone.exceptions;

public class UserExistsWithUsernameException extends RegisterException {

	public UserExistsWithUsernameException(String message) {
		super(message);
	}
	
}
