package avuilder4j.design.base;

import avuilder4j.data.DataMaps;
import avuilder4j.util.java.NullSafe;
import avuilder4j.util.values.Metas;

@SuppressWarnings("rawtypes")
public class BlockFunctionalStructureGeneric<B extends BlockFunctionalGeneric> extends BlockPlanStructureGeneric<B> {
	private static final long serialVersionUID = 8757900473268467596L;

	protected boolean isSubstructure = true;

	public boolean isSubstructure() { return isSubstructure; }

	public void setSubstructure(boolean isSubstructure) { this.isSubstructure = isSubstructure; }

	public double getVolumeBlock() { return sumFromBlocks(B::getVolumeBlock); }

	public double getVolumeStat() { return sumFromBlocks(B::getVolumeStat); }

	public double getDurability() { return sumFromBlocks(B::getDurability); }

	public double getDensity() { return sumFromBlocks(B::getDensity); }

	public double getMass() { return sumFromBlocks(B::getMass); }

	public double getMechanicsReq() { return truncateIfNotSub(sumFromBlocks(B::getMechanicsReq)); }

	public double getEngineersReq() { return truncateIfNotSub(sumFromBlocks(B::getEngineersReq)); }

	public double getSargeantsReq() {
		double ref = getMechanicsReq() + getEngineersReq();
		double ratio = NullSafe
				.get(() -> DataMaps.getMetaValueMap().get(Metas.CREW_RATIO_CREW_PER_SERGEANT).getNumericValue(), 1.0);
		return truncateIfNotSub(ref / ratio);

	}

	public double getLieutenantsReq() {
		double ref = getSargeantsReq();
		double ratio = NullSafe.get(() -> DataMaps.getMetaValueMap().get(Metas.CREW_RATIO_SERGEANTS_PER_LIEUTENANT)
				.getNumericValue(), 1.0);
		return truncateIfNotSub(ref / ratio);
	}

	public double getCommandersReq() {
		double ref = getLieutenantsReq();
		double ratio = NullSafe.get(() -> DataMaps.getMetaValueMap().get(Metas.CREW_RATIO_LIEUTENANTS_PER_COMMANDER)
				.getNumericValue(), 1.0);
		return truncateIfNotSub(ref / ratio);
	}

	public double getGeneralsReq() {
		double ref = getCommandersReq();
		double ratio = NullSafe.get(() -> DataMaps.getMetaValueMap().get(Metas.CREW_RATIO_COMMANDERS_PER_GENERAL)
				.getNumericValue(), 1.0);
		return truncateIfNotSub(ref / ratio);
	}

	protected double truncateIfNotSub(double v) {
		return isSubstructure ? v : Math.floor(v);
	}

}
