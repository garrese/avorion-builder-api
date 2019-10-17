package avuilder.core.entities.game;

/**
 * Piece orientation
 */
public class Orientation {

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
	 * @param up the {@link #up}
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
	 * @return the {@link #look}.
	 */
	public Integer getLook() {
		return look;
	}

	/**
	 * Sets the {@link #look}.
	 * @param look the {@link #look} to set.
	 */
	public void setLook(Integer look) {
		this.look = look;
	}

	/**
	 * Gets the {@link #up}.
	 * @return the {@link #up}.
	 */
	public Integer getUp() {
		return up;
	}

	/**
	 * Sets the {@link #up}.
	 * @param up the {@link #up} to set.
	 */
	public void setUp(Integer up) {
		this.up = up;
	}
	
	
}
