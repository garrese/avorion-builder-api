package avuilder4j.design.base;

import avuilder4j.data.DataMaps;
import avuilder4j.util.java.NullSafe;
import avuilder4j.util.java.Utils;
import avuilder4j.util.keys.Metas;

@SuppressWarnings("rawtypes")
public class BlockFunctionalStructureGeneric<B extends BlockFunctionalGeneric> extends BlockPlanStructureGeneric<B> {
	private static final long serialVersionUID = 8757900473268467596L;

	protected boolean retrieveFinalStats = false;

	public boolean isRetrieveFinalStats() { return retrieveFinalStats; }

	public void setRetrieveFinalStats(boolean retrieveFinalStructureData) {
		this.retrieveFinalStats = retrieveFinalStructureData;
	}

	public double getVolumeBlock() { return sumFromBlocks(B::getVolumeBlock); }

	public double getVolumeStat() { return sumFromBlocks(B::getVolumeStat); }

	public double getDurability() { return sumFromBlocks(B::getDurability); }

	public double getDensity() { return sumFromBlocks(B::getDensity); }

	public double getMass() { return sumFromBlocks(B::getMass); }

	public Double getMechanicsReq() { return truncateIfFinal(sumFromBlocks(B::getCrewReqMechanics)); }

	public Double getEngineersReq() { return truncateIfFinal(sumFromBlocks(B::getCrewReqEngineers)); }

	public double getSargeantsReq() {
		Double dividend = NullSafe.get(() -> getMechanicsReq() + getEngineersReq());
		Double divider = NullSafe
				.get(() -> DataMaps.getMetaValue(Metas.CREW_RATIO_CREW_PER_SERGEANT).getNumber());
		Double result = NullSafe.get(() -> dividend / divider);
		return result;

	}

	public double getLieutenantsReq() {
		return NullSafe.get(() -> getSargeantsReq()
				/ DataMaps.getMetaValue(Metas.CREW_RATIO_SERGEANTS_PER_LIEUTENANT).getNumber());
	}

	public double getCommandersReq() {
		return NullSafe.get(() -> getLieutenantsReq()
				/ DataMaps.getMetaValue(Metas.CREW_RATIO_LIEUTENANTS_PER_COMMANDER).getNumber());
	}

	public double getGeneralsReq() {
		return NullSafe.get(() -> getCommandersReq()
				/ DataMaps.getMetaValue(Metas.CREW_RATIO_COMMANDERS_PER_GENERAL).getNumber());
	}

	protected Double truncateIfFinal(Double v) {
		return NullSafe.get(() -> retrieveFinalStats ? Math.floor(v) : v);
	}

	protected Double roundIfFinal(Double v, int zeros) {
		return retrieveFinalStats ? Utils.round(v, zeros) : v;
	}

}
