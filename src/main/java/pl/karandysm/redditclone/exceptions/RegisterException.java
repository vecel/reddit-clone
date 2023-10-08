package pl.karandysm.redditclone.exceptions;

public class RegisterException extends Exception {

	public RegisterException() {
		super();
	}

	public RegisterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RegisterException(String message, Throwable cause) {
		super(message, cause);
	}

	public RegisterException(String message) {
		super(message);
	}

	public RegisterException(Throwable cause) {
		super(cause);
	}

}
