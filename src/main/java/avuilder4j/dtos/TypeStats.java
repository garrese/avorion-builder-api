package avuilder4j.dtos;

import avuilder4j.dtos.base.Closable;
import avuilder4j.dtos.base.Indexable;

public abstract class TypeStats extends Closable implements Indexable {

	protected Integer index;

	protected Integer shape;

	@Override
	public Integer getIndex() { return index; }

	public Integer getShape() { return shape; }

	public abstract Double getDensityMod();

	public abstract Double getDurabilityMod();

	public abstract Double getCreditCostMod(Integer materialIndex);

	public abstract Double getMaterialCostMod();

	public abstract Double getMechanics();

	public abstract Double getEngineers();

	public abstract Double getProcessingMod();

	public abstract Double getVolumeMod();

	public void setIndex(Integer index) {
		checkClosed();
		this.index = index;
	}

	public void setShape(Integer shape) {
		checkClosed();
		this.shape = shape;
	}

}
