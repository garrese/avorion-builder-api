package avuilder4j.design.base;

import java.util.Arrays;

import avuilder4j.data.DataMaps;
import avuilder4j.data.dtos.BlockArchetype;
import avuilder4j.design.enums.Axis;
import avuilder4j.design.sub.dimensional.AxisEnds;
import avuilder4j.design.sub.functional.HangarSpace;
import avuilder4j.design.sub.functional.IFGAura;
import avuilder4j.design.sub.functional.PropulsionForces;
import avuilder4j.design.sub.functional.RotationForces;
import avuilder4j.util.java.Nullable;
import avuilder4j.util.java.RunAndReturn;
import avuilder4j.util.keys.Cons;
import avuilder4j.util.keys.Types;
import avuilder4j.util.values.Orients;

@SuppressWarnings("rawtypes")
public abstract class BlockFunctionalGeneric<T extends BlockFunctionalGeneric> extends BlockPlanGeneric<T> {
	private static final long serialVersionUID = -7276801719926846539L;

	private BlockArchetype blockArchetype;

	public BlockArchetype getBlockArchetype() { return blockArchetype; }

	public Double getCreditCost() { return Nullable.run(() -> getBlockArchetype().getCreditCost() * getVolumeBlock()); }

	private Double getCrewReqCommanders() {
		return Nullable.run(() -> getCrewReqLieutenants()
				/ DataMaps.getConstantValue(Cons.CREW_RATIO_LIEUTENANTS_PER_COMMANDER));
	}

	private Double getCrewReq(Integer crewIndex) {
		return null;
	}

	private Double getCrewReqEngineers() {
		return null;// NullSafe.run(() -> getVolumeBlock() * typeModel.getEngineers());
	}

	private Double getCrewReqGenerals() {
		return Nullable.run(() -> getCrewReqCommanders()
				/ DataMaps.getConstantsMap().get(Cons.CREW_RATIO_COMMANDERS_PER_GENERAL).getValue());
	}

	private Double getCrewReqLieutenants() {
		return Nullable.run(() -> getCrewSergeantsReq()
				/ DataMaps.getConstantsMap().get(Cons.CREW_RATIO_SERGEANTS_PER_LIEUTENANT).getValue());
	}

	private Double getCrewReqMechanics() {
		return Nullable.run(() -> getBlockArchetype().getMechanics() * getVolumeBlock());
	};

	private Double getCrewSergeantsReq() {
		return Nullable.run(() -> {
			Double crew = (getCrewReqMechanics() + getCrewReqEngineers());
			Double ratio = DataMaps.getConstantsMap().get(Cons.CREW_RATIO_CREW_PER_SERGEANT).getValue();
			return crew / ratio;
		});
	}

	public Double getDensity() { return Nullable.run(() -> getBlockArchetype().getDensity() * getVolumeBlock()); }

	public Double getDurability() { return Nullable.run(() -> getBlockArchetype().getDurability() * getVolumeBlock()); }

	public Double getEffAcademyCap() { return getEffectByVolumeIfType(Types.ACADEMY); }

	public Double getEffAssemblyCap() { return getEffectByVolumeIfType(Types.ASSEMBLY); }

	public Double getEffCargoHold() { return getEffectStorageIfType(Types.CARGO_BAY); }

	public Double getEffCloningCap() { return getEffectByVolumeIfType(Types.CLONING_PODS); }

	public Double getEffComputerCoreProcessingPower() { return getEffectByVolumeIfType(Types.COMPUTER_CORE); }

	protected Axis getEffDirectionalThrusterAxis() {
		if (isTypeOf(Types.DIRECTIONAL_THRUSTER))
			return Orients.getDirectionalThrusterAxisThrustByOrientation(getOrientation());
		else
			return null;
	}

