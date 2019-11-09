package avuilder4j.dtos;

import java.util.HashMap;
import java.util.Map;

import avuilder4j.dtos.base.Closable;
import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;

public class ValuesByIndex<V> extends Closable {

	protected V valueForAll;

	protected Map<Integer, V> valuesByIndex = new HashMap<Integer, V>();

	public ValuesByIndex() {

	}

	public ValuesByIndex(V valueForAll) {
		this.valueForAll = valueForAll;
		close();
	}

	public V getValue(int index) {
		if (valueForAll != null) {
			return valueForAll;
		} else {
			if (valuesByIndex.containsKey(index))
				return valuesByIndex.get(index);
			else
				throw new Avuilder4jRuntimeException(AvErrors.INDEX_NOT_INDEXED);
		}
	}

	public void setValue(int index, V value) {
		valuesByIndex.put(index, value);
	}

	/**
	 * Gets the {@link #valueForAll}.
	 * 
	 * @return the {@link #valueForAll}.
	 */
	public V getValueForAll() { return valueForAll; }

	/**
	 * Sets the {@link #valueForAll}.
	 * 
	 * @param valueForAll the {@link #valueForAll} to set.
	 */
	public void setValueForAll(V valueForAll) {
		checkClosed();
		this.valueForAll = valueForAll;
	}

	@Override
	public String toString() {
		return "ValuesByIndex [valueForAll=" + valueForAll + ", valuesByIndex=" + valuesByIndex + "]";
	}

}
