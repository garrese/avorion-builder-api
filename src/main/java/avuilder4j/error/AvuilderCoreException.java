package avuilder4j.error;

public class AvuilderCoreException extends Exception {
	private static final long serialVersionUID = 88611635658255330L;

	public AvuilderCoreException(String msg) {
		super(msg);
	}

	public AvuilderCoreException(Throwable cause) {
		super(cause);
	}

	public AvuilderCoreException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
