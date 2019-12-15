package avuilder4j.design.sub.functional;

import avuilder4j.data.DataMaps;
import avuilder4j.util.java.NullSafe;
import avuilder4j.util.java.Chainable;
import avuilder4j.util.keys.Cons;

public class HangarSpace implements Chainable<HangarSpace> {

	protected Double hangarSpace;

	public HangarSpace() {}

	public HangarSpace(Double hangarSpace) {
		this.hangarSpace = hangarSpace;
	}

	public HangarSpace addHangarSpace(Double hangarSpace) {
		NullSafe.run(() -> setHangarSpace(getHangarSpace() + hangarSpace));
		return chain();
	}

	public Integer getMaxFighters() { return NullSafe.run(() -> getHangarSpace().intValue()); }

	public Integer getMinFighters() {
		return NullSafe.run(() -> getMaxFighters() / DataMaps.getConstantValue(Cons.MAX_FIGHTER_SIZE).intValue());
	}

	public Double getHangarSpace() { return hangarSpace; }

	public void setHangarSpace(Double hangarSpace) { this.hangarSpace = hangarSpace; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HangarSpace [hangarSpace=");
		builder.append(hangarSpace);
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
