package avuilder4j.dtos;

import avuilder4j.dtos.base.Indexable;

public class Shape implements Indexable {

	protected Integer index;

	protected Double volumeMod;

	/**
	 * @param index
	 * @param volumeMod
	 */
	public Shape(Integer index, Double volumeMod) {
		this.index = index;
		this.volumeMod = volumeMod;
	}

	/**
	 * Gets the {@link #index}.
	 * 
	 * @return the {@link #index}.
	 */
	@Override
	public Integer getIndex() { return index; }

	/**
	 * Gets the {@link #volumeMod}.
	 * 
	 * @return the {@link #volumeMod}.
	 */
	public Double getVolumeMod() { return volumeMod; }

	@Override
	public String toString() {
		return "Shape [index=" + index + ", volumeMod=" + volumeMod + "]";
	}

}
