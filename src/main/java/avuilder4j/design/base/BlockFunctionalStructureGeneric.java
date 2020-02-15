package avuilder4j.design.base;

import java.util.Collection;

import avuilder4j.data.DataMaps;
import avuilder4j.design.enums.Axis;
import avuilder4j.design.sub.dimensional.Point;
import avuilder4j.design.sub.dimensional.Vector;
import avuilder4j.design.sub.functional.Crew;
import avuilder4j.design.sub.functional.HangarSpace;
import avuilder4j.design.sub.functional.LinearAccelerations;
import avuilder4j.design.sub.functional.LinearForces;
import avuilder4j.design.sub.functional.Materials;
import avuilder4j.util.java.Nullable;
import avuilder4j.util.keys.Cons;

@SuppressWarnings("rawtypes")
public abstract class BlockFunctionalStructureGeneric<B extends BlockFunctionalGeneric, S extends BlockFunctionalStructureGeneric> extends BlockPlanStructureGeneric<B, S> {
	private static final long serialVersionUID = 8757900473268467596L;

//	public double getVolumeBlock() { return sumFromBlocks(B::getVolumeBlock); }
//
//	public double getVolumeStat() { return sumFromBlocks(B::getVolumeStat); }
//
//	public double getDurability() { return sumFromBlocks(B::getDurability); }
//
//	public double getDensity() { return sumFromBlocks(B::getDensity); }
//
//	public double getMass() { return sumFromBlocks(B::getMass); }

	public BlockFunctionalStructureGeneric() {
		super();
	}

	public BlockFunctionalStructureGeneric(Collection<? extends B> c) {
		super(c);
	}

	public BlockFunctionalStructureGeneric(int initialCapacity) {
		super(initialCapacity);
	}

	private Double getMechanicsReq() {
		return null;// sumFromBlocks(B::getCrewReqMechanics);
	}

	private Double getEngineersReq() {
		return null;// sumFromBlocks(B::getCrewReqEngineers);
	}

	public double getSargeantsReq() {
		Double dividend = Nullable.m(() -> getMechanicsReq() + getEngineersReq());
		Double divider = Nullable.m(() -> DataMaps.getConstant(Cons.CREW_RATIO_CREW_PER_SERGEANT).getValue());
		Double result = Nullable.m(() -> dividend / divider);
		return result;

	}

	public Materials getMaterialCost() {
		Materials am = new Materials();
		for (B b : this) {
			am.add(b.getMaterialIndex(), b.getMaterialCost());
		}
		return am;
	}

	// P = T ω
	// τ = Iα -> torque = (moment of inertia)(angular acceleration)
	public Point getMassCenter() {
		Point centerOfMass = new Point();

		double totalMass = 0;
		Vector moments = new Vector(0);

		for (B b : this) {
			double blockMass = b.getMass();
			Point blockCenter = b.getCenter();

			totalMass += blockMass;

			for (Axis axis : Axis.values()) {
				double moment = moments.getV3Axis(axis);
				moment += blockMass * blockCenter.getV3Axis(axis);
				moments.setV3Axis(axis, moment);
			}
		}

		for (Axis axis : Axis.values()) {
			double cmComponent = moments.getV3Axis(axis) / totalMass;
			centerOfMass.setV3Axis(axis, cmComponent);
		}

		return centerOfMass;
	}

	public Vector getMomentOfInertia() {
		Vector totalMoi = new Vector();

		Point massCenter = getMassCenter();
		for (B block : this) {
			totalMoi = totalMoi.sumV3(block.getMomentOfInertia(massCenter));
		}

		return totalMoi;
	}

	public Crew getCrew() {
		Crew crew = new Crew();
		for (B block : this) {
			crew.add(block.getCrewReq());
		}
		return crew;
	}

	public Double getCreditCost() { return sumFromBlocks(B::getCreditCost); }

	public Double getMass() { return sumFromBlocks(B::getMass); }

	public Double getDurability() { return sumFromBlocks(B::getDurability); }

	public Double getShieldGenerated() { return sumFromBlocks(B::getEffShieldGenerated); }

	public HangarSpace getEffHangarSpace() {
		Double value = sumFromBlocks(b -> Nullable.m(() -> b.getEffHangarSpace().getValue()));
		if (value != null)
			return new HangarSpace(value);
		else
			return null;
	}

	public LinearForces getEffLinearForces() { return getEffLinearForces((Integer[]) null); }

	public LinearForces getEffLinearForces(Integer... typeFilter) {
		LinearForces total = new LinearForces();
		for (B block : this) {
			if (block != null) {
				total.sumLinear(block.getEffLinearForces(typeFilter));
			}
		}
		return total;
	}

	public Vector calcEffTorque() {
		Point massCenter = getMassCenter();
		Vector totalTorque = new Vector();
		for (B block : this) {
			totalTorque.sumV3(block.getEffThrusterTorque(massCenter));
		}
		return totalTorque;
	}

	public LinearAccelerations getEffLinearAccelerations() { return getEffLinearAccelerations((Integer[]) null); }

	public LinearAccelerations getEffLinearAccelerations(Integer... typeFilter) {
		return new LinearAccelerations(getEffLinearForces(typeFilter), getMass());
	}

	public double getLieutenantsReq() { return Nullable.m(() -> getSargeantsReq() / DataMaps.getConstant(Cons.CREW_RATIO_SERGEANTS_PER_LIEUTENANT).getValue()); }

	public double getCommandersReq() { return Nullable.m(() -> getLieutenantsReq() / DataMaps.getConstant(Cons.CREW_RATIO_LIEUTENANTS_PER_COMMANDER).getValue()); }

	public double getGeneralsReq() { return Nullable.m(() -> getCommandersReq() / DataMaps.getConstant(Cons.CREW_RATIO_COMMANDERS_PER_GENERAL).getValue()); }

}
