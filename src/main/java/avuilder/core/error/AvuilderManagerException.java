package avuilder.core.error;

public class AvuilderManagerException extends AvuilderCoreRuntimeException {
	private static final long serialVersionUID = -7548390909706950130L;

	public AvuilderManagerException(String msg) {
		super(msg);
	}

	public AvuilderManagerException(Throwable cause) {
		super(cause);
	}

	public AvuilderManagerException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
