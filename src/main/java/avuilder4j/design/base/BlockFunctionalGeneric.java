package avuilder4j.design.base;

import java.util.Arrays;

import avuilder4j.data.DataMaps;
import avuilder4j.data.dtos.Material;
import avuilder4j.data.dtos.Shape;
import avuilder4j.data.dtos.Type;
import avuilder4j.data.dtos.TypeModel;
import avuilder4j.data.dtos.TypeModelByMaterial;
import avuilder4j.data.dtos.TypeModelByMaterial.MapIndex;
import avuilder4j.design.enums.Axis;
import avuilder4j.design.sub.dimensional.AxisEnds;
import avuilder4j.design.sub.functional.AuraRanges;
import avuilder4j.design.sub.functional.HangarSpace;
import avuilder4j.design.sub.functional.PropulsionForces;
import avuilder4j.design.sub.functional.RotationForces;
import avuilder4j.error.AvValidations;
import avuilder4j.util.java.DoAndReturn;
import avuilder4j.util.java.NullSafe;
import avuilder4j.util.keys.Cons;
import avuilder4j.util.keys.Types;

@SuppressWarnings("rawtypes")
public abstract class BlockFunctionalGeneric<T extends BlockFunctionalGeneric> extends BlockPlanGeneric<T> {
	private static final long serialVersionUID = -7276801719926846539L;

	protected Material material;
	protected Shape shape;
	protected Type type;
	protected TypeModel typeModel;
	protected TypeModelByMaterial typeModelByMaterial;

	public Double getCreditCost() {
		return NullSafe.go(() -> getVolumeBlock() * material.getCreditCost() * typeModelByMaterial.getCreditCostMod());
	}

	public Double getCrewReqCommanders() {
		return NullSafe.go(() -> getCrewReqLieutenants()
				/ DataMaps.getConstantValue(Cons.CREW_RATIO_LIEUTENANTS_PER_COMMANDER));
	}

	public Double getCrewReqEngineers() { return NullSafe.go(() -> getVolumeBlock() * typeModel.getEngineers()); }

	public Double getCrewReqGenerals() {
		return NullSafe.go(() -> getCrewReqCommanders()
				/ DataMaps.getConstantsMap().get(Cons.CREW_RATIO_COMMANDERS_PER_GENERAL).getValue());
	}

	public Double getCrewReqLieutenants() {
		return NullSafe.go(() -> getSergeantsReq()
				/ DataMaps.getConstantsMap().get(Cons.CREW_RATIO_SERGEANTS_PER_LIEUTENANT).getValue());
	}

	public Double getCrewReqMechanics() { return NullSafe.go(() -> getVolumeBlock() * typeModel.getMechanics()); }

	public Double getDensity() { return NullSafe.go(() -> material.getDensity() * typeModel.getDensityMod()); }

	public Double getDurability() {
		return NullSafe.go(() -> getVolumeBlock() * material.getDurability() * typeModel.getDurabilityMod());
	};

	public Double getEffAcademyCap() { return getEffectByVolumeIfType(Types.ACADEMY); }

	public Double getEffAssemblyCap() { return getEffectByVolumeIfType(Types.ASSEMBLY); }

	private Axis getEffAxisDirectionalThruster() {
		// TODO investigate
		return Axis.X;
	}

	private Axis getEffAxisGyroArray() {
		// TODO investigate
		return Axis.X;
	}

	public Double getEffCargoHold() { return getEffectStorageIfType(Types.CARGO_BAY); }

	public Double getEffCloningCap() { return getEffectByVolumeIfType(Types.CLONING_PODS); }

	public Double getEffComputerCoreProcessingPower() { return getEffectByVolumeIfType(Types.COMPUTER_CORE); }

	public PropulsionForces getEffDirectionalThrusterForces() {
		Double f = getEffectByVolumeIfType(Types.DIRECTIONAL_THRUSTER);
		if (f != null) {
			Axis a = getEffAxisDirectionalThruster();
			PropulsionForces pfs = new PropulsionForces();
			pfs.getAcceleration().setXyzByAxis(a, f);
			pfs.getDeceleration().setXyzByAxis(a, f);
			if (a.equals(Axis.Z)) {
				pfs.getAcceleration().setZ(0d);
			}
			return pfs;
		} else
			return null;
	}

