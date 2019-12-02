package avuilder4j.design.base;

import avuilder4j.data.DataMaps;
import avuilder4j.util.java.NullSafe;
import avuilder4j.util.values.Metas;

@SuppressWarnings("rawtypes")
public class BlockFunctionalStructureGeneric<B extends BlockFunctionalGeneric> extends BlockPlanStructureGeneric<B> {
	private static final long serialVersionUID = 8757900473268467596L;

	protected boolean retrieveFinalStructureData = false;

	public boolean isRetrieveFinalStructureData() {
		return retrieveFinalStructureData;
	}

	public void setRetrieveFinalStructureData(boolean retrieveFinalStructureData) {
		this.retrieveFinalStructureData = retrieveFinalStructureData;
	}

	public double getVolumeBlock() {
		return sumFromBlocks(B::getVolumeBlock);
	}

	public double getVolumeStat() {
		return sumFromBlocks(B::getVolumeStat);
	}

	public double getDurability() {
		return sumFromBlocks(B::getDurability);
	}

	public double getDensity() {
		return sumFromBlocks(B::getDensity);
	}

	public double getMass() {
		return sumFromBlocks(B::getMass);
	}

	public double getMechanicsReq() {
		return truncateIfFinal(sumFromBlocks(B::getMechanicsReq));
	}

	public double getEngineersReq() {
		return truncateIfFinal(sumFromBlocks(B::getEngineersReq));
	}

	public double getSargeantsReq() {
		Double dividend = NullSafe.get(() -> getMechanicsReq() + getEngineersReq());
		Double divider = NullSafe
				.get(() -> DataMaps.getMetaValue(Metas.CREW_RATIO_CREW_PER_SERGEANT).getNumericValue());
		Double result = NullSafe.get(() -> dividend / divider);
		return result;

	}

	public double getLieutenantsReq() {
		return NullSafe.get(() -> getSargeantsReq()
				/ DataMaps.getMetaValue(Metas.CREW_RATIO_SERGEANTS_PER_LIEUTENANT).getNumericValue());
	}

	public double getCommandersReq() {
		return NullSafe.get(() -> getLieutenantsReq()
				/ DataMaps.getMetaValue(Metas.CREW_RATIO_LIEUTENANTS_PER_COMMANDER).getNumericValue());
	}

	public double getGeneralsReq() {
		return NullSafe.get(() -> getCommandersReq()
				/ DataMaps.getMetaValue(Metas.CREW_RATIO_COMMANDERS_PER_GENERAL).getNumericValue());
	}

	protected double truncateIfFinal(double v) {
		return retrieveFinalStructureData ? Math.floor(v) : v;
	}

}
