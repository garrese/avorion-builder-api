package avuilder.core.error;

public class AvuilderCoreRuntimeException extends RuntimeException {

	public AvuilderCoreRuntimeException(String msg) {
		super(msg);
	}
	
	public AvuilderCoreRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
