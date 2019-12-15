package avuilder4j.data.beans;

import java.io.Serializable;

public class BeanConstant implements Serializable {
	private static final long serialVersionUID = -5211125129554805337L;

	protected String name;
	protected String category;
	protected Double value;

	public BeanConstant(String name, String category, Double number) {
		this.name = name;
		this.category = category;
		this.value = number;
	}

	public String getName() { return name; }

	public String getCategory() { return category; }

	public Double getValue() { return value; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Constant [name=");
		builder.append(name);
		builder.append(", category=");
		builder.append(category);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BeanConstant))
			return false;
		BeanConstant other = (BeanConstant) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
