package avuilder4j.design.base;

import java.util.Collection;

import avuilder4j.data.DataMaps;
import avuilder4j.util.java.Nullable;
import avuilder4j.util.keys.Cons;

@SuppressWarnings("rawtypes")
public abstract class BlockFunctionalStructureGeneric<B extends BlockFunctionalGeneric, S extends BlockFunctionalStructureGeneric>
		extends BlockPlanStructureGeneric<B, S> {
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
		Double dividend = Nullable.run(() -> getMechanicsReq() + getEngineersReq());
		Double divider = Nullable.run(() -> DataMaps.getConstant(Cons.CREW_RATIO_CREW_PER_SERGEANT).getValue());
		Double result = Nullable.run(() -> dividend / divider);
		return result;

	}

	public double getLieutenantsReq() {
		return Nullable.run(() -> getSargeantsReq()
				/ DataMaps.getConstant(Cons.CREW_RATIO_SERGEANTS_PER_LIEUTENANT).getValue());
	}

	public double getCommandersReq() {
		return Nullable.run(() -> getLieutenantsReq()
				/ DataMaps.getConstant(Cons.CREW_RATIO_LIEUTENANTS_PER_COMMANDER).getValue());
	}

	public double getGeneralsReq() {
		return Nullable.run(() -> getCommandersReq()
				/ DataMaps.getConstant(Cons.CREW_RATIO_COMMANDERS_PER_GENERAL).getValue());
	}

//	protected Double truncateIfFinal(Double v) {
//		return NullSafe.go(() -> retrieveFinalStats ? Math.floor(v) : v);
//	}
//
//	protected Double roundIfFinal(Double v, int zeros) {
//		return retrieveFinalStats ? Utils.round(v, zeros) : v;
//	}

}
