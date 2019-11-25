package avuilder4j.design.sub;

import java.io.Serializable;
import java.util.Objects;

public class DataReportGeneric<V> extends TagsAdministrator implements Serializable, Tagable {
	private static final long serialVersionUID = 16373479010101139L;

	protected V result;
	protected int nulls;
	protected TagsAdministrator tagsAdministrator = new TagsAdministrator();

	public DataReportGeneric() {}

	public DataReportGeneric(String tags) {
		tagsAdministrator.addTags(tags);
	}

	public DataReportGeneric(V result) {
		this.result = result;
	}

	public V getResult() { return result; }

	@Override
	public TagsAdministrator getTagsAdministrator() { return tagsAdministrator; }

	public void setResult(V result) { this.result = result; }

	public int getNulls() { return nulls; }

	public void setNulls(int nulls) { this.nulls = nulls; }

	public void addNull() {
		nulls++;
	}

	public void addNulls(int n) {
		nulls += n;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataReportGeneric [tags=");
		builder.append(tagsAdministrator.getTags());
		builder.append(", result=");
		builder.append(result);
		builder.append(", nulls=");
		builder.append(nulls);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(nulls, result);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DataReportGeneric))
			return false;
		DataReportGeneric other = (DataReportGeneric) obj;
		return nulls == other.nulls && Objects.equals(result, other.result);
	}

}
