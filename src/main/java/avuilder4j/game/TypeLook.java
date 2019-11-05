package avuilder4j.game;

import java.io.Serializable;

import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;

/**
 * Block orientation
 */
public class TypeLook implements Serializable {
	private static final long serialVersionUID = -7559284143961816577L;

	public static final int MAX_LOOK = 5;
	public static final int MIN_LOOK = 0;

	/**
	 * Look component of the piece orientation.
	 */
	private Integer look = 0;

	/**
	 * Up component of the piece orientation.
	 */
	private Integer up = 0;

	public TypeLook() {}

	/**
	 * @param look the {@link #look}
	 * @param up   the {@link #up}
	 */
	public TypeLook(Integer look, Integer up) {
		setLook(look);
		setUp(up);
	}

	public boolean isOrientationDefined() {
		if (look != null || up != null) {
			return true;
		} else {
			return false;
		}
	}

	public void validateOrientation() {
		if (!isOrientationDefined())
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
	}

	public static TypeLook deepCopy(TypeLook orientation) {
		TypeLook o = null;
		if (orientation != null) {
			o = new TypeLook();
			o.setLook(orientation.getLook());
			o.setUp(orientation.getUp());
		}
		return o;
	}

	/**
	 * Gets the {@link #look}.
	 * 
	 * @return the {@link #look}.
	 */
	public Integer getLook() { return look; }

	/**
	 * Sets the {@link #look}.
	 * 
	 * @param look the {@link #look} to set.
	 */
	public void setLook(Integer look) {
		if (look < MIN_LOOK || look > MAX_LOOK) {
			throw new IllegalArgumentException(
					"Look orientation must be between " + MIN_LOOK + " and " + MAX_LOOK + ".");
		}
		this.look = look;
	}

	/**
	 * Gets the {@link #up}.
	 * 
	 * @return the {@link #up}.
	 */
	public Integer getUp() { return up; }

	/**
	 * Sets the {@link #up}.
	 * 
	 * @param up the {@link #up} to set.
	 */
	public void setUp(Integer up) {
		if (up < MIN_LOOK || look > MAX_LOOK) {
			throw new IllegalArgumentException("Up orientation must be between " + MIN_LOOK + " and " + MAX_LOOK + ".");
		}
		this.up = up;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[look=" + look + ", up=" + up + "]";
	}

}
