package avuilder4j.design.base;

import avuilder4j.data.loaders.dtos.DataMaps;

@SuppressWarnings("rawtypes")
public abstract class BlockFunctionalIndexerGeneric<B extends BlockFunctionalGeneric, S extends BlockFunctionalStructureGeneric<B>>
		extends BlockPlanIndexerGeneric<B, S> {

	protected DataMaps dataMaps;

	public BlockFunctionalIndexerGeneric(DataMaps dataMaps) {
		this.dataMaps = dataMaps;
	}

	public DataMaps getDataMaps() { return dataMaps; }

	public void setDataMaps(DataMaps dataMaps) { this.dataMaps = dataMaps; }

}
