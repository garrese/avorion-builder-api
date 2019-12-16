package avuilder4j.data.beans;

import java.util.Objects;

public class BeanCrew {

	protected Integer index;
	protected String name;
	protected Double salary;

	public BeanCrew(Integer index, String name, Double salary) {
		this.index = index;
		this.name = name;
		this.salary = salary;
	}

	public Integer getIndex() { return index; }

	public String getName() { return name; }

	public Double getSalary() { return salary; }

	@Override
	public int hashCode() {
		return Objects.hash(index, name, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BeanCrew))
			return false;
		BeanCrew other = (BeanCrew) obj;
		return Objects.equals(index, other.index) && Objects.equals(name, other.name)
				&& Objects.equals(salary, other.salary);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Crew [index=");
		builder.append(index);
		builder.append(", name=");
		builder.append(name);
		builder.append(", salary=");
		builder.append(salary);
		builder.append("]");
		return builder.toString();
	}

}
