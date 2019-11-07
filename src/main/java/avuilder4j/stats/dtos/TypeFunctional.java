package avuilder4j.stats.dtos;

import avuilder4j.structural.dtos.TypeStructural;

public class TypeFunctional extends TypeStructural {

	/**
	 * @param index
	 * @param shape
	 * @param densityMod
	 * @param durabilityMod
	 * @param creditCostMod
	 * @param materialCostMod
	 * @param mechanics
	 * @param engineers
	 */
	public TypeFunctional(int index, Shape shape, Double densityMod, Double durabilityMod, Double creditCostMod,
			Double materialCostMod, Double mechanics, Double engineers) {
		super(index);
		this.shape = shape;
		this.densityMod = densityMod;
		this.durabilityMod = durabilityMod;
		this.creditCostMod = creditCostMod;
		this.materialCostMod = materialCostMod;
		this.mechanics = mechanics;
		this.engineers = engineers;
	}

	/**
	 * Block's shape modification to volume
	 */
	protected Shape shape;

	/**
	 * Material density modificator of the block type.
	 */
	protected Double densityMod;

	/**
	 * Material durability modificator of the block type.
	 */
	protected Double durabilityMod;

	/**
	 * Material credit cost modificator of the block type.
	 */
	protected Double creditCostMod;

	/**
	 * Material cost modificator of the block type.
	 */
	protected Double materialCostMod;

	/**
	 * Mechanics cost in crew/m^3.
	 */
	protected Double mechanics;

	/**
	 * Engineers cost in crew/m^3.
	 */
	protected Double engineers;

	/**
	 * Gets the {@link #shape}.
	 * 
	 * @return the {@link #shape}.
	 */
	public Shape getShape() { return shape; }

	/**
	 * Gets the {@link #densityMod}.
	 * 
	 * @return the {@link #densityMod}.
	 */
	public Double getDensityMod() { return densityMod; }

	/**
	 * Gets the {@link #durabilityMod}.
	 * 
	 * @return the {@link #durabilityMod}.
	 */
	public Double getDurabilityMod() { return durabilityMod; }

	/**
	 * Gets the {@link #creditCostMod}.
	 * 
	 * @return the {@link #creditCostMod}.
	 */
	public Double getCreditCostMod() { return creditCostMod; }

	/**
	 * Gets the {@link #materialCostMod}.
	 * 
	 * @return the {@link #materialCostMod}.
	 */
	public Double getMaterialCostMod() { return materialCostMod; }

	/**
	 * Gets the {@link #mechanics}.
	 * 
	 * @return the {@link #mechanics}.
	 */
	public Double getMechanics() { return mechanics; }

	/**
	 * Gets the {@link #engineers}.
	 * 
	 * @return the {@link #engineers}.
	 */
	public Double getEngineers() { return engineers; }

}
