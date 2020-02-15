package avuilder4j.design.sub.dimensional;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import avuilder4j.util.java.Chainable;

/**
 * La clase está pensada para usar Enums como Ks.<BR>
 * Por eso no se checkean las K en los set
 */
public class ComponentsFormed<K, V, T extends ComponentsFormed<K, V, T>> implements Chainable<T> {

	public ComponentsFormed(List<K> componentsKeyList) {
		for (K k : componentsKeyList) {
			components.put(k, null);
		}
	}

	public ComponentsFormed(ComponentsFormed<K, V, T> componentsFormed) {
		copyComponents(componentsFormed);
	}

	private TreeMap<K, V> components = new TreeMap<>();

	public TreeMap<K, V> getComponentsCopy() { return components; }

	public T setComponents(V value) {
		for (Map.Entry<K, V> e : components.entrySet()) {
			e.setValue(value);
		}
		return chain();
	}

	public T copyComponents(ComponentsFormed<K, V, ?> source) {
		for (Map.Entry<K, V> e : components.entrySet()) {
			e.setValue(source.getComponent(e.getKey()));
		}
		return chain();
	}

	public V getComponent(K component) {
		return components.get(component);
	}

	public T setComponent(K component, V value) {
		components.put(component, value);
		return chain();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName()).append(" [");
		if (components != null) {
			builder.append("components=");
			builder.append(components);
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public T chain() {
		return (T) this;
	}

}
