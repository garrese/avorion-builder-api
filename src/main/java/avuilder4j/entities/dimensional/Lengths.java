package avuilder4j.entities.dimensional;

import java.io.Serializable;

import avuilder4j.error.AvErrors;
import avuilder4j.utils.AvK;

public class Lengths implements Serializable {
	private static final long serialVersionUID = -3737835992118370776L;

	Double lengthX;
	Double lengthY;
	Double lengthZ;

	public Lengths() {

	}

	public Lengths(Double lengthX, Double lengthY, Double lengthZ) {
		this.lengthX = lengthX;
		this.lengthY = lengthY;
		this.lengthZ = lengthZ;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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

	public Double getLength(int axisId) {
		switch (axisId) {
		case AvK.AXIS_X:
			return lengthX;
		case AvK.AXIS_Y:
			return lengthY;
		case AvK.AXIS_Z:
			return lengthZ;
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lengthX == null) ? 0 : lengthX.hashCode());
		result = prime * result + ((lengthY == null) ? 0 : lengthY.hashCode());
		result = prime * result + ((lengthZ == null) ? 0 : lengthZ.hashCode());
		return result;
	}

	public void setLength(Double length, int axisId) {
		switch (axisId) {
		case AvK.AXIS_X:
			lengthX = length;
		case AvK.AXIS_Y:
			lengthY = length;
		case AvK.AXIS_Z:
			lengthZ = length;
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Lengths [lengthX=" + lengthX + ", lengthY=" + lengthY + ", lengthZ=" + lengthZ + "]";
	}

}
