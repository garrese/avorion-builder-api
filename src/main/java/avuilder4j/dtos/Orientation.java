package avuilder4j.dtos;

import java.io.Serializable;

import avuilder4j.utils.AvValidations;

/**
 * Block orientation
 */
public class Orientation implements Serializable {
	private static final long serialVersionUID = -7559284143961816577L;

	public static final int MAX_LOOK = 5;
	public static final int MIN_LOOK = 0;

	/**
	 * Look component of the piece orientation.
	 */
	private int look = 0;

	/**
	 * Up component of the piece orientation.
	 */
	private int up = 0;

	public Orientation() {}

	/**
	 * @param look the {@link #look}
	 * @param up   the {@link #up}
	 */
	public Orientation(int look, int up) {
		setLook(look);
		setUp(up);
	}

	public static Orientation deepCopy(Orientation typeLook) {
		Orientation o = null;
		if (typeLook != null) {
			o = new Orientation(typeLook.getLook(), typeLook.getUp());
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
	protected void setLook(Integer look) {
		AvValidations.orientation(true, look);
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
	protected void setUp(Integer up) {
		AvValidations.orientation(true, up);
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
