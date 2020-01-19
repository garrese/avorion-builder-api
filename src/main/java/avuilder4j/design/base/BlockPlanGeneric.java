package avuilder4j.design.base;

import java.util.Objects;

import avuilder4j.design.sub.dimensional.Lengths;
import avuilder4j.design.sub.dimensional.Orientation;
import avuilder4j.error.AvValidations;
import avuilder4j.util.java.Nullable;

/**
 * Represents an Avorion full functional block in a structure.
 */
@SuppressWarnings("rawtypes")
public abstract class BlockPlanGeneric<T extends BlockPlanGeneric<T>> extends CuboidGeneric<T> implements BlockInterfaceExporter, BlockInterfaceImporter<T> {
	private static final long serialVersionUID = -1896528590585386376L;

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

	public BlockPlanGeneric(Integer index) {
		super(index);
	}

	public BlockPlanGeneric(Integer material, Integer type, Orientation typeLook) {
		super();
		this.materialIndex = material;
		this.typeIndex = type;
		this.orientation = typeLook;
	}

	public BlockPlanGeneric(Integer index, T parent) {
		super(index, parent);
	}

	public BlockPlanGeneric(Lengths lengths) {
		super(lengths);
	}

	public static <T extends BlockPlanGeneric> T copy(T copyFrom, T copyTo) {
		if (copyFrom != null && copyTo != null) {
			CuboidGeneric.copy(copyFrom, copyTo);
			copyTo.setMaterialIndex(copyFrom.getMaterialIndex());
			copyTo.setTypeIndex(copyFrom.getTypeIndex());
			copyTo.setColor(copyFrom.getColor());
			copyTo.setOrientation(Nullable.m(() -> copyFrom.getOrientation().getCopy()));
		}
		return copyTo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof BlockPlanGeneric))
			return false;
		BlockPlanGeneric other = (BlockPlanGeneric) obj;
		return Objects.equals(color, other.color) && Objects.equals(materialIndex, other.materialIndex) && Objects.equals(orientation, other.orientation) && Objects.equals(typeIndex, other.typeIndex);
	}

	/**
	 * Gets the {@link #color}.
	 * 
	 * @return the {@link #color}.
	 */
	@Override
	public String getColor() { return color; }

	@Override
	public Integer getLook() { return Nullable.m(() -> getOrientation().getLook().getIndex()); }

	@Override
	public Integer getMaterialIndex() { return materialIndex; }

	/**
	 * Gets the {@link #orientation}.
	 * 
	 * @return the {@link #orientation}.
	 */
	public Orientation getOrientation() { return orientation; }

	@Override
	public Integer getParentIndex() {
		if (getParent() != null)
			return getParent().getIndex();
		else
			return null;
	}

	@Override
	public Integer getTypeIndex() { return typeIndex; }

	@Override
	public Integer getUp() { return Nullable.m(() -> getOrientation().getUp().getIndex()); }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(color, materialIndex, orientation, typeIndex);
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
	public T setLook(Integer look) {
		Nullable.m(() -> getOrientation().setLook(look));
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
	public T setUp(Integer up) {
		Nullable.m(() -> getOrientation().setUp(up));
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

	public String toStringBodyPlanIndexes() {
		StringBuilder builder = new StringBuilder();
		builder.append("materialIndex=");
		builder.append(materialIndex);
		builder.append(", typeIndex=");
		builder.append(typeIndex);
		return builder.toString();
	}

	public String toStringBodyPlanValues() {
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

}