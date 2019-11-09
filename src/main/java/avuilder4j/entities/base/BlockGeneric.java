package avuilder4j.entities.base;

import avuilder4j.dtos.Lengths;
import avuilder4j.dtos.Orientation;
import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;
import avuilder4j.utils.AvValidations;

/**
 * Represents an Avorion full functional block in a structure.
 */
@SuppressWarnings("rawtypes")
public class BlockGeneric<T extends BlockGeneric> extends CuboidGeneric<T> implements BlockInterface {
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
	private Orientation orientation = new Orientation();

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

	public BlockGeneric(Integer material, Integer type, Orientation typeLook) {
		super();
		this.material = material;
		this.type = type;
		this.orientation = typeLook;
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
	@Override
	public String getColor() { return color; }

	@Override
	public Integer getMaterial() { return material; }

	/**
	 * Gets the {@link #orientation}.
	 * 
	 * @return the {@link #orientation}.
	 */
	public Orientation getOrientation() { return orientation; }

	@Override
	public Integer getType() { return type; }

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

	public boolean isBlockDefined() {
		if (!isCuboidDefined()) {
			return false;
		} else if (color == null || orientation == null || material == null || type == null) {
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
		AvValidations.colors(true, color);
		this.color = color;
	}

	public void setMaterial(Integer material) { this.material = material; }

	/**
	 * Sets the {@link #orientation}.
	 * 
	 * @param orientation the {@link #orientation} to set.
	 */
	public void setOrientation(Orientation orientation) { this.orientation = orientation; }

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
				+ ", orientation=" + orientation
				+ "]";
		//@formatter:on

	}

	public void validateBlock() {
		if (!isBlockDefined())
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
	}

	@Override
	public Integer getParentIndex() {
		if (getParent() != null)
			return getParent().getIndex();
		else
			return null;
	}

	@Override
	public Double getLX() {
		if (getAxisX() != null)
			return getAxisX().getLowerEnd();
		else
			return null;
	}

	@Override
	public Double getLY() {
		if (getAxisY() != null)
			return getAxisY().getLowerEnd();
		else
			return null;
	}

	@Override
	public Double getLZ() {
		if (getAxisZ() != null)
			return getAxisZ().getLowerEnd();
		else
			return null;
	}

	@Override
	public Double getUX() {
		if (getAxisX() != null)
			return getAxisX().getUpperEnd();
		else
			return null;
	}

	@Override
	public Double getUY() {
		if (getAxisY() != null)
			return getAxisY().getUpperEnd();
		else
			return null;
	}

	@Override
	public Double getUZ() {
		if (getAxisZ() != null)
			return getAxisZ().getUpperEnd();
		else
			return null;
	}

	@Override
	public Integer getLook() {
		if (getOrientation() != null)
			return getOrientation().getLook();
		else
			return null;
	}

	@Override
	public Integer getUp() {
		if (getOrientation() != null)
			return getOrientation().getUp();
		else
			return null;
	}

}