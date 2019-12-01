package avuilder4j.design.base;

import avuilder4j.data.DataMaps;
import avuilder4j.data.Material;
import avuilder4j.data.Shape;
import avuilder4j.data.Type;
import avuilder4j.data.TypeModel;
import avuilder4j.data.TypeModelByMaterial;
import avuilder4j.data.TypeModelByMaterial.MapIndex;
import avuilder4j.error.AvValidations;
import avuilder4j.util.NullSafe;

@SuppressWarnings("rawtypes")
public class BlockFunctionalGeneric<T extends BlockFunctionalGeneric> extends BlockPlanGeneric<T> {
	private static final long serialVersionUID = -7276801719926846539L;

	protected DataMaps dataMaps;

	protected Material material;
	protected Shape shape;
	protected Type type;
	protected TypeModel typeModel;
	protected TypeModelByMaterial typeModelByMaterial;

	public BlockFunctionalGeneric(DataMaps generalDataMap) {
		this.dataMaps = generalDataMap;
	}

	public Double getDurability() {
		return NullSafe.get(() -> getVolumeBlock() * material.getDurability() * typeModel.getDurabilityMod());
	}

	public Double getVolumeBlock() { return NullSafe.get(() -> getVolumeCuboid() * shape.getVolumeMod()); }

	public Double getVolumeStat() { return NullSafe.get(() -> getVolumeBlock() * typeModel.getVolumeStatMod()); }

	public Double getDensity() { return NullSafe.get(() -> material.getDensity() * typeModel.getDensityMod()); }

	public Double getMass() { return NullSafe.get(() -> getDensity() * getVolumeBlock()); }

	@Override
	public Integer getIndex() { return NullSafe.get(() -> type.getIndex()); }

	@Override
	public Integer getMaterialIndex() { return NullSafe.get(() -> material.getIndex()); }

	@Override
	public Integer getTypeIndex() { return NullSafe.get(() -> type.getIndex()); }

	public Material getMaterial() { return material; }

	public Shape getShape() { return shape; }

	public Type getType() { return type; }

	public TypeModel getTypeModel() { return typeModel; }

	public TypeModelByMaterial getTypeModelByMaterial() { return typeModelByMaterial; }

	@Override
	public void setMaterial(Integer materialIndex) {
		Material material = null;
		if (materialIndex != null) {
			AvValidations.keyInMap(materialIndex, NullSafe.get(() -> dataMaps.getMaterialMap()));
			material = NullSafe.get(() -> dataMaps.getMaterialMap().get(materialIndex));
		}
		setMaterial(material);
	}

	public void setMaterial(Material material) {
		this.material = material;
		refreshTypeModelByMaterial();
	}

	@Override
	public void setType(Integer typeIndex) {
		Type type = null;
		if (typeIndex != null) {
			AvValidations.keyInMap(typeIndex, NullSafe.get(() -> dataMaps.getTypeMap()));
			type = NullSafe.get(() -> dataMaps.getTypeMap().get(typeIndex));
		}
		setType(type);
	}

	public void setType(Type type) {
		this.type = type;

		refreshTypeModel();
		refreshTypeModelByMaterial();
		refreshShape();
	}

	public DataMaps getDataMaps() { return dataMaps; }

	protected void refreshShape() {
		if (type != null) {
			AvValidations.keyInMap(type.getShapeIndex(), NullSafe.get(() -> dataMaps.getShapeMap()));
			shape = NullSafe.get(() -> dataMaps.getShapeMap().get(type.getShapeIndex()));
		} else {
			shape = null;
		}
	}

	protected void refreshTypeModel() {
		if (type != null) {
			AvValidations.keyInMap(type.getTypeModelIndex(), NullSafe.get(() -> dataMaps.getTypeModelMap()));
			typeModel = NullSafe.get(() -> dataMaps.getTypeModelMap().get(type.getTypeModelIndex()));
		} else {
			typeModel = null;
		}
	}

	protected void refreshTypeModelByMaterial() {
		if (typeModel != null && material != null) {

			TypeModelByMaterial.MapIndex idx = new MapIndex(typeModel.getIndex(), material.getIndex());
			AvValidations.keyInMap(idx, NullSafe.get(() -> dataMaps.getTypeModelByMaterialMap()));
			typeModelByMaterial = NullSafe.get(() -> dataMaps.getTypeModelByMaterialMap().get(idx));

		} else {
			typeModel = null;
		}
	}

	public void setDataMaps(DataMaps dataMap) { this.dataMaps = dataMap; }

	public String getTypeName() {
		String typeName;
		if (typeModel != null) {
			typeName = typeModel.getName();
			if (shape != null) {
				if (!typeName.trim().isEmpty()) {
					typeName += " " + shape.getName();
				}
			}
		} else {
			typeName = "null";
		}
		return typeName;
	}

	@Override
	public String toString() {

		String parentSring = null;
		if (parent != null)
			parentSring = "[id=" + parent.getIndex() + "]";

		String materialName = NullSafe.get(() -> material.getName());

		// @formatter:off
		return "Block [" + "tags=\"" + tagsAdministrator.getTags() + "\"" + ", index=" + getIndex() + ", parent="
				+ parentSring + ", material=" + materialName + ", type=" + getTypeName() + ", color=" + getColor()
				+ ", lengths=" + getLengths() + ", volumeCuboid=" + getVolumeCuboid() + ", center=" + getCenter()
				+ ", axisX=" + getAxisX() + ", axisY=" + getAxisY() + ", axisZ=" + getAxisZ() + ", orientation="
				+ getOrientation() + "]";
		// @formatter:on

	}

}
