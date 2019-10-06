package entities.avorion;

/**
 * Immutable Avorion material reference.
 */
public class Material {

	/**
	 * Material's index in game.
	 */
	private int index;
	/**
	 * Material's density in t/m^3.
	 */
	private double density;
	/**
	 * Material's durability in hp/m^3.
	 */
	private double durability;
	/**
	 * Material's credit cost in credits/m^3.
	 */
	private double creditCost;
	/**
	 * Material's material cost in ore/m^3.
	 */
	private double materialCost;

	/**
	 * Material reference constructor.
	 * 
	 * @param index        the {@link #index}
	 * @param density      the {@link #density}
	 * @param durability   the {@link #durability}
	 * @param creditCost   the {@link #creditCost}
	 * @param materialCost the {@link #materialCost}
	 */
	public Material(int index, double density, double durability, double creditCost, double materialCost) {
		super();
		this.index = index;
		this.density = density;
		this.durability = durability;
		this.creditCost = creditCost;
		this.materialCost = materialCost;
	}

	/**
	 * Gets the {@link #index}.
	 * 
	 * @return the {@link #index}.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Gets the {@link #density}.
	 * 
	 * @return the {@link #density}.
	 */
	public double getDensity() {
		return density;
	}

	/**
	 * Gets the {@link #durability}.
	 * 
	 * @return the {@link #durability}.
	 */
	public double getDurability() {
		return durability;
	}

	/**
	 * Gets the {@link #creditCost}.
	 * 
	 * @return the {@link #creditCost}.
	 */
	public double getCreditCost() {
		return creditCost;
	}

	/**
	 * Gets the {@link #materialCost}.
	 * 
	 * @return the {@link #materialCost}.
	 */
	public double getMaterialCost() {
		return materialCost;
	}

}
