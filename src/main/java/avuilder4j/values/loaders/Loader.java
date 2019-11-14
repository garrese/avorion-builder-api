package avuilder4j.values.loaders;

import avuilder4j.dtos.base.Indexable;
import avuilder4j.error.Avuilder4jException;

public interface Loader<E extends Indexable> {

	public EntityMap<E> loadAll() throws Avuilder4jException;
}
