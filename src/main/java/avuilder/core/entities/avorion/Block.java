package avuilder.core.entities.avorion;

import avuilder.core.entities.dimensional.Piece;

/**
 * Represents an Avorion full functional block in a structure.
 */
public class Block {

	public Block() {
	}
	
	/**
	 * @param piece the {@link #piece}
	 * @param type the {@link #type}
	 * @param material the {@link #material}
	 * @param color the {@link #color}
	 */
	public Block(Piece piece, TypeBlock type, Material material, String color) {
		this.piece = piece;
		this.type = type;
		this.material = material;
		this.color = color;
	}

	/**
	 * Dimensions of the block.
	 */
	private Piece piece = new Piece();

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

	/**
	 * Gets the {@link #piece}.
	 * @return the {@link #piece}.
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * Sets the {@link #piece}.
	 * @param piece the {@link #piece} to set.
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	/**
	 * Gets the {@link #type}.
	 * @return the {@link #type}.
	 */
	public TypeBlock getType() {
		return type;
	}

	/**
	 * Sets the {@link #type}.
	 * @param type the {@link #type} to set.
	 */
	public void setType(TypeBlock type) {
		this.type = type;
	}

	/**
	 * Gets the {@link #material}.
	 * @return the {@link #material}.
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * Sets the {@link #material}.
	 * @param material the {@link #material} to set.
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

	/**
	 * Gets the {@link #color}.
	 * @return the {@link #color}.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the {@link #color}.
	 * @param color the {@link #color} to set.
	 */
	public void setColor(String color) {
		this.color = color;
	}


	

}