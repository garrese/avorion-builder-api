package avuilder4j.dtos.map;

import java.util.Objects;

public final class MapIndex<T> extends MapIndexAbstract {

	protected T value;

	public MapIndex(T value) {
		this.value = value;
	}

	public T getValue() { return value; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MapIndex [value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapIndex other = (MapIndex) obj;
		return Objects.equals(value, other.value);
	}

}
