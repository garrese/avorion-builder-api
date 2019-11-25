package avuilder4j.design.base;

import avuilder4j.design.sub.DataReport;

@SuppressWarnings("rawtypes")
public class BlockFunctionalStructureGeneric<T extends BlockFunctionalGeneric> extends BlockPlanStructureGeneric<T> {
	private static final long serialVersionUID = 8757900473268467596L;

	public DataReport getVolumeBlock() {
		DataReport r = new DataReport(getTagsAdministrator().getTags() + " totalVolumeBlock");
		for (BlockFunctionalGeneric b : this) {
			r.addToResult(b.getVolumeBlock());
		}
		return r;
	}

	public DataReport getVolumeStat() {
		DataReport r = new DataReport(getTagsAdministrator().getTags() + " totalVolumeStat");
		for (BlockFunctionalGeneric b : this) {
			r.addToResult(b.getVolumeStat());
		}
		return r;
	}

	public DataReport getDensity() {
		DataReport r = new DataReport(getTagsAdministrator().getTags() + " totalDensity");
		for (BlockFunctionalGeneric b : this) {
			r.addToResult(b.getDensity());
		}
		return r;
	}

	public DataReport getMass() {
		DataReport r = new DataReport(getTagsAdministrator().getTags() + " totalMass");
		for (BlockFunctionalGeneric b : this) {
			r.addToResult(b.getMass());
		}
		return r;
	}

}
