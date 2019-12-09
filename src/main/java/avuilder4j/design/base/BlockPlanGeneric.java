package avuilder4j.design.base;

import avuilder4j.design.sub.dimensional.Lengths;
import avuilder4j.design.sub.dimensional.Orientation;
import avuilder4j.error.AvValidations;
import avuilder4j.util.java.NullSafe;

/**
 * Represents an Avorion full functional block in a structure.
 */
@SuppressWarnings("rawtypes")
public abstract class BlockPlanGeneric<T extends BlockPlanGeneric> extends CuboidGeneric<T>
		implements BlockPlanInterface {
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
	protected String color;

	/**
	 * Block's material.
	 */
	protected Integer material;

	/**
	 * Block's orientation
	 */
	protected Orientation orientation = new Orientation();

	/**
	 * Block's type.
	 */
	protected Integer type;

	public BlockPlanGeneric() {}

	public BlockPlanGeneric(Lengths lengths) {
		super(lengths);
	}

	public BlockPlanGeneric(Integer index) {
		super(index);
	}

	public BlockPlanGeneric(Integer index, T parent) {
		super(index, parent);
	}

	public BlockPlanGeneric(Integer material, Integer type, Orientation typeLook) {
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
		BlockPlanGeneric other = (BlockPlanGeneric) obj;
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
	public Integer getMaterialIndex() { return material; }

	/**
	 * Gets the {@link #orientation}.
	 * 
	 * @return the {@link #orientation}.
	 */
	public Orientation getOrientation() { return orientation; }

	@Override
	public Integer getTypeIndex() { return type; }

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

	@Override
	public boolean isBlockPlanDefined() {
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
	public T setColor(String color) {
		AvValidations.colors(true, color);
		this.color = color;
		return returnThis();
	}

	public T setMaterial(Integer material) {
		this.material = material;
		return returnThis();
	}

	/**
	 * Sets the {@link #orientation}.
	 * 
	 * @param orientation the {@link #orientation} to set.
	 */
	public T setOrientation(Orientation orientation) {
		this.orientation = orientation;
		return returnThis();
	}

	public T setType(Integer type) {
		this.type = type;
		return returnThis();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String parentSring = null;
		if (parent != null)
			parentSring = "[id=" + parent.getIndexInStructure() + "]";

		//@formatter:off
		return "BlockPlan ["
				+ "tags=\"" + getTagsAdministrator().getTags() + "\""
				+ ", index=" + indexInStructure 
				+ ", parent=" + parentSring
				+ ", material=" + material
				+ ", type=" + type 
				+ ", color=" + color
				+ ", lengths=" + getLengths()
				+ ", volumeCuboid=" + getVolumeCuboid()
				+ ", center=" + getCenter()
				+ ", axisX=" + getAxisX()
				+ ", axisY=" + getAxisY() 
				+ ", axisZ=" + getAxisZ()
				+ ", orientation=" + orientation
				+ "]";
		//@formatter:on

	}

	@Override
	public void validateBlockPlan() {
		validateCuboid();
		AvValidations.indexes(false, getIndexInStructure());
		if (getParentIndex() != null && !getParentIndex().equals(-1))
			AvValidations.indexes(true, getParentIndex());
		AvValidations.notNull(getMaterialIndex(), "Material");
		AvValidations.notNull(getTypeIndex(), "Type");
		AvValidations.orientations(false, getOrientation());
		AvValidations.colors(false, getColor());
		AvValidations.ends(false, getLX(), getUX());
		AvValidations.ends(false, getLY(), getUY());
		AvValidations.ends(false, getLZ(), getUZ());
	}

	@Override
	public Integer getParentIndex() {
		if (getParent() != null)
			return getParent().getIndexInStructure();
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
	public Integer getLook() { return NullSafe.go(() -> getOrientation().getLook().getIndex()); }

	@Override
	public Integer getUp() { return NullSafe.go(() -> getOrientation().getUp().getIndex()); }

}