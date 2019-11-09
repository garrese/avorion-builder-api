package avuilder4j.dtos.base;

import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;

public class Closable {

	protected boolean closed = false;

	public void close() {
		closed = true;
	}

	protected void checkClosed() {
		if (closed)
			throw new Avuilder4jRuntimeException(AvErrors.OBJET_CLOSED);
	}

	public boolean isClosed() { return closed; }
}