	protected Double getEffectByVolumeIfType(Integer typeIndexFilter) {
		return NullSafe.go(() -> {
			if (isTypeOf(typeIndexFilter)) {
				return getVolumeBlock() * typeModelByMaterial.getEffect();
			} else {
				return null;
			}
		});
	}

	protected Double getEffectStorageIfType(Integer typeIndexFilter) {
		return NullSafe.go(() -> {
			if (isTypeOf(typeIndexFilter)) {
				return typeModelByMaterial.getEffect() * getEffectStorageVolume();
			} else {
				return null;
			}
		});
	}

	protected Double getEffectStorageVolume() {
		return NullSafe.go(() -> {
			Double vol = 1d;
			for (AxisEnds ends : getAllAxes()) {
				vol *= ends.getLength() - DataMaps.getConstantValue(Cons.CONTAINERS_WALL_THICKNESS);
			}
			return vol;
		});
	}

	public Double getEffEnergyContainerECap() { return getEffectByVolumeIfType(Types.ENERGY_CONTAINER); }

	public PropulsionForces getEffEngineForces() {
		Double f = getEffectByVolumeIfType(Types.ENGINE);
		if (f != null)
			return DoAndReturn.go(new PropulsionForces(), (p) -> p.getAcceleration().setZ(f));
		else
			return null;
	}

	public Double getEffGeneratorGeneratedE() { return getEffectByVolumeIfType(Types.GENERATOR); }

	public RotationForces getEffGyroArrayForces() {
		Double f = getEffectByVolumeIfType(Types.GYRO_ARRAY);
		if (f != null) {
			Axis a = getEffAxisGyroArray();
			RotationForces rfs = new RotationForces();
			rfs.setXyzByAxis(a, f);
			rfs.setXyzByAxis(a, f);
			return rfs;
		} else
			return null;
	}

	public HangarSpace getEffHangarSpace() {
		Double s = getEffectStorageIfType(Types.HANGAR);
		if (s != null)
			return new HangarSpace(s);
		else
			return null;
	}

	public Double getEffHyperspaceCorePower() { return getEffectByVolumeIfType(Types.HYPERSPACE_CORE); }

	public PropulsionForces getEffInertiaDampenerForces() {
		Double f = getEffectByVolumeIfType(Types.INERTIA_DAMPENER);
		if (f != null)
			return DoAndReturn.go(new PropulsionForces(), (p) -> p.getDeceleration().setZ(f));
		else
			return null;
	}

	public AuraRanges getEffIntegrityFieldGenerator() {
		return NullSafe.go(() -> {
			Double effect = typeModelByMaterial.getEffect();
			AuraRanges aura = new AuraRanges();
			for (Axis axis : Axis.values()) {
				aura.setXyzByAxis(axis, getAxis(axis).getLength() * effect);
			}
			return aura;
		});
	}

	public PropulsionForces getEffPropulsionForces() { return getEffPropulsionForces((Integer[]) null); }

	public PropulsionForces getEffPropulsionForces(Integer... typeFilter) {
		Integer typeIndex = NullSafe.go(() -> type.getIndex());
		if (typeIndex != null && (typeFilter == null || isTypeOf(typeFilter))) {

			switch (typeIndex) {
			case Types.ENGINE:
				return getEffEngineForces();
			case Types.THRUSTER:
				return getEffThrusterForces();
			case Types.DIRECTIONAL_THRUSTER:
				return getEffDirectionalThrusterForces();
			case Types.INERTIA_DAMPENER:
				return getEffInertiaDampenerForces();
			}
		}
		return null;
	}

	public Double getEffShieldGenerated() { return getEffectByVolumeIfType(Types.SHIELD_GENERATOR); }

	public Double getEffSolarPanelGeneratedE() {
		return NullSafe.go(() -> {
			if (isTypeOf(Types.SOLAR_PANEL)) {
				return getSurfaceArea() * getTypeModelByMaterial().getEffect();
			} else
				return null;
		});
	}

