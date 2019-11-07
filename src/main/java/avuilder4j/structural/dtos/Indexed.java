package avuilder4j.structural.dtos;

public abstract class Indexed {

	protected int index;

	public Indexed(int index) {
		this.index = index;
	}

	public int getIndex() { return index; }

}
