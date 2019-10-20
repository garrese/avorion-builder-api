package avuilder.core.entities.game;

import java.io.Serializable;

/**
 * Block orientation
 */
public class Orientation implements Serializable {
	private static final long serialVersionUID = -7559284143961816577L;

	/**
	 * Look component of the piece orientation.
	 */
	private Integer look;

	/**
	 * Up component of the piece orientation.
	 */
	private Integer up;

	public Orientation() {
	}

	/**
	 * @param look the {@link #look}
	 * @param up   the {@link #up}
	 */
	public Orientation(Integer look, Integer up) {
		this.look = look;
		this.up = up;
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
