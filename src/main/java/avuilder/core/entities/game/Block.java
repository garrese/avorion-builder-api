package avuilder.core.entities.game;

import avuilder.core.entities.dimensional.Cuboid;

/**
 * Represents an Avorion full functional block in a structure.
 */
public class Block extends Cuboid {
	private static final long serialVersionUID = -1896528590585386376L;

	/**
	 * Block's index in structure
	 */
	private Integer index;

	/**
	 * Block's parent index in a structure.
	 */
	private Block parent;

	/**
	 * Block's orientation
	 */
	private Orientation orientation = new Orientation();

	/**
	 * Block's type.
	 */
	private TypeBlock type;

	/**
	 * Block's material.
	 */
	private Material material;

	/**
	 * Block's color in 8 digit color hex format.
	 */
	private String color;

	public Block() {
	}

	/**
	 * @param index
	 */
	public Block(Integer index) {
		this.index = index;
	}

	/**
	 * @param parent the {@link #parent}
	 */
	public Block(Block parent) {
		this.parent = parent;
	}

	/**
	 * @param index
	 * @param parent
	 */
	public Block(Integer index, Block parent) {
		this.index = index;
		this.parent = parent;
	}

	public static Block deepCopy(Block bb) {
		Block b = (Block) Cuboid.deepCopy(bb);
		if (bb != null) {
			b.setIndex(bb.getIndex());
			b.setParent(Block.deepCopy(bb.getParent()));
			b.setOrientation(Orientation.deepCopy(bb.getOrientation()));
			b.setType(bb.getType());
			b.setMaterial(bb.getMaterial());
			b.setColor(bb.getColor());
		}
		return b;
	}

	/**
	 * Gets the {@link #index}.
	 * 
	 * @return the {@link #index}.
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * Sets the {@link #index}.
	 * 
	 * @param index the {@link #index} to set.
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * Gets the {@link #parent}.
	 * 
	 * @return the {@link #parent}.
	 */
	public Block getParent() {
		return parent;
	}

	/**
	 * Sets the {@link #parent}.
	 * 
	 * @param parent the {@link #parent} to set.
	 */
	public void setParent(Block parent) {
		this.parent = parent;
	}

	/**
	 * Gets the {@link #orientation}.
	 * 
	 * @return the {@link #orientation}.
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * Sets the {@link #orientation}.
	 * 
	 * @param orientation the {@link #orientation} to set.
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	/**
	 * Gets the {@link #type}.
	 * 
	 * @return the {@link #type}.
	 */
	public TypeBlock getType() {
		return type;
	}

	/**
	 * Sets the {@link #type}.
	 * 
	 * @param type the {@link #type} to set.
	 */
	public void setType(TypeBlock type) {
		this.type = type;
	}

	/**
	 * Gets the {@link #material}.
	 * 
	 * @return the {@link #material}.
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * Sets the {@link #material}.
	 * 
	 * @param material the {@link #material} to set.
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

	/**
	 * Gets the {@link #color}.
	 * 
	 * @return the {@link #color}.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the {@link #color}.
	 * 
	 * @param color the {@link #color} to set.
	 */
	public void setColor(String color) {
		this.color = color;
	}

}