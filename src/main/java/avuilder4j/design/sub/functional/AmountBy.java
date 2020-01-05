package avuilder4j.design.sub.functional;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import avuilder4j.util.java.Chainable;
import avuilder4j.util.java.Copyable;
import avuilder4j.util.java.Nullable;

public abstract class AmountBy<T, K, E> extends TreeMap<K, Double> implements Chainable<T>, Copyable<T> {
	private static final long serialVersionUID = 8783727114370701807L;

	public boolean removeWhenZero = true;

	public AmountBy() {
		super();
	}

	public AmountBy(Comparator<? super K> comparator) {
		super(comparator);
	}

	public AmountBy(Map<? extends K, ? extends Double> m) {
		super(m);
	}

	public AmountBy(SortedMap<K, ? extends Double> m) {
		super(m);
	}

	public T add(AmountBy<T, K, E> amounts) {
		amounts.entrySet().stream().forEach(e -> add(e.getKey(), e.getValue()));
		return chain();
	}

	public T add(K key, Double amount) {
		if (amount != null && key != null) {
			if (containsKey(key)) {
				put(key, get(key) + amount);
			} else {
				put(key, amount);
			}

			if (isRemoveWhenZero() && get(key).equals(0d)) {
				remove(key);
			}
		}
		return chain();
	}

	public T addForAll(Double ammount) {
		Set<Map.Entry<K, E>> set = Nullable.m(() -> getMapSource().entrySet());
		if (set != null) {
			for (Map.Entry<K, E> e : set) {
				add(e.getKey(), ammount);
			}
		}
		return chain();
	}

	public abstract String getLabelByElement(E element);

	public abstract Map<K, E> getMapSource();

	public boolean isRemoveWhenZero() { return removeWhenZero; }

	public T remove(Integer key) {
		super.remove(key);
		return chain();
	}

	public T set(AmountBy<T, K, E> amounts) {
		amounts.entrySet().stream().forEach(e -> set(e.getKey(), e.getValue()));
		return chain();
	}

	public T set(K key, Double amount) {
		if (amount != null && key != null) {
			put(key, amount);
		}
		return chain();
	}

	public T setAll(Double ammount) {
		Set<Map.Entry<K, E>> set = Nullable.m(() -> getMapSource().entrySet());
		if (set != null) {
			for (Map.Entry<K, E> e : set) {
				set(e.getKey(), ammount);
			}
		}
		return chain();
	}

	public double getSumOfAll() {
		double total = 0;
		for (Double v : values()) {
			if (v != null)
				total += v;
		}
		return total;
	}

	public T multiply(Double factor) {
		if (factor != null) {
			for (Map.Entry<K, Double> e : entrySet()) {
				if (e.getValue() != null) {
					set(e.getKey(), e.getValue() * factor);
				}
			}
		}
		return chain();
	}

	public void setRemoveWhenZero(boolean removeWhenZero) { this.removeWhenZero = removeWhenZero; }

	public T sub(AmountBy<T, K, E> amounts) {
		amounts.entrySet().stream().forEach(e -> sub(e.getKey(), e.getValue()));
		return chain();
	}

	public T sub(K key, Double amount) {
		if (amount != null)
			add(key, amount * -1);
		return chain();
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(getClass().getSimpleName()).append(" [{");
		int c = 0;
		int last = this.entrySet().size() - 1;
		for (Map.Entry<K, Double> e : this.entrySet()) {
			String label = Nullable.m(() -> getLabelByElement(getMapSource().get(e.getKey())));
			b.append(label != null ? label : e.getKey()).append("=").append(e.getValue());
			if (c != last)
				b.append(", ");
			c++;
		}
		b.append("}]");
		return b.toString();
	}

}
