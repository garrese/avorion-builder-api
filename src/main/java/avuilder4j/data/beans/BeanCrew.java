package avuilder4j.data.beans;

import java.util.Objects;

public class BeanCrew {

	protected String name;
	protected Double salary;

	public BeanCrew(String name, Double salary) {
		this.name = name;
		this.salary = salary;
	}

	public String getName() { return name; }

	public Double getSalary() { return salary; }

	@Override
	public int hashCode() {
		return Objects.hash(name, salary);
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
		return Objects.equals(name, other.name) && Objects.equals(salary, other.salary);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Crew [");
		builder.append("name=");
		builder.append(name);
		builder.append(", salary=");
		builder.append(salary);
		builder.append("]");
		return builder.toString();
	}

}
