package avuilder4j.dtos.base;

public abstract class TypeFields implements Indexable {

	protected Integer index;
	protected Integer typeModelIndex;
	protected Integer shapeIndex;

	@Override
	public Integer getIndex() {
		return index;
	}

	public Integer getTypeModelIndex() {
		return typeModelIndex;
	}

	public Integer getShapeIndex() {
		return shapeIndex;
	}

}
