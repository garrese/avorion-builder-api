package avuilder4j.util;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.BiFunction;

import avuilder4j.design.sub.Tagable;
import avuilder4j.design.sub.TagsAdministrator;

public class OperationOfNullables<R> implements Serializable, Tagable {
	private static final long serialVersionUID = 16373479010101139L;

	protected R result;
	protected int nullCases;
	protected TagsAdministrator tagsAdministrator = new TagsAdministrator();

	public static BiFunction<Double, Double, Double> sumDoubles = (arg, result) -> {
		if (result == null)
			result = 0d;
		result += arg;
		return result;
	};

	public OperationOfNullables() {}

	public OperationOfNullables(String tags) {
		tagsAdministrator.addTags(tags);
	}

	public OperationOfNullables(R result) {
		this.result = result;
	}

	public R getResult() { return result; }

	@Override
	public TagsAdministrator getTagsAdministrator() { return tagsAdministrator; }

	public void setResult(R result) { this.result = result; }

	public int getNullCases() { return nullCases; }

	public void setNullCases(int nulls) { this.nullCases = nulls; }

	public void addNullCase() {
		nullCases++;
	}

	public void addNullCases(int n) {
		nullCases += n;
	}

	public <A> void operate(BiFunction<A, R, R> operation, A arg) {
		if (arg == null)
			addNullCase();
		else
			this.result = operation.apply(arg, this.result);
	}

	public <A> void operate(BiFunction<A, R, R> operation, Iterable<A> argList) {
		for (A arg : argList) {
			operate(operation, arg);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OperationOfNullables [tags=");
		builder.append(tagsAdministrator.getTags());
		builder.append(", result=");
		builder.append(result);
		builder.append(", nullCases=");
		builder.append(nullCases);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(nullCases, result);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof OperationOfNullables))
			return false;
		OperationOfNullables other = (OperationOfNullables) obj;
		return nullCases == other.nullCases && Objects.equals(result, other.result);
	}

}
