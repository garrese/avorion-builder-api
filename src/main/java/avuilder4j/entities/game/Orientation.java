package avuilder4j.entities.game;

import java.io.Serializable;

import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;

/**
 * Block orientation
 */
public class Orientation implements Serializable {
	private static final long serialVersionUID = -7559284143961816577L;

	public static final int MAX_ORIENTATION = 5;
	public static final int MIN_ORIENTATION = 0;

	/**
	 * Look component of the piece orientation.
	 */
	private Integer look = 0;

	/**
	 * Up component of the piece orientation.
	 */
	private Integer up = 0;

	public Orientation() {
	}

	/**
	 * @param look the {@link #look}
	 * @param up   the {@link #up}
	 */
	public Orientation(Integer look, Integer up) {
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

	public static Orientation deepCopy(Orientation orientation) {
		Orientation o = null;
		if (orientation != null) {
			o = new Orientation();
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
	public Integer getLook() {
		return look;
	}

	/**
	 * Sets the {@link #look}.
	 * 
	 * @param look the {@link #look} to set.
	 */
	public void setLook(Integer look) {
		if (look < MIN_ORIENTATION || look > MAX_ORIENTATION) {
			throw new IllegalArgumentException(
					"Look orientation must be between " + MIN_ORIENTATION + " and " + MAX_ORIENTATION + ".");
		}
		this.look = look;
	}

	/**
	 * Gets the {@link #up}.
	 * 
	 * @return the {@link #up}.
	 */
	public Integer getUp() {
		return up;
	}

	/**
	 * Sets the {@link #up}.
	 * 
	 * @param up the {@link #up} to set.
	 */
	public void setUp(Integer up) {
		if (up < MIN_ORIENTATION || look > MAX_ORIENTATION) {
			throw new IllegalArgumentException(
					"Up orientation must be between " + MIN_ORIENTATION + " and " + MAX_ORIENTATION + ".");
		}
		this.up = up;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Orientation [look=" + look + ", up=" + up + "]";
	}

}
