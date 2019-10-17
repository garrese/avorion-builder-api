package avuilder.core.entities.game;

/**
 * Immutable Avorion block type reference.
 */
public class TypeBlock {

	/**
	 * Block type index in game.
	 */
	protected int index;

	/**
	 * Material density modificator of the block type.
	 */
	protected Double density;
	
	/**
	 * Material durability modificator of the block type.
	 */
	protected Double durability;
	
	/**
	 * Material credit cost modificator of the block type.
	 */
	protected Double creditCost;
	
	/**
	 * Material cost modificator of the block type.
	 */
	protected Double materialCost;
	
	/**
	 * Mechanics cost in crew/m^3.
	 */
	protected Double mechanics;
	
	/**
	 * Engineers cost in crew/m^3.
	 */
	protected Double engineers;

	/**
	 * Block type reference constructor.
	 * 
	 * @param index        the {@link #index}
	 * @param density      the {@link #density}
	 * @param durability   the {@link #durability}
	 * @param creditCost   the {@link #creditCost}
	 * @param materialCost the {@link #materialCost}
	 * @param mechanics    the {@link #mechanics}
	 * @param engineers    the {@link #engineers}
	 */
	protected TypeBlock(int index, Double density, Double durability, Double creditCost, Double materialCost,
			Double mechanics, Double engineers) {
		super();
		this.index = index;
		this.density = density;
		this.durability = durability;
		this.creditCost = creditCost;
		this.materialCost = materialCost;
		this.mechanics = mechanics;
		this.engineers = engineers;
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
	public Double getDensity() {
		return density;
	}

	/**
	 * Gets the {@link #durability}.
	 * 
	 * @return the {@link #durability}.
	 */
	public Double getDurability() {
		return durability;
	}

	/**
	 * Gets the {@link #creditCost}.
	 * 
	 * @return the {@link #creditCost}.
	 */
	public Double getCreditCost() {
		return creditCost;
	}

	/**
	 * Gets the {@link #materialCost}.
	 * 
	 * @return the {@link #materialCost}.
	 */
	public Double getMaterialCost() {
		return materialCost;
	}

	/**
	 * Gets the {@link #mechanics}.
	 * 
	 * @return the {@link #mechanics}.
	 */
	public Double getMechanics() {
		return mechanics;
	}

	/**
	 * Gets the {@link #engineers}.
	 * 
	 * @return the {@link #engineers}.
	 */
	public Double getEngineers() {
		return engineers;
	}

}
