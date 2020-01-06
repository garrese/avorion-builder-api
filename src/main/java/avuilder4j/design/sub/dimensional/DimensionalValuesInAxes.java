package avuilder4j.design.sub.dimensional;

import avuilder4j.design.enums.Axis;
import avuilder4j.util.java.Nullable;

public class DimensionalValuesInAxes<T extends DimensionalValuesInAxes<T>> extends DimensionalValues<T> {

	public DimensionalValuesInAxes(DimensionalValuesInAxes<T> dimensional) {
		super(dimensional);
	}

	public DimensionalValuesInAxes() {
		super(Axis.values().length);
	}

	public T set(Axis axis, Number component) {
		setDim(axis.getIndex(), Nullable.m(() -> component.doubleValue()));
		return chain();
	}

	public Double getComponent(Axis axis) {
		return getDim(axis.getIndex());
	}

	public void set(Number x, Number y, Number z) {
		set(Axis.X, Nullable.m(() -> x.doubleValue()));
		set(Axis.Y, Nullable.m(() -> y.doubleValue()));
		set(Axis.Z, Nullable.m(() -> z.doubleValue()));
	}

}
