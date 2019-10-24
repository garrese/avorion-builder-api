package avuilder4j.entities.game;

/**
 * Immutable Avorion material reference.
 */
public abstract class Material {

	/**
	 * Material's index in game.
	 */
	protected int index;

	public Material(int index) {
		super();
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
