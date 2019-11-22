package avuilder4j.values.loaders;

import java.util.LinkedHashMap;
import java.util.Map;

import avuilder4j.dtos.map.Indexable;
import avuilder4j.dtos.map.MapIndexAbstract;
import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;

public class EntityMap<E extends Indexable> {
	protected Map<MapIndexAbstract, E> map = new LinkedHashMap<>();

	public E get(MapIndexAbstract mapIndex) throws Avuilder4jRuntimeException {
		if (!map.containsKey(mapIndex)) {
			throw new Avuilder4jRuntimeException(AvErrors.INDEX_NOT_INDEXED);
		}
		return map.get(mapIndex);
	}

	public void add(E element) {
		map.put(element.getMapIndex(), element);
	}

	public Map<MapIndexAbstract, E> getMap() { return map; }

	protected void setMap(Map<MapIndexAbstract, E> map) { this.map = map; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EntityMap [\n");
		for (E e : map.values()) {
			builder.append("\t");
			builder.append(e);
			builder.append("\n");
		}
		builder.append("]");
		return builder.toString();
	}

}