	public PropulsionForces getEffThrusterForces() {
		Double f = getEffectByVolumeIfType(Types.THRUSTER);
		if (f != null) {
			PropulsionForces p = new PropulsionForces();
			p.getAcceleration().setX(f).setY(f);
			p.getDeceleration().setXyz(f);
			return p;
		}
		return null;
	}

	public Double getEffTorpedoStorage() { return getEffectStorageIfType(Types.TORPEDO_STORAGE); }

	public Double getMass() { return NullSafe.go(() -> getDensity() * getVolumeBlock()); };

	public Material getMaterial() { return material; }

	public Double getMaterialCost() {
		return NullSafe.go(() -> getVolumeBlock() * material.getMaterialCost() * typeModel.getMaterialCostMod());
	}

	@Override
	public Integer getMaterialIndex() { return NullSafe.go(() -> material.getIndex()); }

	public Double getProcessingPowerBase() {
		if (NullSafe.go(() -> typeModel.isProcess(), false)) {
			return getVolumeBlock();
		} else
			return 0d;
	}

	public Double getSergeantsReq() {
		return NullSafe.go(() -> {
			Double crew = (getCrewReqMechanics() + getCrewReqEngineers());
			Double ratio = DataMaps.getConstantsMap().get(Cons.CREW_RATIO_CREW_PER_SERGEANT).getValue();
			return crew / ratio;
		});
	}

	public Shape getShape() { return shape; }

	public Type getType() { return type; }

	@Override
	public Integer getTypeIndex() { return NullSafe.go(() -> type.getIndex()); }

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

	public Double getVolumeBlock() { return NullSafe.go(() -> getVolumeCuboid() * shape.getVolumeMod()); }

	public Double getVolumeStat() {
		if (NullSafe.go(() -> typeModel.isHasVolume(), false)) {
			return getVolumeBlock();
		} else
			return 0d;
	}

	public boolean isTypeOf(Integer index) {
		return NullSafe.go(() -> type.getIndex().equals(index), false);
	}

	public boolean isTypeOf(Integer[] indexes) {
		return NullSafe.go(() -> Arrays.stream(indexes).anyMatch(type.getIndex()::equals), false);
	}

	protected void refreshShape() {
		shape = DataMaps.getShape(type.getShapeIndex());
	}

	protected void refreshTypeModel() {
		typeModel = DataMaps.getTypeModel(type.getTypeModelIndex());
	}

	protected void refreshTypeModelByMaterial() {
		TypeModelByMaterial.MapIndex idx = NullSafe.go(() -> new MapIndex(typeModel.getIndex(), material.getIndex()));
		typeModelByMaterial = DataMaps.getTypeModelByMaterial(idx);
	}

	@Override
	public T setMaterial(Integer materialIndex) {
		Material material = null;
		if (materialIndex != null) {
			AvValidations.keyInMap(materialIndex, DataMaps.getMaterialsMap());
			material = DataMaps.getMaterial(materialIndex);
		}
		setMaterial(material);
		return returnThis();
	}

	public T setMaterial(Material material) {
		this.material = material;
		refreshTypeModelByMaterial();
		return returnThis();
	}

	@Override
	public T setType(Integer typeIndex) {
		Type type = null;
		if (typeIndex != null) {
			AvValidations.keyInMap(typeIndex, DataMaps.getTypesMap());
			type = DataMaps.getType(typeIndex);
		}
		setType(type);
		return returnThis();
	}

	public T setType(Type type) {
		this.type = type;
		refreshTypeModel();
		refreshTypeModelByMaterial();
		refreshShape();
		return returnThis();
	}

	@Override
	public String toString() {

		String parentSring = null;
		if (parent != null)
			parentSring = "[id=" + parent.getIndexInStructure() + "]";

		String materialName = NullSafe.go(() -> material.getName());

		// @formatter:off
		return "Block [" + 
				"tags=\"" + tagsAdministrator.getTags() + "\"" + ", "
				+ "index=" + getIndexInStructure() + ", "
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

}
