package avuilder4j.data.dtos;

import java.io.Serializable;
import java.util.Objects;

public class MetaValue implements Serializable {
	private static final long serialVersionUID = -5211125129554805337L;

	protected String label;
	protected String group;
	protected String textValue;
	protected Double numericValue;

	public MetaValue(String label, String group, String textValue, Double numericValue) {
		this.label = label;
		this.group = group;
		this.textValue = textValue;
		this.numericValue = numericValue;
	}

	public String getLabel() { return label; }

	public String getGroup() { return group; }

	public String getTextValue() { return textValue; }

	public Double getNumericValue() { return numericValue; }

	@Override
	public int hashCode() {
		return Objects.hash(group, label, numericValue, textValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MetaValue))
			return false;
		MetaValue other = (MetaValue) obj;
		return Objects.equals(group, other.group) && Objects.equals(label, other.label)
				&& Objects.equals(numericValue, other.numericValue) && Objects.equals(textValue, other.textValue);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MetaValue [label=");
		builder.append(label);
		builder.append(", group=");
		builder.append(group);
		builder.append(", textValue=");
		builder.append(textValue);
		builder.append(", numericValue=");
		builder.append(numericValue);
		builder.append("]");
		return builder.toString();
	}

}
