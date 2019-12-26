package avuilder4j.design.base;

@SuppressWarnings("rawtypes")
public interface BlockInterfaceImporter<B extends BlockInterfaceImporter> {

	public B setIndex(Integer index);

	public B setParent(B parent);

	public B setXL(Double XL);

	public B setXU(Double XU);

	public B setYL(Double YL);

	public B setYU(Double YU);

	public B setZL(Double ZL);

	public B setZU(Double ZU);

	public B setMaterialIndex(Integer materialIndex);

	public B setTypeIndex(Integer typeIndex);

	public B setLook(Integer look);

	public B setUp(Integer up);

	public B setColor(String color);

}
