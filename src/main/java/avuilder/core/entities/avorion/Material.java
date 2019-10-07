package avuilder.core.entities.avorion;

/**
 * Immutable Avorion material reference.
 */
public class Material {

	/**
	 * Material's index in game.
	 */
	protected int index;
	/**
	 * Material's name.
	 */
	protected String name;
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


	/**
	 * Material reference constructor.
	 * 
	 * @param index        the {@link #index}
	 * @param density      the {@link #density}
	 * @param durability   the {@link #durability}
	 * @param creditCost   the {@link #creditCost}
	 * @param materialCost the {@link #materialCost}
	 */
	protected Material(int index, String name, double density, double durability, double creditCost, double materialCost) {
		super();
		this.index = index;
		this.name = name;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Material [index=" + index + ", name=" + name + ", density=" + density + ", durability=" + durability
				+ ", creditCost=" + creditCost + ", materialCost=" + materialCost + "]";
	}
	
	

}
