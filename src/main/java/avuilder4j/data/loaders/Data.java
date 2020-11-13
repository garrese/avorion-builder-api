package avuilder4j.data.loaders;

import java.util.HashMap;
import java.util.Map;

public class Data {

	private Map<HashKey, Object> dataMap = new HashMap<>();

	public Map<HashKey, Object> getDataMap() { return dataMap; }

	public void setDataMap(Map<HashKey, Object> dataMap) { this.dataMap = dataMap; }

	@SuppressWarnings("unchecked")
	public <T> T find(Object... keys) {
		return (T) getDataMap().get(new HashKey(keys));
	}

	public void put(HashKey key, Object value) {
		dataMap.put(key, value);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Data [dataMap=\n");
		for (Map.Entry<HashKey, Object> entry : dataMap.entrySet()) {
			builder.append("\t");
			builder.append(entry.getKey()).append(": ").append(entry.getValue());
			builder.append("\n");
		}
		builder.append("]");
		return builder.toString();
	}

}
