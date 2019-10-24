package avuilder4j.entities.game;

/**
 * Immutable Avorion block type reference.
 */
public abstract class TypeBlock {

	protected int index;

	public TypeBlock(int index) {
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
