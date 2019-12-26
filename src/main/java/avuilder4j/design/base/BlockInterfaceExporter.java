package avuilder4j.design.base;

import java.util.ArrayList;
import java.util.List;

import avuilder4j.error.Avuilder4jException;

public interface BlockInterfaceExporter {

	public String getColor();

	public Integer getIndex();

	public Integer getLook();

	public Integer getMaterialIndex();

	public Integer getParentIndex();

	public Integer getTypeIndex();

	public Integer getUp();

	public Double getXL();

	public Double getXU();

	public Double getYL();

	public Double getYU();

	public Double getZL();

	public Double getZU();

	public boolean isBlockPlanDefined();

	public void validateBlockPlan() throws Avuilder4jException;

	public static List<BlockInterfaceExporter> findRoots(List<? extends BlockInterfaceExporter> blocks) {
		ArrayList<BlockInterfaceExporter> roots = new ArrayList<BlockInterfaceExporter>();
		for (BlockInterfaceExporter block : blocks) {
			if (block.getParentIndex() == null || block.getParentIndex().equals(-1)) {
				roots.add(block);
			}
		}
		return roots;
	}

}
