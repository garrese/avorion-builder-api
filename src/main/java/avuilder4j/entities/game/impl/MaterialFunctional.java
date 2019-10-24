package avuilder4j.entities.game.impl;

import avuilder4j.entities.game.base.MaterialBase;

/**
 * Immutable Avorion material reference.
 */
public class MaterialFunctional extends MaterialBase {

	/**
	 * Material's density in t/m^3.
	 */
	protected double density;
	/**
	 * Material's durability in hp/m^3.
	 */
	protected double durability;
	/**
	 * Material's credit cost in credits/m^3.
	 */
	protected double creditCost;
	/**
	 * Material's material cost in ore/m^3.
	 */
	protected double materialCost;

	public MaterialFunctional(int index, String name) {
		super(index, name);
	}

	public MaterialFunctional(int index, String name, double density, double durability, double creditCost, double materialCost) {
		super(index, name);
		this.density = density;
		this.durability = durability;
		this.creditCost = creditCost;
		this.materialCost = materialCost;
	}

}
