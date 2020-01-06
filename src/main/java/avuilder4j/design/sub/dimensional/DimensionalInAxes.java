package avuilder4j.design.sub.dimensional;

import avuilder4j.design.enums.Axis;

public class DimensionalInAxes<T extends DimensionalInAxes<T, E>, E>
		extends Dimensional<T, E> {

	public DimensionalInAxes(int dimensions) {
		super(Axis.values().length);
	}

	public DimensionalInAxes(DimensionalInAxes<T, E> dimensional) {
		super(dimensional);
	}

	public void setDim(Axis axis, E component) {
		setDim(axis.getIndex(), component);
	}

	public E getDim(Axis axis) {
		return getDim(axis.getIndex());
	}

}
