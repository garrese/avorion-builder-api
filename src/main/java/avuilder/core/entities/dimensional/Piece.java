package avuilder.core.entities.dimensional;

public class Piece extends Cube {

	public Piece() {
		super();
	}

	/**
	 * @param lineX
	 * @param lineY
	 * @param lineZ
	 * @param index
	 * @param parent
	 * @param look
	 * @param up
	 */
	public Piece(Integer index, Piece parent, 
			AxisLine lineX, AxisLine lineY, AxisLine lineZ, 
			Integer look, Integer up) {
		super(lineX, lineY, lineZ);
		this.index = index;
		this.parent = parent;
		this.look = look;
		this.up = up;
	}

	/**
	 * Block's index in a structure.
	 */
	private Integer index;

	/**
	 * Block's parent index in a structure.
	 */
	private Piece parent;
	
	/**
	 * Look component of the block horientation.
	 */
	private Integer look;

	/**
	 * Up component of the block horientation.
	 */
	private Integer up;

	/**
	 * Gets the {@link #index}.
	 * @return the {@link #index}.
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * Sets the {@link #index}.
	 * @param index the {@link #index} to set.
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * Gets the {@link #parent}.
	 * @return the {@link #parent}.
	 */
	public Piece getParent() {
		return parent;
	}

	/**
	 * Sets the {@link #parent}.
	 * @param parent the {@link #parent} to set.
	 */
	public void setParent(Piece parent) {
		this.parent = parent;
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
