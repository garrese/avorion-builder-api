package avuilder4j.design.base;

import avuilder4j.data.DataMap;
import avuilder4j.data.Material;
import avuilder4j.data.Shape;
import avuilder4j.data.Type;
import avuilder4j.data.TypeModel;
import avuilder4j.data.TypeModelByMaterial;

public class Block implements BlockPlanInterface {

	protected DataMap dataMap;

	protected Material material;
	protected Type type;
	protected Shape shape;
	protected TypeModel typeModel;
	protected TypeModelByMaterial typeModelByMaterial;

	public Block(DataMap generalDataMap) {
		this.dataMap = generalDataMap;
	}

	public void setMaterial(Integer materialIndex) { material = dataMap.getMaterialMap().get(materialIndex); }

	protected DataMap getDataMap() { return dataMap; }

	protected void setDataMap(DataMap dataMap) { this.dataMap = dataMap; }

	@Override
	public Integer getIndex() { return null; }

	@Override
	public Integer getParentIndex() { return null; }

	@Override
	public Double getLX() { return null; }

	@Override
	public Double getLY() { return null; }

	@Override
	public Double getLZ() { return null; }

	@Override
	public Double getUX() { return null; }

	@Override
	public Double getUY() { return null; }

	@Override
	public Double getUZ() { return null; }

	@Override
	public Integer getMaterialIndex() { return null; }

	@Override
	public Integer getTypeIndex() { return null; }

	@Override
	public Integer getLook() { return null; }

	@Override
	public Integer getUp() { return null; }

	@Override
	public String getColor() { return null; }

}
