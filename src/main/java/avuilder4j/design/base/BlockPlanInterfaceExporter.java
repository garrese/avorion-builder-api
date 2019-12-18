package avuilder4j.design.base;

import avuilder4j.error.Avuilder4jException;

public interface BlockPlanInterfaceExporter {

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

}
