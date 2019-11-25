package avuilder4j.design.base;

import avuilder4j.data.DataMaps;
import avuilder4j.data.Material;
import avuilder4j.data.Shape;
import avuilder4j.data.Type;
import avuilder4j.data.TypeModel;
import avuilder4j.data.TypeModelByMaterial;
import avuilder4j.data.TypeModelByMaterial.MapIndex;
import avuilder4j.error.AvErrors;
import avuilder4j.error.AvValidations;
import avuilder4j.error.Avuilder4jRuntimeException;

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

	public Double getHealth() {
		try {
			return getVolumeBlock() * material.getDurability() * typeModel.getDurabilityMod();
		} catch (NullPointerException e) {
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
		}
	}

	public Double getVolumeBlock() {
		try {
			return getVolumeCuboid() * shape.getVolumeMod();
		} catch (NullPointerException e) {
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
		}
	}

	public Double getVolumeStat() {
		try {
			return getVolumeBlock() * typeModel.getVolumeStatMod();
		} catch (NullPointerException e) {
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
		}
	}

	public Double getDensity() {
		try {
			return material.getDensity() * typeModel.getDensityMod();
		} catch (NullPointerException e) {
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
		}
	}

	public Double getMass() {
		try {
			return getDensity() * getVolumeBlock();
		} catch (NullPointerException e) {
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
		}
	}

	@Override
	public Integer getIndex() {
		if (type != null)
			return type.getIndex();
		else
			return null;
	}

	@Override
	public Integer getMaterialIndex() {
		if (material != null)
			return material.getIndex();
		else
			return null;
	}

	@Override
	public Integer getTypeIndex() {
		if (type != null)
			return type.getIndex();
		else
			return null;
	}

	public Material getMaterial() {
		return material;
	}

	public Shape getShape() {
		return shape;
	}

	public Type getType() {
		return type;
	}

	public TypeModel getTypeModel() {
		return typeModel;
	}

	public TypeModelByMaterial getTypeModelByMaterial() {
		return typeModelByMaterial;
	}

	@Override
	public void setMaterial(Integer materialIndex) {
		Material material = null;
		if (materialIndex != null) {
			AvValidations.keyInMap(materialIndex, dataMaps.getMaterialMap());
			material = dataMaps.getMaterialMap().get(materialIndex);
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
			AvValidations.keyInMap(typeIndex, dataMaps.getTypeMap());
			type = dataMaps.getTypeMap().get(typeIndex);
		}
		setType(type);
	}

	public void setType(Type type) {
		this.type = type;

		refreshTypeModel();
		refreshTypeModelByMaterial();
		refreshShape();
	}

	public DataMaps getDataMaps() {
		return dataMaps;
	}

	protected void refreshShape() {
		if (type != null) {
			AvValidations.keyInMap(type.getShapeIndex(), dataMaps.getShapeMap());
			shape = dataMaps.getShapeMap().get(type.getShapeIndex());
		} else {
			shape = null;
		}
	}

	protected void refreshTypeModel() {
		if (type != null) {
			try {
				AvValidations.keyInMap(type.getTypeModelIndex(), dataMaps.getTypeModelMap());
				typeModel = dataMaps.getTypeModelMap().get(type.getTypeModelIndex());
			} catch (NullPointerException e) {
				throw new Avuilder4jRuntimeException(AvErrors.INDEX_NOT_IN_MAPS, e);
			}
		} else {
			typeModel = null;
		}

	}

	protected void refreshTypeModelByMaterial() {
		if (typeModel != null && material != null) {
			try {
				TypeModelByMaterial.MapIndex idx = new MapIndex(typeModel.getIndex(), material.getIndex());
				AvValidations.keyInMap(idx, dataMaps.getTypeModelByMaterialMap());
				typeModelByMaterial = dataMaps.getTypeModelByMaterialMap().get(idx);
			} catch (NullPointerException e) {
				throw new Avuilder4jRuntimeException(AvErrors.INDEX_NOT_IN_MAPS, e);
			}
		} else {
			typeModel = null;
		}
	}

	public void setDataMaps(DataMaps dataMap) {
		this.dataMaps = dataMap;
	}

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

		String materialName;
		if (material != null) {
			materialName = material.getName();
		} else {
			materialName = "null";
		}

		// @formatter:off
		return "Block [" + "tags=\"" + tagsAdministrator.getTags() + "\"" + ", index=" + getIndex() + ", parent="
				+ parentSring + ", material=" + materialName + ", type=" + getTypeName() + ", color=" + getColor()
				+ ", lengths=" + getLengths() + ", volumeCuboid=" + getVolumeCuboid() + ", center=" + getCenter()
				+ ", axisX=" + getAxisX() + ", axisY=" + getAxisY() + ", axisZ=" + getAxisZ() + ", orientation="
				+ getOrientation() + "]";
		// @formatter:on

	}

}
