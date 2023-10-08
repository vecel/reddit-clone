package pl.karandysm.redditclone.exceptions;

public class UserExistsWithEmailException extends RegisterException {

	public UserExistsWithEmailException(String message) {
		super(message);
	}

}
