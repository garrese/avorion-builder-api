package avuilder4j.design.base;

import java.util.Collection;

import avuilder4j.design.enums.Axis;
import avuilder4j.design.sub.dimensional.Point;
import avuilder4j.design.sub.dimensional.Vector;
import avuilder4j.design.sub.functional.Crew;
import avuilder4j.design.sub.functional.HangarSpace;
import avuilder4j.design.sub.functional.LinearAccelerations;
import avuilder4j.design.sub.functional.LinearForces;
import avuilder4j.design.sub.functional.Materials;
import avuilder4j.util.java.Nullable;

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

	public Materials calcMaterialCost() {
		Materials am = new Materials();
		for (B b : this) {
			am.add(b.getMaterialIndex(), b.getMaterialCost());
		}
		return am;
	}

	// P = T ω
	// τ = Iα -> torque = (moment of inertia)(angular acceleration)
	public Point calcMassCenter() {
		Point centerOfMass = new Point();

		double totalMass = 0;
		Vector moments = new Vector(0);

		for (B b : this) {
			double blockMass = b.getMass();
			Point blockCenter = b.calcCenter();

			totalMass += blockMass;

			for (Axis axis : Axis.values()) {
				double moment = moments.getXyzAxis(axis);
				moment += blockMass * blockCenter.getXyzAxis(axis);
				moments.setXyzAxis(axis, moment);
			}
		}

		for (Axis axis : Axis.values()) {
			double cmComponent = moments.getXyzAxis(axis) / totalMass;
			centerOfMass.setXyzAxis(axis, cmComponent);
		}

		return centerOfMass;
	}

	public Vector calcMomentOfInertia() {
		Vector totalMoi = new Vector();

		Point massCenter = calcMassCenter();
		for (B block : this) {
			totalMoi = totalMoi.sumXyz(block.getMomentOfInertia(massCenter));
		}

		return totalMoi;
	}

	public Crew calcCrew() {
		Crew crew = new Crew();
		for (B block : this) {
			crew.add(block.getCrewReq());
		}
		return crew;
	}

	public Double calcCreditCost() {
		return sumFromBlocks(B::getCreditCost);
	}

	public Double calcMass() {
		return sumFromBlocks(B::getMass);
	}

	public Double calcDurability() {
		return sumFromBlocks(B::getDurability);
	}

	public Double calcEffShieldGenerated() {
		return sumFromBlocks(B::getEffShieldGenerated);
	}

	public HangarSpace calcEffHangarSpace() {
		Double value = sumFromBlocks(b -> Nullable.m(() -> b.getEffHangarSpace().getValue()));
		if (value != null)
			return new HangarSpace(value);
		else
			return null;
	}

	public LinearForces calcEffLinearForces() {
		return calcEffLinearForces((Integer[]) null);
	}

	public LinearForces calcEffLinearForces(Integer... typeFilter) {
		LinearForces total = new LinearForces();
		for (B block : this) {
			if (block != null) {
				total.sumLinear(block.getEffLinearForces(typeFilter));
			}
		}
		return total;
	}

	public Vector calcEffTorque() {
		Point massCenter = calcMassCenter();
		Vector totalTorque = new Vector();
		for (B block : this) {
			totalTorque.sumXyz(block.getEffThrusterTorque(massCenter));
		}
		return totalTorque;
	}

	public LinearAccelerations calcEffLinearAccelerations() {
		return calcEffLinearAccelerations((Integer[]) null);
	}

	public LinearAccelerations calcEffLinearAccelerations(Integer... typeFilter) {
		return new LinearAccelerations(calcEffLinearForces(typeFilter), calcMass());
	}

}
