package avuilder4j.design.base;

import avuilder4j.data.DataMaps;
import avuilder4j.data.dtos.Material;
import avuilder4j.data.dtos.Shape;
import avuilder4j.data.dtos.Type;
import avuilder4j.data.dtos.TypeModel;
import avuilder4j.data.dtos.TypeModelByMaterial;
import avuilder4j.data.dtos.TypeModelByMaterial.MapIndex;
import avuilder4j.error.AvValidations;
import avuilder4j.util.java.NullSafe;
import avuilder4j.util.values.Metas;

@SuppressWarnings("rawtypes")
public class BlockFunctionalGeneric<T extends BlockFunctionalGeneric> extends BlockPlanGeneric<T> {
	private static final long serialVersionUID = -7276801719926846539L;

	protected Material material;
	protected Shape shape;
	protected Type type;
	protected TypeModel typeModel;
	protected TypeModelByMaterial typeModelByMaterial;

	public Double getCreditCost() {
		return NullSafe.get(() -> getVolumeBlock() * material.getCreditCost() * typeModelByMaterial.getCreditCostMod());
	};

	public Double getDensity() {
		return NullSafe.get(() -> material.getDensity() * typeModel.getDensityMod());
	}

	public Double getDurability() {
		return NullSafe.get(() -> getVolumeBlock() * material.getDurability() * typeModel.getDurabilityMod());
	}

	public Double getEngineersReq() {
		return NullSafe.get(() -> getVolumeBlock() * typeModel.getEngineers() / 1000);
	}

	public Double getSergeantsReq() {
		return NullSafe.get(() -> {
			Double crew = (getMechanicsReq() + getEngineersReq());
			Double ratio = DataMaps.getMetaValueMap().get(Metas.CREW_RATIO_CREW_PER_SERGEANT).getNumericValue();
			return crew / ratio;
		});
	}

	public Double getLieutenantsReq() {
		return NullSafe.get(() -> getSergeantsReq()
				/ DataMaps.getMetaValueMap().get(Metas.CREW_RATIO_SERGEANTS_PER_LIEUTENANT).getNumericValue());
	}

	public Double getCommandersReq() {
		return NullSafe.get(() -> getLieutenantsReq()
				/ DataMaps.getMetaValueMap().get(Metas.CREW_RATIO_LIEUTENANTS_PER_COMMANDER).getNumericValue());
	}

	public Double getGeneralsReq() {
		return NullSafe.get(() -> getCommandersReq()
				/ DataMaps.getMetaValueMap().get(Metas.CREW_RATIO_COMMANDERS_PER_GENERAL).getNumericValue());
	}

	@Override
	public Integer getIndex() {
		return NullSafe.get(() -> type.getIndex());
	}

	public Double getMass() {
		return NullSafe.get(() -> getDensity() * getVolumeBlock());
	}

	public Material getMaterial() {
		return material;
	}

	public Double getMaterialCost() {
		return NullSafe.get(() -> getVolumeBlock() * material.getMaterialCost() * typeModel.getMaterialCostMod());
	};

	@Override
	public Integer getMaterialIndex() {
		return NullSafe.get(() -> material.getIndex());
	}

	public Double getMechanicsReq() {
		return NullSafe.get(() -> getVolumeBlock() * typeModel.getMechanics() / 1000);
	}

	public Double getProcessingPower() {
		return NullSafe.get(() -> getVolumeBlock() * typeModel.getProcessingMod());
	}

	public Shape getShape() {
		return shape;
	}

	public Type getType() {
		return type;
	}

	@Override
	public Integer getTypeIndex() {
		return NullSafe.get(() -> type.getIndex());
	}

	public TypeModel getTypeModel() {
		return typeModel;
	}

	public TypeModelByMaterial getTypeModelByMaterial() {
		return typeModelByMaterial;
	}

	public String getTypeName() {
		if (typeModel != null) {
			StringBuilder typeName = new StringBuilder();
			typeName.append(typeModel.getName());
			if (shape != null && !shape.getName().trim().isEmpty()) {
				typeName.append(" ").append(shape.getName());
			}
			return typeName.toString();
		} else {
			return null;
		}
	}

	public Double getVolumeBlock() {
		return NullSafe.get(() -> getVolumeCuboid() * shape.getVolumeMod());
	}

	public Double getVolumeStat() {
		return NullSafe.get(() -> getVolumeBlock() * typeModel.getVolumeStatMod());
	}

	@Override
	public void setMaterial(Integer materialIndex) {
		Material material = null;
		if (materialIndex != null) {
			AvValidations.keyInMap(materialIndex, DataMaps.getMaterialMap());
			material = DataMaps.getMaterial(materialIndex);
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
			AvValidations.keyInMap(typeIndex, DataMaps.getTypeMap());
			type = DataMaps.getType(typeIndex);
		}
		setType(type);
	}

	public void setType(Type type) {
		this.type = type;

		refreshTypeModel();
		refreshTypeModelByMaterial();
		refreshShape();
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

	protected void refreshShape() {
		if (type != null) {
			shape = NullSafe.get(() -> DataMaps.getShape(type.getShapeIndex()));
		} else {
			shape = null;
		}
	}

	protected void refreshTypeModel() {
		if (type != null) {
			typeModel = NullSafe.get(() -> DataMaps.getTypeModel(type.getTypeModelIndex()));
		} else {
			typeModel = null;
		}
	}

	protected void refreshTypeModelByMaterial() {
		if (typeModel != null && material != null) {
			TypeModelByMaterial.MapIndex idx = new MapIndex(typeModel.getIndex(), material.getIndex());
			typeModelByMaterial = DataMaps.getTypeModelByMaterial(idx);
		} else {
			typeModel = null;
		}
	}

}
