package avuilder4j.error;

public class Avuilder4jRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 6944837899896564691L;

	public Avuilder4jRuntimeException(String msg) {
		super(msg);
	}

	public Avuilder4jRuntimeException(Throwable cause) {
		super(cause);
	}

	public Avuilder4jRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
