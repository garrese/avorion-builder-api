package avuilder4j.design.base;

import avuilder4j.error.Avuilder4jException;

public interface BlockPlanInterface {

	public Integer getIndex();

	public Integer getParentIndex();

	public Double getLX();

	public Double getLY();

	public Double getLZ();

	public Double getUX();

	public Double getUY();

	public Double getUZ();

	public Integer getMaterialIndex();

	public Integer getTypeIndex();

	public Integer getLook();

	public Integer getUp();

	public String getColor();

	public void validateBlockPlan() throws Avuilder4jException;

	public boolean isBlockPlanDefined();

}
