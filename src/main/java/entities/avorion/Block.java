package entities.avorion;

import entities.dimensional.Cube;

/**
 * Represents an Avorion full functional block in a structure.
 */
public class Block {

	/**
	 * Block's index in a structure.
	 */
	private Integer index;

	/**
	 * Block's parent index in a structure.
	 */
	private Block parent;

	/**
	 * Dimensions of the block.
	 */
	private Cube cube = new Cube();

	/**
	 * Block's type.
	 */
	private BlockType type;

	/**
	 * Block's material.
	 */
	private Material material;

	/**
	 * Look component of the block horientation.
	 */
	private Integer look;

	/**
	 * Up component of the block horientation.
	 */
	private Integer up;

	/**
	 * Block's color in 8 digit color hex format.
	 */
	private String color;

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
	 * Gets the {@link #cube}.
	 * 
	 * @return the {@link #cube}.
	 */
	public Cube getCube() {
		return cube;
	}

	/**
	 * Sets the {@link #cube}.
	 * 
	 * @param cube the {@link #cube} to set.
	 */
	public void setCube(Cube cube) {
		this.cube = cube;
	}

	/**
	 * Gets the {@link #type}.
	 * 
	 * @return the {@link #type}.
	 */
	public BlockType getType() {
		return type;
	}

	/**
	 * Sets the {@link #type}.
	 * 
	 * @param type the {@link #type} to set.
	 */
	public void setType(BlockType type) {
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