package avuilder4j.design.base;

import java.util.Arrays;

import avuilder4j.data.DataMaps;
import avuilder4j.data.dtos.BlockArchetype;
import avuilder4j.design.enums.Axis;
import avuilder4j.design.sub.dimensional.AxisEnds;
import avuilder4j.design.sub.functional.Crew;
import avuilder4j.design.sub.functional.HangarSpace;
import avuilder4j.design.sub.functional.IFGAura;
import avuilder4j.design.sub.functional.LinearForces;
import avuilder4j.design.sub.functional.RotationForces;
import avuilder4j.util.java.Chain;
import avuilder4j.util.java.Nullable;
import avuilder4j.util.keys.Cons;
import avuilder4j.util.keys.Crews;
import avuilder4j.util.keys.Types;
import avuilder4j.util.values.Orients;

public abstract class BlockFunctionalGeneric<T extends BlockFunctionalGeneric<T>> extends BlockPlanGeneric<T> {
	private static final long serialVersionUID = -7276801719926846539L;

	private BlockArchetype blockArchetype;

	public BlockArchetype getBlockArchetype() { return blockArchetype; }

	public Double getCreditCost() { return Nullable.m(() -> Math.ceil(getBlockArchetype().getCreditCost() * getVolumeBlock())); }

	public Crew getCrewReq() {
		Crew ac = new Crew();
		ac.add(Crews.MECHANICS, getCrewReqMechanics());
		ac.add(Crews.ENGINEERS, getCrewReqEngineers());
		return ac;
	}

	public Double getCrewReqEngineers() { return Nullable.m(() -> getBlockArchetype().getEngineers() * getVolumeBlock()); }

	public Double getCrewReqMechanics() { return Nullable.m(() -> getBlockArchetype().getMechanics() * getVolumeBlock()); };

	public Double getDensity() { return Nullable.m(() -> getBlockArchetype().getDensity() * getVolumeBlock()); }

	public Double getDurability() { return Nullable.m(() -> getBlockArchetype().getDurability() * getVolumeBlock()); }

	public Double getEffAcademyCap() { return getEffectByVolumeIfType(Types.ACADEMY); }

	public Double getEffAssemblyCap() { return getEffectByVolumeIfType(Types.ASSEMBLY); }

	public Double getEffCargoHold() { return getEffectStorageIfType(Types.CARGO_BAY); }

	public Double getEffCloningCap() { return getEffectByVolumeIfType(Types.CLONING_PODS); }

	public Double getEffComputerCoreProcessingPower() { return getEffectByVolumeIfType(Types.COMPUTER_CORE); }

	public Axis getEffDirectionalThrusterAxis() {
		if (isTypeOf(Types.DIRECTIONAL_THRUSTER))
			return Orients.getDirectionalThrusterAxisThrustByOrientation(getOrientation());
		else
			return null;
	}

	public LinearForces getEffDirectionalThrusterForces() {
		Double f = getEffectByVolumeIfType(Types.DIRECTIONAL_THRUSTER);
		if (f != null) {
			Axis a = getEffDirectionalThrusterAxis();
			LinearForces pfs = new LinearForces();
			pfs.getSpeedingUp().setXyzByAxis(a, f);
			pfs.getBraking().setXyzByAxis(a, f);
			if (a.equals(Axis.Z)) {
				pfs.getSpeedingUp().setZ(0d);
			}
			return pfs;
		} else
			return null;
	}

	protected Double getEffectByVolumeIfType(Integer typeIndexFilter) {
		return Nullable.m(() -> {
			if (isTypeOf(typeIndexFilter)) {
				return getVolumeBlock() * getBlockArchetype().getEffects().get(0);
			} else {
				return null;
			}
		});
	}

	protected Double getEffectStorageIfType(Integer typeIndexFilter) {
		return Nullable.m(() -> {
			if (isTypeOf(typeIndexFilter)) {
				return getBlockArchetype().getEffects().get(0) * getEffectStorageVolume();
			} else {
				return null;
			}
		});
	}

	public Double getEffComputerCoreProcessing() {
		Double e = getEffectByVolumeIfType(Types.DIRECTIONAL_THRUSTER);
		Double p = getProcessingPowerBase();
		return Nullable.m(() -> p * e);
	}

