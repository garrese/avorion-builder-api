package avuilder4j.design.sub;

import java.io.Serializable;

import avuilder4j.design.enums.Axis;
import avuilder4j.error.AvErrors;

public class Lengths implements Serializable {
	private static final long serialVersionUID = -3737835992118370776L;

	public Double lengthX;
	public Double lengthY;
	public Double lengthZ;

	public Lengths() {

	}

	public Lengths(double lengthX, double lengthY, double lengthZ) {
		this.lengthX = lengthX;
		this.lengthY = lengthY;
		this.lengthZ = lengthZ;
	}

	public Lengths(Double lengthX, Double lengthY, Double lengthZ) {
		this.lengthX = lengthX;
		this.lengthY = lengthY;
		this.lengthZ = lengthZ;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lengths other = (Lengths) obj;
		if (lengthX == null) {
			if (other.lengthX != null)
				return false;
		} else if (!lengthX.equals(other.lengthX))
			return false;
		if (lengthY == null) {
			if (other.lengthY != null)
				return false;
		} else if (!lengthY.equals(other.lengthY))
			return false;
		if (lengthZ == null) {
			if (other.lengthZ != null)
				return false;
		} else if (!lengthZ.equals(other.lengthZ))
			return false;
		return true;
	}

	public Double getLength(Axis axis) {
		switch (axis) {
		case X:
			return lengthX;
		case Y:
			return lengthY;
		case Z:
			return lengthZ;
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lengthX == null) ? 0 : lengthX.hashCode());
		result = prime * result + ((lengthY == null) ? 0 : lengthY.hashCode());
		result = prime * result + ((lengthZ == null) ? 0 : lengthZ.hashCode());
		return result;
	}

	public void setLength(Double length, Axis axisId) {
		switch (axisId) {
		case X:
			lengthX = length;
		case Y:
			lengthY = length;
		case Z:
			lengthZ = length;
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	@Override
	public String toString() {
		return "[lengthX=" + lengthX + ", lengthY=" + lengthY + ", lengthZ=" + lengthZ + "]";
	}

}
