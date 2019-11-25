package avuilder4j.design.base;

import avuilder4j.data.loaders.dtos.DataMaps;
import avuilder4j.data.loaders.dtos.Material;
import avuilder4j.data.loaders.dtos.Shape;
import avuilder4j.data.loaders.dtos.Type;
import avuilder4j.data.loaders.dtos.TypeModel;
import avuilder4j.data.loaders.dtos.TypeModelByMaterial;
import avuilder4j.data.loaders.dtos.TypeModelByMaterial.MapIndex;
import avuilder4j.error.AvValidations;

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

	public Double getVolumeBlock() {
		Double v = null;
		if (shape != null && getVolumeCuboid() != null && shape.getVolumeMod() != null) {
			v = getVolumeCuboid() * shape.getVolumeMod();
		}
		return v;
	}

	public Double getVolumeStat() {
		Double v = null;
		if (getVolumeBlock() != null && typeModel != null && typeModel.getVolumeStatMod() != null) {
			v = getVolumeBlock() * typeModel.getVolumeStatMod();
		}
		return v;
	}

	public Double getDensity() {
		Double d = null;
		if (material != null && material.getDensity() != null && typeModel != null
				&& typeModel.getDensityMod() != null) {
			d = material.getDensity() * typeModel.getDensityMod();
		}
		return d;
	}

	public Double getMass() {
		Double m = null;
		if (getDensity() != null && getVolumeBlock() != null) {
			m = getDensity() * getVolumeBlock();
		}
		return m;
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

	public Material getMaterial() { return material; }

	public Shape getShape() { return shape; }

	public Type getType() { return type; }

	public TypeModel getTypeModel() { return typeModel; }

	public TypeModelByMaterial getTypeModelByMaterial() { return typeModelByMaterial; }

	@Override
	public void setMaterial(Integer materialIndex) {
		Material material = null;
		if (materialIndex != null) {
			AvValidations.mapNotNull(dataMaps, "Data Maps");
			AvValidations.mapNotNull(dataMaps.getMaterialMap(), "Materials");
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
			AvValidations.mapNotNull(dataMaps, "Data Maps");
			AvValidations.mapNotNull(dataMaps.getTypeMap(), "Types");
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

	public DataMaps getDataMaps() { return dataMaps; }

	protected void refreshShape() {
		if (type != null) {
			AvValidations.mapNotNull(dataMaps, "Data Maps");
			AvValidations.mapNotNull(dataMaps.getShapeMap(), "Shapes");
			AvValidations.keyInMap(type.getShapeIndex(), dataMaps.getShapeMap());

			shape = dataMaps.getShapeMap().get(type.getShapeIndex());

		} else {
			shape = null;
		}
	}

	protected void refreshTypeModel() {
		if (type != null) {
			AvValidations.mapNotNull(dataMaps, "Data Maps");
			AvValidations.mapNotNull(dataMaps.getTypeModelMap(), "TypeModels");
			AvValidations.keyInMap(type.getTypeModelIndex(), dataMaps.getTypeModelMap());

			typeModel = dataMaps.getTypeModelMap().get(type.getTypeModelIndex());

		} else {
			typeModel = null;
		}

	}

	protected void refreshTypeModelByMaterial() {
		if (typeModel != null && material != null) {
			AvValidations.mapNotNull(dataMaps, "Data Maps");
			AvValidations.mapNotNull(dataMaps.getTypeMap(), "TypeModelByMaterials");
			TypeModelByMaterial.MapIndex idx = new MapIndex(typeModel.getIndex(), material.getIndex());

			AvValidations.keyInMap(idx, dataMaps.getTypeModelByMaterialMap());
			typeModelByMaterial = dataMaps.getTypeModelByMaterialMap().get(idx);

		} else {
			typeModelByMaterial = null;
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

		String materialName;
		if (material != null) {
			materialName = material.getName();
		} else {
			materialName = "null";
		}

		//@formatter:off
		return "Block ["
				+ "tags=\"" + tagsAdministrator.getTags() + "\""
				+ ", index=" + getIndex() 
				+ ", parent=" + parentSring
				+ ", material=" + materialName
				+ ", type=" + getTypeName()
				+ ", color=" + getColor()
				+ ", lengths=" + getLengths()
				+ ", volumeCuboid=" + getVolumeCuboid()
				+ ", center=" + getCenter()
				+ ", axisX=" + getAxisX()
				+ ", axisY=" + getAxisY() 
				+ ", axisZ=" + getAxisZ()
				+ ", orientation=" + getOrientation()
				+ "]";
		//@formatter:on

	}

}
