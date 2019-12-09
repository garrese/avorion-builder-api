package avuilder4j.design.sub.functional;

import avuilder4j.data.DataMaps;
import avuilder4j.util.java.NullSafe;
import avuilder4j.util.java.ReturnThis;
import avuilder4j.util.keys.Cons;

public class HangarSpace implements ReturnThis<HangarSpace> {

	protected Double hangarSpace;

	public HangarSpace() {}

	public HangarSpace(Double hangarSpace) {
		this.hangarSpace = hangarSpace;
	}

	public HangarSpace addHangarSpace(Double hangarSpace) {
		NullSafe.go(() -> setHangarSpace(getHangarSpace() + hangarSpace));
		return returnThis();
	}

	public Integer getMaxFighters() { return NullSafe.go(() -> getHangarSpace().intValue()); }

	public Integer getMinFighters() {
		return NullSafe.go(() -> getMaxFighters() / DataMaps.getConstantValue(Cons.MAX_FIGHTER_SIZE).intValue());
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
	public HangarSpace returnThis() {
		return this;
	}

}
