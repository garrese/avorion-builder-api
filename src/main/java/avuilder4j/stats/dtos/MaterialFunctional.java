package avuilder4j.stats.dtos;

import avuilder4j.structural.dtos.MaterialStructural;

public class MaterialFunctional extends MaterialStructural {

	/**
	 * Material's name.
	 */
	protected String name;
	/**
	 * Material's density in t/m^3.
	 */
	protected Double baseDensity;
	/**
	 * Material's durability in hp/m^3.
	 */
	protected Double baseDurability;
	/**
	 * Material's credit cost in credits/m^3.
	 */
	protected Double baseCreditCost;
	/**
	 * Material's material cost in ore/m^3.
	 */
	protected Double baseMaterialCost;

	public MaterialFunctional(int index) {
		super(index);
	}

	/**
	 * @param index
	 * @param name
	 * @param baseDensity
	 * @param baseDurability
	 * @param baseCreditCost
	 */
	public MaterialFunctional(int index, String name, Double baseDensity, Double baseDurability,
			Double baseCreditCost) {
		super(index);
		this.name = name;
		this.baseDensity = baseDensity;
		this.baseDurability = baseDurability;
		this.baseCreditCost = baseCreditCost;
		this.baseMaterialCost = 5.0;
	}

	/**
	 * Gets the {@link #name}.
	 * 
	 * @return the {@link #name}.
	 */
	public String getName() { return name; }

	/**
	 * Gets the {@link #baseDensity}.
	 * 
	 * @return the {@link #baseDensity}.
	 */
	public Double getBaseDensity() { return baseDensity; }

	/**
	 * Gets the {@link #baseDurability}.
	 * 
	 * @return the {@link #baseDurability}.
	 */
	public Double getBaseDurability() { return baseDurability; }

	/**
	 * Gets the {@link #baseCreditCost}.
	 * 
	 * @return the {@link #baseCreditCost}.
	 */
	public Double getBaseCreditCost() { return baseCreditCost; }

	/**
	 * Gets the {@link #baseMaterialCost}.
	 * 
	 * @return the {@link #baseMaterialCost}.
	 */
	public Double getBaseMaterialCost() { return baseMaterialCost; }

}
