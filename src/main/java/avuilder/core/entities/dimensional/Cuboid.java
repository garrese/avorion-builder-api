package avuilder.core.entities.dimensional;

import java.io.Serializable;

import avuilder.core.error.AvuilderEntityException;
import avuilder.core.error.Errors;
import avuilder.core.utils.K;
import avuilder.core.utils.Validations;

/**
 * Represents a cuboid in a Cartesian coordinate system.
 */
public class Cuboid implements Serializable {
	private static final long serialVersionUID = -5598838939653504628L;

	private Double lengthX;
	private Double lengthY;
	private Double lengthZ;
	private Point center = new Point();

	public Cuboid() {
	}

	/**
	 * @param lengthX
	 * @param lengthY
	 * @param lengthZ
	 */
	public Cuboid(Double lengthX, Double lengthY, Double lengthZ) {
		setLengthX(lengthX);
		setLengthY(lengthY);
		setLengthZ(lengthZ);
	}

	/**
	 * @param lengthX
	 * @param lengthY
	 * @param lengthZ
	 * @param center
	 */
	public Cuboid(Double lengthX, Double lengthY, Double lengthZ, Point center) {
		setLengthX(lengthX);
		setLengthY(lengthY);
		setLengthZ(lengthZ);
		setCenter(center);
	}

	public static Cuboid deepCopy(Cuboid cuboid) {
		Cuboid c = null;
		if (cuboid != null) {
			c = new Cuboid(cuboid.getLengthX(), cuboid.getLengthY(), cuboid.getLengthZ(), cuboid.getCenter());
		}
		return c;
	}

	public void validate() {
		validateLengths();
		validateCenter();
	}

	public void validateLengths() {
		if (!isLengths())
			throw new AvuilderEntityException(Errors.NOT_SUFFICIENTLY_DEFINED);
	}

	public void validateCenter() {
		if (!isCenter())
			throw new AvuilderEntityException(Errors.NOT_SUFFICIENTLY_DEFINED);
	}

	public boolean isLengths() {
		if (getLengthX() == null || getLengthY() == null || getLengthZ() == null)
			return false;
		else
			return true;
	}

	public boolean isCenter() {
		if (getCenter() != null)
			return true;
		else
			return false;
	}

	public boolean isLengthsAndCenter() {
		if (isLengths() && isCenter())
			return true;
		else
			return false;
	}

	public void setLengths(Double lengthX, Double lengthY, Double lengthZ) {
		setLengthX(lengthX);
		setLengthY(lengthY);
		setLengthZ(lengthZ);
	}

	public Double getVolume() {
		validateLengths();
		return lengthX * lengthY * lengthZ;
	}

	public Point getFaceCenter(int face) {
		Point p = null;
		try {
			validate();
			Validations.validateFacesExistance(face);

			p = getCenter();
			switch (face) {
			case K.FACE_BASE:
				p.y = getLY();
				break;
			case K.FACE_TOP:
				p.y = getUY();
				break;
			case K.FACE_WALL_1:
				p.z = getUZ();
				break;
			case K.FACE_WALL_2:
				p.x = getUX();
				break;
			case K.FACE_WALL_3:
				p.z = getLZ();
				break;
			case K.FACE_WALL_4:
				p.x = getLX();
				break;
			default:
				throw new AvuilderEntityException(Errors.FACE_NOT_RECOGNIZED);
			}
		} catch (Exception e) {
			throw new AvuilderEntityException(e);
		}
		return p;
	}

	public Point getCorner(int corner) {
		Point p = null;
		try {
			validate();
			Validations.validateCornersExistance(corner);

			p = new Point();
			switch (corner) {
			case K.CORNER_BASE_1:
				p.x = getLX();
				p.y = getLY();
				p.z = getLZ();
				break;
			case K.CORNER_BASE_2:
				p.x = getUX();
				p.y = getLY();
				p.z = getLZ();
				break;
			case K.CORNER_BASE_3:
				p.x = getUX();
				p.y = getLY();
				p.z = getUZ();
				break;
			case K.CORNER_BASE_4:
				p.x = getLX();
				p.y = getLY();
				p.z = getUZ();
				break;
			case K.CORNER_TOP_1:
				p.x = getLX();
				p.y = getUY();
				p.z = getLZ();
				break;
			case K.CORNER_TOP_2:
				p.x = getUX();
				p.y = getUY();
				p.z = getLZ();
				break;
			case K.CORNER_TOP_3:
				p.x = getUX();
				p.y = getUY();
				p.z = getUZ();
				break;
			case K.CORNER_TOP_4:
				p.x = getLX();
				p.y = getUY();
				p.z = getUZ();
				break;
			default:
				throw new AvuilderEntityException(Errors.CORNER_NOT_RECOGNIZED);
			}
		} catch (Exception e) {
			throw new AvuilderEntityException(e);
		}
		return p;
	}

