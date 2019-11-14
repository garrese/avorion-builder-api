package avuilder4j.dtos.base;

public class ShapeFields implements Indexable {

	protected Integer index;

	protected String name;

	protected Double volumeMod;

	@Override
	public Integer getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public Double getVolumeMod() {
		return volumeMod;
	}

}
