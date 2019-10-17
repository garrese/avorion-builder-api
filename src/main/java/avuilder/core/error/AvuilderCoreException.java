package avuilder.core.error;

public class AvuilderCoreException extends Exception {
	private static final long serialVersionUID = 88611635658255330L;

	public AvuilderCoreException(String msg) {
		super(msg);
	}
	
	public AvuilderCoreException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
