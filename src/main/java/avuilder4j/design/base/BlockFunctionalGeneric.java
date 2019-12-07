package avuilder4j.design.base;

import java.util.Arrays;

import avuilder4j.data.DataMaps;
import avuilder4j.data.dtos.Material;
import avuilder4j.data.dtos.Shape;
import avuilder4j.data.dtos.Type;
import avuilder4j.data.dtos.TypeModel;
import avuilder4j.data.dtos.TypeModelByMaterial;
import avuilder4j.data.dtos.TypeModelByMaterial.MapIndex;
import avuilder4j.design.sub.dimensional.AxisEnds;
import avuilder4j.design.sub.dimensional.Vector;
import avuilder4j.error.AvValidations;
import avuilder4j.util.java.NullSafe;
import avuilder4j.util.keys.Cons;
import avuilder4j.util.keys.Types;

@SuppressWarnings("rawtypes")
public class BlockFunctionalGeneric<B extends BlockFunctionalGeneric> extends BlockPlanGeneric<B> {
	private static final long serialVersionUID = -7276801719926846539L;

	protected Material material;
	protected Shape shape;
	protected Type type;
	protected TypeModel typeModel;
	protected TypeModelByMaterial typeModelByMaterial;

	public Double getCreditCost() {
		return NullSafe.get(() -> getVolumeBlock() * material.getCreditCost() * typeModelByMaterial.getCreditCostMod());
	}

	public Double getCrewReqCommanders() {
		return NullSafe.get(() -> getCrewReqLieutenants()
				/ DataMaps.getConstantsMap().get(Cons.CREW_RATIO_LIEUTENANTS_PER_COMMANDER).getValue());
	}

	public Double getCrewReqEngineers() { return NullSafe.get(() -> getVolumeBlock() * typeModel.getEngineers()); }

	public Double getCrewReqGenerals() {
		return NullSafe.get(() -> getCrewReqCommanders()
				/ DataMaps.getConstantsMap().get(Cons.CREW_RATIO_COMMANDERS_PER_GENERAL).getValue());
	}

	public Double getCrewReqLieutenants() {
		return NullSafe.get(() -> getSergeantsReq()
				/ DataMaps.getConstantsMap().get(Cons.CREW_RATIO_SERGEANTS_PER_LIEUTENANT).getValue());
	}

	public Double getCrewReqMechanics() { return NullSafe.get(() -> getVolumeBlock() * typeModel.getMechanics()); }

	public Double getDensity() { return NullSafe.get(() -> material.getDensity() * typeModel.getDensityMod()); }

	public Double getDurability() {
		return NullSafe.get(() -> getVolumeBlock() * material.getDurability() * typeModel.getDurabilityMod());
	};

	public Double getEffAcademyCap() { return getEffectBasic(Types.ACADEMY); }

	public Double getEffCargoHold() { return getEffectStorage(Types.CARGO_BAY); }

	public Double getEffComputerCoreProcessingPower() { return getEffectBasic(Types.COMPUTER_CORE); }

	public Double getEffHangarSpace() { return getEffectStorage(Types.HANGAR); }

	public Double getEffTorpedoStorage() { return getEffectStorage(Types.TORPEDO_STORAGE); }

	public Double getEffAssemblyCap() { return getEffectBasic(Types.ASSEMBLY); }

	public Double getEffCloningCap() { return getEffectBasic(Types.CLONING_PODS); }

	public Double getEffEnergyContainerCap() { return getEffectBasic(Types.ENERGY_CONTAINER); }

	public Double getEffEnergyGenerated() { return getEffectBasic(Types.GENERATOR); }

	public Double getEffShieldGenerated() { return getEffectBasic(Types.SHIELD_GENERATOR); }

	public Vector getEffEngineForce() { return new Vector(getEffectBasic(Types.ENGINE), 0, 0); }

	public Double getEffForce() { return getEffectBasic(Types.ENGINE); }

	@Override
	public Integer getIndex() { return NullSafe.get(() -> type.getIndex()); }

	public Double getMass() { return NullSafe.get(() -> getDensity() * getVolumeBlock()); }

