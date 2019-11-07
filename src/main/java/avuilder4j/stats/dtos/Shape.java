package avuilder4j.stats.dtos;

import avuilder4j.structural.dtos.Indexed;

public class Shape extends Indexed {

	private double volumeMod;

	public Shape(int index, double volumeMod) {
		super(index);
		this.volumeMod = volumeMod;
	}

	/**
	 * Gets the {@link #volumeMod}.
	 * 
	 * @return the {@link #volumeMod}.
	 */
	public double getVolumeMod() { return volumeMod; }

}