	/**
	 * Gets the {@link #lengthX}.
	 * 
	 * @return the {@link #lengthX}.
	 */
	public Double getLengthX() {
		return lengthX;
	}

	/**
	 * Sets the {@link #lengthX}.
	 * 
	 * @param lengthX the {@link #lengthX} to set.
	 */
	public void setLengthX(Double lengthX) {
		try {
			Validations.validateLengths(lengthX);
			this.lengthX = lengthX;
		} catch (Exception e) {
			throw new AvuilderEntityException(e);
		}
	}

	/**
	 * Gets the {@link #lengthY}.
	 * 
	 * @return the {@link #lengthY}.
	 */
	public Double getLengthY() {
		return lengthY;
	}

	/**
	 * Sets the {@link #lengthY}.
	 * 
	 * @param lengthY the {@link #lengthY} to set.
	 */
	public void setLengthY(Double lengthY) {
		try {
			Validations.validateLengths(lengthY);
			this.lengthY = lengthY;
		} catch (Exception e) {
			throw new AvuilderEntityException(e);
		}
	}

	/**
	 * Gets the {@link #lengthZ}.
	 * 
	 * @return the {@link #lengthZ}.
	 */
	public Double getLengthZ() {
		return lengthZ;
	}

	/**
	 * Sets the {@link #lengthZ}.
	 * 
	 * @param lengthZ the {@link #lengthZ} to set.
	 */
	public void setLengthZ(Double lengthZ) {
		try {
			Validations.validateLengths(lengthZ);
			this.lengthZ = lengthZ;
		} catch (Exception e) {
			throw new AvuilderEntityException(e);
		}
	}

	public Double getLength(int dimension) {
		try {
			Validations.validateDimensionsExistance(dimension);
			switch (dimension) {
			case K.X:
				return getLengthX();
			case K.Y:
				return getLengthY();
			case K.Z:
				return getLengthZ();
			default:
				throw new AvuilderEntityException(Errors.DIMENSION_NOT_RECOGNIZED);
			}
		} catch (Exception e) {
			throw new AvuilderEntityException(e);
		}
	}

	public void setLength(int dimension, Double length) {
		try {
			Validations.validateDimensionsExistance(dimension);
			switch (dimension) {
			case K.X:
				setLengthX(length);
				break;
			case K.Y:
				setLengthY(length);
				break;
			case K.Z:
				setLengthZ(length);
				break;
			default:
				System.out.println("ye");
				throw new AvuilderEntityException(Errors.DIMENSION_NOT_RECOGNIZED);
			}
		} catch (Exception e) {
			throw new AvuilderEntityException(e);
		}
	}

	/**
	 * Gets the {@link #center}.
	 * 
	 * @return the {@link #center}.
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * Sets the {@link #center}.
	 * 
	 * @param center the {@link #center} to set.
	 */
	public void setCenter(Point center) {
		this.center = center;
	}

	public double getHX() {
		return lengthX / 2;
	}

	public double getUX() {
		return center.x + getHX();
	}

	public double getLX() {
		return center.x - getHX();
	}

	public double getHY() {
		return lengthX / 2;
	}

	public double getUY() {
		return center.y + getHY();
	}

	public double getLY() {
		return center.y - getHY();
	}

	public double getHZ() {
		return lengthX / 2;
	}

	public double getUZ() {
		return center.z + getHZ();
	}

	public double getLZ() {
		return center.z - getHZ();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cuboid [lengthX=" + lengthX + ", lengthY=" + lengthY + ", lengthZ=" + lengthZ + ", center=" + center
				+ "]";
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((center == null) ? 0 : center.hashCode());
		result = prime * result + ((lengthX == null) ? 0 : lengthX.hashCode());
		result = prime * result + ((lengthY == null) ? 0 : lengthY.hashCode());
		result = prime * result + ((lengthZ == null) ? 0 : lengthZ.hashCode());
		return result;
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
		Cuboid other = (Cuboid) obj;
		if (center == null) {
			if (other.center != null)
				return false;
		} else if (!center.equals(other.center))
			return false;
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

}
