package avuilder4j.design.base;

import avuilder4j.design.sub.dimensional.Lengths;
import avuilder4j.design.sub.dimensional.Orientation;
import avuilder4j.error.AvValidations;
import avuilder4j.util.java.Nullable;

/**
 * Represents an Avorion full functional block in a structure.
 */
@SuppressWarnings("rawtypes")
public abstract class BlockPlanGeneric<T extends BlockPlanGeneric> extends CuboidGeneric<T>
		implements BlockPlanInterfaceExporter, BlockPlanInterfaceImporter<T> {
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
	private Integer materialIndex;

	/**
	 * Block's orientation
	 */
	private Orientation orientation = new Orientation();

	/**
	 * Block's type.
	 */
	private Integer typeIndex;

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
		this.materialIndex = material;
		this.typeIndex = type;
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
		if (materialIndex == null) {
			if (other.materialIndex != null)
				return false;
		} else if (!materialIndex.equals(other.materialIndex))
			return false;
		if (orientation == null) {
			if (other.orientation != null)
				return false;
		} else if (!orientation.equals(other.orientation))
			return false;
		if (typeIndex == null) {
			if (other.typeIndex != null)
				return false;
		} else if (!typeIndex.equals(other.typeIndex))
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
	public Integer getMaterialIndex() { return materialIndex; }

	/**
	 * Gets the {@link #orientation}.
	 * 
	 * @return the {@link #orientation}.
	 */
	public Orientation getOrientation() { return orientation; }

	@Override
	public Integer getTypeIndex() { return typeIndex; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((materialIndex == null) ? 0 : materialIndex.hashCode());
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
		result = prime * result + ((typeIndex == null) ? 0 : typeIndex.hashCode());
		return result;
	}

	@Override
	public boolean isBlockPlanDefined() {
		if (!isCuboidDefined()) {
			return false;
		} else if (color == null || orientation == null || materialIndex == null || typeIndex == null) {
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
	@Override
	public T setColor(String color) {
		AvValidations.colors(true, color);
		this.color = color;
		return chain();
	}

	@Override
	public T setMaterialIndex(Integer materialIndex) {
		this.materialIndex = materialIndex;
		return chain();
	}

	/**
	 * Sets the {@link #orientation}.
	 * 
	 * @param orientation the {@link #orientation} to set.
	 */
	public T setOrientation(Orientation orientation) {
		this.orientation = orientation;
		return chain();
	}

	@Override
	public T setTypeIndex(Integer typeIndex) {
		this.typeIndex = typeIndex;
		return chain();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BlockPlan [");
		builder.append(toStringHeader());
		builder.append(", ");
		builder.append(toStringBodyPlanIndexes());
		builder.append(", ");
		builder.append(toStringBodyPlanValues());
		builder.append(", ");
		builder.append(toStringBodyCuboid());
		builder.append("]");
		return builder.toString();
	}

	protected String toStringBodyPlanIndexes() {
		StringBuilder builder = new StringBuilder();
		builder.append("materialIndex=");
		builder.append(materialIndex);
		builder.append(", typeIndex=");
		builder.append(typeIndex);
		return builder.toString();
	}

	protected String toStringBodyPlanValues() {
		StringBuilder builder = new StringBuilder();
		builder.append("color=");
		builder.append(color);
		builder.append(", orientation=");
		builder.append(orientation);
		return builder.toString();
	}

	@Override
	public void validateBlockPlan() {
		validateCuboid();
		AvValidations.indexes(false, getIndex());
		if (getParentIndex() != null && !getParentIndex().equals(-1))
			AvValidations.indexes(true, getParentIndex());
		AvValidations.notNull(getMaterialIndex(), "Material");
		AvValidations.notNull(getTypeIndex(), "Type");
		AvValidations.orientations(false, getOrientation());
		AvValidations.colors(false, getColor());
		AvValidations.ends(false, getXL(), getXU());
		AvValidations.ends(false, getYL(), getYU());
		AvValidations.ends(false, getZL(), getZU());
	}

	@Override
	public Integer getParentIndex() {
		if (getParent() != null)
			return getParent().getIndex();
		else
			return null;
	}

	@Override
	public Double getXL() {
		if (getAxisX() != null)
			return getAxisX().getLowerEnd();
		else
			return null;
	}

	@Override
	public Double getYL() {
		if (getAxisY() != null)
			return getAxisY().getLowerEnd();
		else
			return null;
	}

	@Override
	public Double getZL() {
		if (getAxisZ() != null)
			return getAxisZ().getLowerEnd();
		else
			return null;
	}

	@Override
	public Double getXU() {
		if (getAxisX() != null)
			return getAxisX().getUpperEnd();
		else
			return null;
	}

	@Override
	public Double getYU() {
		if (getAxisY() != null)
			return getAxisY().getUpperEnd();
		else
			return null;
	}

	@Override
	public Double getZU() {
		if (getAxisZ() != null)
			return getAxisZ().getUpperEnd();
		else
			return null;
	}

	@Override
	public Integer getLook() { return Nullable.run(() -> getOrientation().getLook().getIndex()); }

	@Override
	public Integer getUp() { return Nullable.run(() -> getOrientation().getUp().getIndex()); }

	@Override
	public T setLook(Integer look) {
		Nullable.run(() -> getOrientation().setLook(look));
		return chain();
	}

	@Override
	public T setUp(Integer up) {
		Nullable.run(() -> getOrientation().setUp(up));
		return chain();
	}

}