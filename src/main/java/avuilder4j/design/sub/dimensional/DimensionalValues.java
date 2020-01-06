package avuilder4j.design.sub.dimensional;

public class DimensionalValues<T extends DimensionalValues<T>> extends Dimensional<T, Double> {

	public DimensionalValues(Dimensional<T, Double> dimensional) {
		super(dimensional);
	}

	public DimensionalValues(int dimensions) {
		super(dimensions);
	}

	public T sum(DimensionalValues<T> dimensional) {
		return setByFunctionByDim(d -> getDim(d) + dimensional.getDim(d));
	}

	public T sumAll(Number value) {
		return setByFunctionByDim(d -> getDim(d) + value.doubleValue());
	}

	public T sub(DimensionalValues<T> dimensional) {
		return setByFunctionByDim(d -> getDim(d) - dimensional.getDim(d));
	}

	public T subAll(Number value) {
		return setByFunctionByDim(d -> getDim(d) - value.doubleValue());
	}

	public T multiply(DimensionalValues<T> dimensional) {
		return setByFunctionByDim(d -> getDim(d) * dimensional.getDim(d));
	}

	public T multiplyAll(Number value) {
		return setByFunctionByDim(d -> getDim(d) * value.doubleValue());
	}

	public T negate() {
		return multiplyAll(-1);
	}

}
