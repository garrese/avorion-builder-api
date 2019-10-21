package avuilder.core.entities.game;

import avuilder.core.entities.dimensional.AxisEnds;
import avuilder.core.entities.dimensional.Cuboid;

/**
 * Represents an Avorion full functional block in a structure.
 */
public class Block extends Cuboid {
	private static final long serialVersionUID = -1896528590585386376L;



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

	public Block(Integer index) {
		super(index);
	}

	public Block(Integer index, Cuboid parent) {
		super(index, parent);
	}

	public Block(AxisEnds lineX, AxisEnds lineY, AxisEnds lineZ) {
		super(lineX, lineY, lineZ);
	}

	public Block(double lengthX, double lengthY, double lengthZ) {
		super(lengthX, lengthY, lengthZ);
	}

	public Block(Material material, TypeBlock type, Orientation orientation) {
		super();
		this.material = material;
		this.type = type;
		this.orientation = orientation;
	}

	public static Block deepCopy(Block bb) {
		Block b = (Block) Cuboid.deepCopy(bb);
		if (bb != null) {

			b.setOrientation(Orientation.deepCopy(bb.getOrientation()));
			b.setType(bb.getType());
			b.setMaterial(bb.getMaterial());
			b.setColor(bb.getColor());
		}
		return b;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Block [index=" + index + ", parent=" + parent + ", orientation=" + orientation + ", type=" + type
				+ ", material=" + material + ", color=" + color + ", getVolume()=" + getVolume() + ", getCenter()="
				+ getCenter() + ", getAxisX()=" + getAxisX() + ", getAxisY()=" + getAxisY() + ", getAxisZ()="
				+ getAxisZ() + "]";
	}

}