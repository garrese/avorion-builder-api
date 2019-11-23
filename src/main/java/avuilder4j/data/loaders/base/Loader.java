package avuilder4j.data.loaders.base;

import java.util.Map;

import avuilder4j.error.Avuilder4jException;

public interface Loader<K, E> {

	public Map<K, E> loadAll() throws Avuilder4jException;
}
