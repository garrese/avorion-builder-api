package avuilder4j.structural;

import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;
import avuilder4j.spatial.CuboidGeneric;
import avuilder4j.spatial.auxs.Lengths;
import avuilder4j.structural.dtos.MaterialStructural;
import avuilder4j.structural.dtos.TypeLook;
import avuilder4j.structural.dtos.TypeStructural;
import avuilder4j.utils.AvValidations;

public class BlockStructuralGeneric<M extends MaterialStructural, T extends TypeStructural>
		extends CuboidGeneric<BlockStructuralGeneric<M, T>> {
	private static final long serialVersionUID = -1896528590585386376L;

	/**
	 * Block's color in 8 digit color hex format.
	 */
	private String color;

	/**
	 * Block's material.
	 */
	private M material;

	/**
	 * Block's orientation
	 */
	private TypeLook typeLook = new TypeLook();

	/**
	 * Block's type.
	 */
	private T type;

	public BlockStructuralGeneric() {}

	public BlockStructuralGeneric(Lengths lengths) {
		super(lengths);
	}

	public BlockStructuralGeneric(Integer index) {
		super(index);
	}

	public BlockStructuralGeneric(Integer index, BlockStructuralGeneric<M, T> parent) {
		super(index, parent);
	}

	public BlockStructuralGeneric(M material, T type, TypeLook typeLook) {
		super();
		this.material = material;
		this.type = type;
		this.typeLook = typeLook;
	}

	/**
	 * Gets the {@link #color}.
	 * 
	 * @return the {@link #color}.
	 */
	public String getColor() { return color; }

	public M getMaterial() { return material; }

	/**
	 * Gets the {@link #typeLook}.
	 * 
	 * @return the {@link #typeLook}.
	 */
	public TypeLook getTypeLook() { return typeLook; }

	public T getType() { return type; }

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

	public void setMaterial(M material) { this.material = material; }

	/**
	 * Sets the {@link #typeLook}.
	 * 
	 * @param typeLook the {@link #typeLook} to set.
	 */
	public void setTypeLook(TypeLook typeLook) { this.typeLook = typeLook; }

	public void setType(T type) { this.type = type; }

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
