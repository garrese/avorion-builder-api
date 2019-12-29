package avuilder4j.design.sub.functional;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import avuilder4j.util.java.Chainable;
import avuilder4j.util.java.Copyable;

public abstract class Amounts<T> extends TreeMap<Integer, Double> implements Chainable<T>, Copyable<T> {
	private static final long serialVersionUID = 8783727114370701807L;

	public boolean removeWhenZero = true;

	public Amounts() {
		super();
	}

	public Amounts(Comparator<? super Integer> comparator) {
		super(comparator);
	}

	public Amounts(Map<? extends Integer, ? extends Double> m) {
		super(m);
	}

	public Amounts(SortedMap<Integer, ? extends Double> m) {
		super(m);
	}

	public T add(Amounts<T> amounts) {
		amounts.entrySet().stream().forEach(e -> add(e.getKey(), e.getValue()));
		return chain();
	}

	public T add(Integer key, Double amount) {
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

	public T remove(Integer key) {
		super.remove(key);
		return chain();
	}

	public T set(Amounts<T> amounts) {
		amounts.entrySet().stream().forEach(e -> set(e.getKey(), e.getValue()));
		return chain();
	}

	public T set(Integer key, Double amount) {
		if (amount != null && key != null) {
			put(key, amount);
		}
		return chain();
	}

	public T sub(Amounts<T> amounts) {
		amounts.entrySet().stream().forEach(e -> sub(e.getKey(), e.getValue()));
		return chain();
	}

	public T sub(Integer key, Double amount) {
		if (amount != null)
			add(key, amount * -1);
		return chain();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Amounts [");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	public boolean isRemoveWhenZero() { return removeWhenZero; }

	public void setRemoveWhenZero(boolean removeWhenZero) { this.removeWhenZero = removeWhenZero; }

}