	public PropulsionForces getEffDirectionalThrusterForces() {
		Double f = getEffectByVolumeIfType(Types.DIRECTIONAL_THRUSTER);
		if (f != null) {
			Axis a = getEffDirectionalThrusterAxis();
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
		return Nullable.run(() -> {
			if (isTypeOf(typeIndexFilter)) {
				return getVolumeBlock() * getBlockArchetype().getEffects().get(1);
			} else {
				return null;
			}
		});
	}

	protected Double getEffectStorageIfType(Integer typeIndexFilter) {
		return Nullable.run(() -> {
			if (isTypeOf(typeIndexFilter)) {
				return getBlockArchetype().getEffects().get(1) * getEffectStorageVolume();
			} else {
				return null;
			}
		});
	}

	protected Double getEffectStorageVolume() {
		return Nullable.run(() -> {
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
			return RunAndReturn.run(new PropulsionForces(), (p) -> p.getAcceleration().setZ(f));
		else
			return null;
	}

	public Double getEffGeneratorGeneratedE() { return getEffectByVolumeIfType(Types.GENERATOR); }

	protected Axis getEffGyroArrayAxis() {
		if (isTypeOf(Types.GYRO_ARRAY))
			return Orients.getGyroArrayAxisOfRotationByOrientation(getOrientation());
		else
			return null;
	}

	public RotationForces getEffGyroArrayForces() {
		Double f = getEffectByVolumeIfType(Types.GYRO_ARRAY);
		if (f != null) {
			Axis a = getEffGyroArrayAxis();
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
			return RunAndReturn.run(new PropulsionForces(), (p) -> p.getDeceleration().setZ(f));
		else
			return null;
	}

	public IFGAura getEffIntegrityFieldGenerator() {
		return Nullable.run(() -> {
			Double effect = getBlockArchetype().getEffects().get(1);
			IFGAura aura = new IFGAura();
			for (Axis axis : Axis.values()) {
				aura.setXyzByAxis(axis, getAxis(axis).getLength() * effect);
			}
			return aura;
		});
	}

	public PropulsionForces getEffPropulsionForces() { return getEffPropulsionForces((Integer[]) null); }

	public PropulsionForces getEffPropulsionForces(Integer... typeFilter) {
		Integer typeIndex = Nullable.run(() -> getBlockArchetype().getTypeIndex());
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
		return Nullable.run(() -> {
			if (isTypeOf(Types.SOLAR_PANEL)) {
				return getSurfaceArea() * getBlockArchetype().getEffects().get(1);
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

	public Double getEffTorpedoStorage() { return getEffectStorageIfType(Types.TORPEDO_STORAGE); };

	public Double getMass() { return Nullable.run(() -> getDensity() * getVolumeBlock()); }

	public Double getMaterialCost() {
		return Nullable.run(() -> getBlockArchetype().getMaterialCost() * getVolumeBlock());
	}

	public Double getProcessingPowerBase() {
		if (Nullable.run(() -> getBlockArchetype().getProcess(), false)) {
			return getVolumeBlock();
		} else
			return 0d;
	}

	public Double getVolumeBlock() {
		return Nullable.run(() -> getVolumeCuboid() * getBlockArchetype().getCuboidFilledIn());
	}

	public Double getVolumeStat() {
		if (Nullable.run(() -> getBlockArchetype().getHasVolume(), false)) {
			return getVolumeBlock();
		} else
			return 0d;
	}

	public boolean isTypeOf(Integer index) {
		return Nullable.run(() -> getBlockArchetype().getTypeIndex().equals(index), false);
	}

	public boolean isTypeOf(Integer[] indexes) {
		return Nullable.run(() -> Arrays.stream(indexes).anyMatch(getBlockArchetype().getTypeIndex()::equals), false);
	}

	public T setBlockArchetype(BlockArchetype blockArchetype) {
		this.blockArchetype = blockArchetype;
		return chain();
	}

	public T setBlockArchetype(Integer typeIndex, Integer materialIndex) {
		super.setTypeIndex(typeIndex);
		super.setMaterialIndex(materialIndex);
		BlockArchetype a = DataMaps.getBlockArchetype(typeIndex, materialIndex);
		return setBlockArchetype(a);
	}

	@Override
	public T setMaterialIndex(Integer materialIndex) {
		return setBlockArchetype(getTypeIndex(), materialIndex);
	}

	@Override
	public T setTypeIndex(Integer typeIndex) {
		return setBlockArchetype(typeIndex, super.getMaterialIndex());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Block [");
		builder.append(toStringHeader());
		builder.append(", ");
		builder.append(toStringBodyPlanIndexes());
		builder.append(", ");
		builder.append(toStringBodyFunctionalNames());
		builder.append(", ");
		builder.append(toStringBodyPlanValues());
		builder.append(", ");
		builder.append(toStringBodyCuboid());
		builder.append(", ");
		builder.append(toStringBodyFunctionalValues());
		builder.append("]");
		return builder.toString();
	}

	protected String toStringBodyFunctionalNames() {
		StringBuilder builder = new StringBuilder();
		builder.append("block=");
		builder.append(Nullable.run(() -> getBlockArchetype().getTypeName()));
		builder.append(", material=");
		builder.append(Nullable.run(() -> blockArchetype.getMaterialName()));
		return builder.toString();
	}

	protected String toStringBodyFunctionalValues() {
		StringBuilder builder = new StringBuilder();
		builder.append("mass=");
		builder.append(getMass());
		builder.append(", volBlock=");
		builder.append(getVolumeBlock());
		builder.append(", MCost=");
		builder.append(getMaterialCost());
		builder.append(", CCost=");
		builder.append(getCreditCost());
		builder.append(", dur=");
		builder.append(getDurability());
		builder.append(", PPB=");
		builder.append(getProcessingPowerBase());
		builder.append(", CR=");
		builder.append(Nullable.run(() -> getBlockArchetype().getCollisionReduction()));
		return builder.toString();
	}

}
