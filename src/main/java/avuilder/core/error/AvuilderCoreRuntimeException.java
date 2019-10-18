package avuilder.core.error;

public class AvuilderCoreRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 6944837899896564691L;

	public AvuilderCoreRuntimeException(String msg) {
		super(msg);
	}

	public AvuilderCoreRuntimeException(Throwable cause) {
		super(cause);
	}

	public AvuilderCoreRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
