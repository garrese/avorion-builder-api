package avuilder.core.entities.game;

import avuilder.core.entities.dimensional.AxisEnds;
import avuilder.core.entities.dimensional.Cuboid;

/**
 * Represents an Avorion full functional block in a structure.
 */
public class Block extends Cuboid {
	private static final long serialVersionUID = -1896528590585386376L;



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
	 * Block's color in 8 digit color hex format.
	 */
	private String color;

	/**
	 * Block's material.
	 */
	private Material material;

	/**
	 * Block's orientation
	 */
	private Orientation orientation = new Orientation();

	/**
	 * Block's type.
	 */
	private TypeBlock type;

	public Block() {
	}

	public Block(AxisEnds lineX, AxisEnds lineY, AxisEnds lineZ) {
		super(lineX, lineY, lineZ);
	}

	public Block(double lengthX, double lengthY, double lengthZ) {
		super(lengthX, lengthY, lengthZ);
	}

	public Block(Integer index) {
		super(index);
	}

	public Block(Integer index, Cuboid parent) {
		super(index, parent);
	}

	public Block(Material material, TypeBlock type, Orientation orientation) {
		super();
		this.material = material;
		this.type = type;
		this.orientation = orientation;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Block other = (Block) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (material == null) {
			if (other.material != null)
				return false;
		} else if (!material.equals(other.material))
			return false;
		if (orientation == null) {
			if (other.orientation != null)
				return false;
		} else if (!orientation.equals(other.orientation))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
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
	 * Gets the {@link #material}.
	 * 
	 * @return the {@link #material}.
	 */
	public Material getMaterial() {
		return material;
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
	 * Gets the {@link #type}.
	 * 
	 * @return the {@link #type}.
	 */
	public TypeBlock getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((material == null) ? 0 : material.hashCode());
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/**
	 * Sets the {@link #color}.
	 * 
	 * @param color the {@link #color} to set.
	 */
	public void setColor(String color) {
		this.color = color;
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
	 * Sets the {@link #orientation}.
	 * 
	 * @param orientation the {@link #orientation} to set.
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	/**
	 * Sets the {@link #type}.
	 * 
	 * @param type the {@link #type} to set.
	 */
	public void setType(TypeBlock type) {
		this.type = type;
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