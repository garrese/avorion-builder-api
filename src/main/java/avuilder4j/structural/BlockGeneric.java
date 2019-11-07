package avuilder4j.structural;

import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;
import avuilder4j.spatial.auxs.Lengths;
import avuilder4j.spatial.generics.CuboidGeneric;
import avuilder4j.structural.dtos.TypeLook;
import avuilder4j.utils.AvValidations;

/**
 * Represents an Avorion full functional block in a structure.
 */
public abstract class BlockGeneric<T extends CuboidGeneric> extends CuboidGeneric<T> {
	private static final long serialVersionUID = -1896528590585386376L;

//	public static Block deepCopy(Block bb) {
//		Block b = (Block) Cuboid.deepCopy(bb);
//		if (bb != null) {
//			b.setParent(bb.getParent());
//			b.setTypeLook(TypeLook.deepCopy(bb.getTypeLook()));
//			b.setType(bb.getType());
//			b.setMaterial(bb.getMaterial());
//			b.setColor(bb.getColor());
//		}
//		return b;
//	}

	/**
	 * Block's color in 8 digit color hex format.
	 */
	private String color;

	/**
	 * Block's material.
	 */
	private Integer material;

	/**
	 * Block's orientation
	 */
	private TypeLook typeLook = new TypeLook();

	/**
	 * Block's type.
	 */
	private Integer type;

	public BlockGeneric() {}

	public BlockGeneric(Lengths lengths) {
		super(lengths);
	}

	public BlockGeneric(Integer index) {
		super(index);
	}

	public BlockGeneric(Integer index, T parent) {
		super(index, parent);
	}

	public BlockGeneric(Integer material, Integer type, TypeLook typeLook) {
		super();
		this.material = material;
		this.type = type;
		this.typeLook = typeLook;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlockGeneric other = (BlockGeneric) obj;
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
		if (typeLook == null) {
			if (other.typeLook != null)
				return false;
		} else if (!typeLook.equals(other.typeLook))
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
	public String getColor() { return color; }

	public Integer getMaterial() { return material; }

	/**
	 * Gets the {@link #typeLook}.
	 * 
	 * @return the {@link #typeLook}.
	 */
	public TypeLook getTypeLook() { return typeLook; }

	public Integer getType() { return type; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((material == null) ? 0 : material.hashCode());
		result = prime * result + ((typeLook == null) ? 0 : typeLook.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	public boolean isBlockDefined() {
		if (!isCuboidDefined()) {
			return false;
		} else if (color == null || typeLook == null || material == null || type == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Sets the {@link #color}.
	 * 
	 * @param color the {@link #color} to set.
	 */
	public void setColor(String color) {
		AvValidations.validateColors(color);
		this.color = color;
	}

	public void setMaterial(Integer material) { this.material = material; }

	/**
	 * Sets the {@link #typeLook}.
	 * 
	 * @param typeLook the {@link #typeLook} to set.
	 */
	public void setTypeLook(TypeLook typeLook) { this.typeLook = typeLook; }

	public void setType(Integer type) { this.type = type; }

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String tags = "";
		if (this.tags != null)
			tags = this.tags;

		String parentSring = null;
		if (parent != null)
			parentSring = "[id=" + parent.getIndex() + "]";

		//@formatter:off
		return "Block ["
				+ "tags=\"" + tags + "\""
				+ ", index=" + index 
				+ ", parent=" + parentSring
				+ ", material=" + material
				+ ", type=" + type 
				+ ", color=" + color
				+ ", lengths=" + getLengths()
				+ ", volume=" + getVolume()
				+ ", center=" + getCenter()
				+ ", axisX=" + getAxisX()
				+ ", axisY=" + getAxisY() 
				+ ", axisZ=" + getAxisZ()
				+ ", typeLook=" + typeLook
				+ "]";
		//@formatter:on

	}

	public void validateBlock() {
		if (!isBlockDefined())
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
	}

}