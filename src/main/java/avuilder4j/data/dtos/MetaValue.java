package avuilder4j.data.dtos;

import java.io.Serializable;
import java.util.Objects;

public class MetaValue implements Serializable {
	private static final long serialVersionUID = -5211125129554805337L;

	protected String name;
	protected String group;
	protected String text;
	protected Double number;

	public MetaValue(String name, String group, String text, Double number) {
		this.name = name;
		this.group = group;
		this.text = text;
		this.number = number;
	}

	public String getName() { return name; }

	public String getGroup() { return group; }

	public String getTextValue() { return text; }

	public Double getNumber() { return number; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MetaValue [name=");
		builder.append(name);
		builder.append(", group=");
		builder.append(group);
		builder.append(", text=");
		builder.append(text);
		builder.append(", number=");
		builder.append(number);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(group, name, number, text);
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
		return Objects.equals(group, other.group) && Objects.equals(name, other.name)
				&& Objects.equals(number, other.number) && Objects.equals(text, other.text);
	}

}