	protected Double getEffectStorageVolume() {
		return Nullable.m(() -> {
			Double vol = 1d;
			for (AxisEnds ends : getAllAxes()) {
				vol *= ends.getLength() - DataMaps.getConstantValue(Cons.CONTAINERS_WALL_THICKNESS);
			}
			return vol;
		});
	}

	public Double getEffEnergyContainerECap() { return getEffectByVolumeIfType(Types.ENERGY_CONTAINER); }

	public LinearForces getEffEngineForces() {
		Double f = getEffectByVolumeIfType(Types.ENGINE);
		if (f != null)
			return Chain.m(new LinearForces(), (p) -> p.getSpeedingUp().setZ(f));
		else
			return null;
	}

	public Double getEffGeneratorGeneratedEnergy() { return getEffectByVolumeIfType(Types.GENERATOR); }

	public Axis getEffGyroArrayAxis() {
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

	public LinearForces getEffInertiaDampenerForces() {
		Double f = getEffectByVolumeIfType(Types.INERTIA_DAMPENER);
		if (f != null)
			return Chain.m(new LinearForces(), (p) -> p.getBraking().setZ(f));
		else
			return null;
	}

	public IFGAura getEffIntegrityFieldGenerator() {
		return Nullable.m(() -> {
			Double effect = getBlockArchetype().getEffects().get(0);
			IFGAura aura = new IFGAura();
			for (Axis axis : Axis.values()) {
				aura.setXyzByAxis(axis, getAxis(axis).getLength() * effect);
			}
			return aura;
		});
	}

	public LinearForces getEffLinearForces() { return getEffPropulsionForces((Integer[]) null); }

	public LinearForces getEffPropulsionForces(Integer... typeFilter) {
		Integer typeIndex = Nullable.m(() -> getBlockArchetype().getTypeIndex());
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
		return Nullable.m(() -> {
			if (isTypeOf(Types.SOLAR_PANEL)) {
				return getSurfaceArea() * getBlockArchetype().getEffects().get(0);
			} else
				return null;
		});
	}

	public LinearForces getEffThrusterForces() {
		Double f = getEffectByVolumeIfType(Types.THRUSTER);
		if (f != null) {
			LinearForces p = new LinearForces();
			p.getSpeedingUp().setX(f).setY(f);
			p.getBraking().setXyz(f);
			return p;
		}
		return null;
	}

	public Double getEffTorpedoStorage() { return getEffectStorageIfType(Types.TORPEDO_STORAGE); };

	public Double getMass() { return Nullable.m(() -> getDensity() * getVolumeBlock()); }

	public Double getMaterialCost() { return Nullable.m(() -> Math.ceil(getBlockArchetype().getMaterialCost() * getVolumeBlock())); }

	public Double getProcessingPowerBase() {
		if (Nullable.m(() -> getBlockArchetype().getProcess(), false)) {
			return getVolumeBlock();
		} else
			return 0d;
	}

	public Double getVolumeBlock() { return Nullable.m(() -> getVolumeCuboid() * getBlockArchetype().getCuboidFilledIn()); }

	public Double getVolumeStat() {
		if (Nullable.m(() -> getBlockArchetype().getHasVolume(), false)) {
			return getVolumeBlock();
		} else
			return 0d;
	}

	public boolean isTypeOf(Integer typeIndex) {
		return Nullable.m(() -> getBlockArchetype().getTypeIndex().equals(typeIndex), false);
	}

	public boolean isTypeOf(Integer[] indexes) {
		return Nullable.m(() -> Arrays.stream(indexes).anyMatch(getBlockArchetype().getTypeIndex()::equals), false);
	}

	public T setBlockArchetype(BlockArchetype blockArchetype) {
		this.blockArchetype = blockArchetype;
		if (blockArchetype != null) {
			super.setMaterialIndex(getBlockArchetype().getMaterialIndex());
			super.setTypeIndex(getBlockArchetype().getTypeIndex());
		}
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

	public String toStringBodyFunctionalNames() {
		StringBuilder builder = new StringBuilder();
		builder.append("block=");
		builder.append(Nullable.m(() -> getBlockArchetype().getTypeName()));
		builder.append(", material=");
		builder.append(Nullable.m(() -> blockArchetype.getMaterialName()));
		return builder.toString();
	}

	public String toStringBodyFunctionalValues() {
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
		builder.append(Nullable.m(() -> getBlockArchetype().getCollisionReduction()));
		return builder.toString();
	}

}
