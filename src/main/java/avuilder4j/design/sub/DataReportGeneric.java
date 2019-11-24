package avuilder4j.design.sub;

import java.io.Serializable;
import java.util.Objects;

import avuilder4j.design.base.Tags;

public class DataReportGeneric<V> extends Tags implements Serializable {
	private static final long serialVersionUID = 16373479010101139L;

	protected V result;
	protected int nulls;

	public DataReportGeneric() {}

	public DataReportGeneric(String tags) {
		setTags(tags);
	}

	public DataReportGeneric(V result) {
		this.result = result;
	}

	public V getResult() { return result; }

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
		builder.append(tags);
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
