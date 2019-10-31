package avuilder4j.error;

public class Avuilder4jException extends Exception {
	private static final long serialVersionUID = 88611635658255330L;

	public Avuilder4jException(String msg) {
		super(msg);
	}

	public Avuilder4jException(Throwable cause) {
		super(cause);
	}

	public Avuilder4jException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
