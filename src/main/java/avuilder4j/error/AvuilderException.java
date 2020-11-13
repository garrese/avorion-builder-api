package avuilder4j.error;

public class AvuilderException extends Exception {
	private static final long serialVersionUID = 88611635658255330L;

	public AvuilderException(String msg) {
		super(msg);
	}

	public AvuilderException(Throwable cause) {
		super(cause);
	}

	public AvuilderException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
