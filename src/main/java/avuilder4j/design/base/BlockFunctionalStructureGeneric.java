package avuilder4j.design.base;

import avuilder4j.util.OperationOfNullables;

@SuppressWarnings("rawtypes")
public class BlockFunctionalStructureGeneric<B extends BlockFunctionalGeneric> extends BlockPlanStructureGeneric<B> {
	private static final long serialVersionUID = 8757900473268467596L;

	protected boolean isSubstructure = true;

	public boolean isSubstructure() { return isSubstructure; }

	public void setSubstructure(boolean isSubstructure) { this.isSubstructure = isSubstructure; }

	public OperationOfNullables<Double> getVolumeBlock() {
		return operation(OperationOfNullables.sumDoubles, (b) -> b.getMass(), "totalMass");
	}

	public OperationOfNullables<Double> getVolumeStat() {
		return operation(OperationOfNullables.sumDoubles, (b) -> b.getMass(), "totalMass");
	}

	public OperationOfNullables<Double> getDensity() {
		return operation(OperationOfNullables.sumDoubles, (b) -> b.getMass(), "totalMass");
	}

	public OperationOfNullables<Double> getMass() {
		return operation(OperationOfNullables.sumDoubles, (b) -> b.getMass(), "totalMass");
	}

}
