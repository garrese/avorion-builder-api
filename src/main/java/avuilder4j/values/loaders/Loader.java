package avuilder4j.values.loaders;

import java.util.LinkedHashMap;
import java.util.Map;

import avuilder4j.dtos.base.Indexable;
import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;

public class Loader<E extends Indexable> {
	protected Map<Integer, E> map = new LinkedHashMap<Integer, E>();

	public E get(Integer index) throws Avuilder4jRuntimeException {
		if (!map.containsKey(index)) {
			throw new Avuilder4jRuntimeException(AvErrors.INDEX_NOT_INDEXED);
		}
		return map.get(index);
	}

	public void add(E e) {
		map.put(e.getIndex(), e);
	}

	/**
	 * Gets the {@link #map}.
	 * 
	 * @return the {@link #map}.
	 */
	public Map<Integer, E> getMap() { return map; }

	/**
	 * Sets the {@link #map}.
	 * 
	 * @param map the {@link #map} to set.
	 */
	protected void setMap(Map<Integer, E> map) { this.map = map; }

}
