package avuilder4j.data.loaders;

import java.io.Serializable;
import java.util.Arrays;

public class HashKey implements Serializable {
	private static final long serialVersionUID = 1L;

	private Object[] keys = new Object[] {};

	public HashKey(Object... keys) {
		this.keys = keys;
	}

	public Object[] getKeys() { return keys; }

	public void setKeys(Object... keys) { this.keys = keys; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(keys);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HashKey))
			return false;
		HashKey other = (HashKey) obj;
		return Arrays.deepEquals(keys, other.keys);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HashKey [keys=");
		int c = 0;
		for (Object o : keys) {
			if (c != 0)
				builder.append(",");
			builder.append(o);
		}
		builder.append("]");
		return builder.toString();
	}

}
