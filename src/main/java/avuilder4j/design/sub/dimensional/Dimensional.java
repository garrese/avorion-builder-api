package avuilder4j.design.sub.dimensional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class Dimensional<T extends Dimensional<T, E>, E> implements Iterable<E> {

	protected List<E> components = new ArrayList<>();

	public int getDimensions() { return components.size(); }

	public Dimensional(int dimensions) {
		for (int i = 0; i < dimensions; i++) {
			components.add(null);
		}
	}

	public Dimensional(Dimensional<T, E> dimensional) {
		this(dimensional.getDimensions());
		copyDimensions(dimensional);
	}

	public T setDim(int dimension, E component) {
		components.set(dimension, component);
		return chain();
	}

	@SuppressWarnings("unchecked")
	public T chain() {
		return (T) this;
	}

	public T setAllDims(E component) {
		for (int dim = 0; dim < getDimensions(); dim++) {
			setDim(dim, component);
		}
		return chain();
	}

	public E getDim(int dimension) {
		return components.get(dimension);
	}

	public List<E> getDimList() { return new ArrayList<>(components); }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("D").append(getDimensions()).append(" ");
		builder.append(components);
		return builder.toString();
	}

	public T copyDimensions(Dimensional<?, E> dimensional) {
		validateSameAmountOfDimensions(dimensional);
		for (int dim = 0; dim < getDimensions(); dim++) {
			setDim(dim, dimensional.getDim(dim));
		}
		return chain();
	}

	@Override
	public Iterator<E> iterator() {
		return getDimList().iterator();
	}

	public boolean hasSameAmountOfDimensions(Dimensional<?, ?> dimensional) {
		if (this.getDimensions() == dimensional.getDimensions())
			return true;
		else
			return false;
	}

	public void validateSameAmountOfDimensions(Dimensional<?, ?> dimensional) {
		if (!hasSameAmountOfDimensions(dimensional))
			throw new IllegalArgumentException("Object with different amount of dimensions: "
					+ dimensional.getDimensions() + "( this=" + getDimensions() + " )");
	}

	public T setByFunctionByDim(Function<Integer, E> forEachDim) {
		for (int dim = 0; dim < getDimensions(); dim++) {
			E result = forEachDim.apply(dim);
			setDim(dim, result);
		}
		return chain();
	}

}