	public Material getMaterial() { return material; }

	public Double getMaterialCost() {
		return NullSafe.get(() -> getVolumeBlock() * material.getMaterialCost() * typeModel.getMaterialCostMod());
	}

	@Override
	public Integer getMaterialIndex() { return NullSafe.get(() -> material.getIndex()); }

	public Double getProcessingPowerBase() {
		return NullSafe.get(() -> {
			if (NullSafe.get(() -> typeModel.isProcess())) {
				return getVolumeBlock();
			} else
				return 0d;
		});
	};

	public Double getSergeantsReq() {
		return NullSafe.get(() -> {
			Double crew = (getCrewReqMechanics() + getCrewReqEngineers());
			Double ratio = DataMaps.getConstantsMap().get(Cons.CREW_RATIO_CREW_PER_SERGEANT).getValue();
			return crew / ratio;
		});
	}

	public Shape getShape() { return shape; }

	public Type getType() { return type; }

	@Override
	public Integer getTypeIndex() { return NullSafe.get(() -> type.getIndex()); }

	public TypeModel getTypeModel() { return typeModel; }

	public TypeModelByMaterial getTypeModelByMaterial() { return typeModelByMaterial; }

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

	public Double getVolumeBlock() { return NullSafe.get(() -> getVolumeCuboid() * shape.getVolumeMod()); }

	public Double getVolumeStat() {
		return NullSafe.get(() -> {
			if (NullSafe.get(() -> typeModel.isHasVolume())) {
				return getVolumeBlock();
			} else
				return 0d;
		});
	}

	@Override
	public void setMaterial(Integer materialIndex) {
		Material material = null;
		if (materialIndex != null) {
			AvValidations.keyInMap(materialIndex, DataMaps.getMaterialsMap());
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
			AvValidations.keyInMap(typeIndex, DataMaps.getTypesMap());
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
		return "Block [" + 
				"tags=\"" + tagsAdministrator.getTags() + "\"" + ", "
				+ "index=" + getIndex() + ", "
				+ "parent=" + parentSring + ", "
				+ "material=" + materialName + ", "
				+ "type=" + getTypeName() + ", "
				+ "color=" + getColor()
				+ ", lengths=" + getLengths() + ", "
				+ "volumeCuboid=" + getVolumeCuboid() + ", "
				+ "center=" + getCenter()
				+ ", axisX=" + getAxisX() + ", "
				+ "axisY=" + getAxisY() + ", "
				+ "axisZ=" + getAxisZ() + ", "
				+ "orientation=" + getOrientation() 
			+ "]";
		// @formatter:on

	}

	protected Double getEffectBasic(Integer typeModelIndex) {
		return NullSafe.get(() -> {
			if (typeModel.getIndex().equals(typeModelIndex)) {
				return getVolumeBlock() * typeModelByMaterial.getEffect();
			} else {
				return null;
			}
		});
	}

	protected Double getEffectStorage(Integer... index) {
		return NullSafe.get(() -> {
			if (Arrays.stream(index).anyMatch(typeModel.getIndex()::equals)) {
				return typeModelByMaterial.getEffect() * getEffectStorageVolume();
			} else {
				return null;
			}
		});
	}

	protected Double getEffectStorageVolume() {
		return NullSafe.get(() -> {
			Double vol = 1d;
			for (AxisEnds ends : getAllAxes()) {
				vol *= ends.getLength() - DataMaps.getConstantValue(Cons.CONTAINERS_WALL_THICKNESS);
			}
			return vol;
		});
	}

	protected void refreshShape() {
		shape = DataMaps.getShape(type.getShapeIndex());
	}

	protected void refreshTypeModel() {
		typeModel = DataMaps.getTypeModel(type.getTypeModelIndex());
	}

	protected void refreshTypeModelByMaterial() {
		TypeModelByMaterial.MapIndex idx = NullSafe.get(() -> new MapIndex(typeModel.getIndex(), material.getIndex()));
		typeModelByMaterial = DataMaps.getTypeModelByMaterial(idx);
	}

}
