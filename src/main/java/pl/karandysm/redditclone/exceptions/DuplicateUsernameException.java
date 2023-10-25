package pl.karandysm.redditclone.exceptions;

public class DuplicateUsernameException extends Exception {
	
	private static String messagePrefix = "Multiple records found with the same username: ";

	public DuplicateUsernameException() {
		super();
	}

	public DuplicateUsernameException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(messagePrefix + message, cause, enableSuppression, writableStackTrace);
	}

	public DuplicateUsernameException(String message, Throwable cause) {
		super(messagePrefix + message, cause);
	}

	public DuplicateUsernameException(String message) {
		super(messagePrefix + message);
	}

	public DuplicateUsernameException(Throwable cause) {
		super(cause);
	}

}
