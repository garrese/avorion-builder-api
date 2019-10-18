package avuilder.core.error;

public class AvuilderEntityException extends AvuilderCoreRuntimeException {
	private static final long serialVersionUID = -800973262197531617L;

	public AvuilderEntityException(String msg) {
		super(msg);
	}

	public AvuilderEntityException(Throwable cause) {
		super(cause);
	}

	public AvuilderEntityException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
