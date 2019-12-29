package avuilder4j.design.sub.functional;

import avuilder4j.data.DataMaps;
import avuilder4j.util.java.Chainable;
import avuilder4j.util.java.Nullable;
import avuilder4j.util.keys.Cons;

public class HangarSpace implements Chainable<HangarSpace> {

	protected Double value;

	public HangarSpace() {}

	public HangarSpace(Double hangarSpace) {
		this.value = hangarSpace;
	}

	public HangarSpace addHangarSpace(Double hangarSpace) {
		Nullable.m(() -> setHangarSpace(getValue() + hangarSpace));
		return chain();
	}

	public Integer getMaxFighters() { return Nullable.m(() -> getValue().intValue()); }

	public Integer getMinFighters() { return Nullable.m(() -> getMaxFighters() / DataMaps.getConstantValue(Cons.MAX_FIGHTER_SIZE).intValue()); }

	public Double getValue() { return value; }

	public void setHangarSpace(Double hangarSpace) { this.value = hangarSpace; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HangarSpace [value=");
		builder.append(value);
		builder.append(", maxFighters=");
		builder.append(getMaxFighters());
		builder.append(", minFighters=");
		builder.append(getMinFighters());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public HangarSpace chain() {
		return this;
	}

}
