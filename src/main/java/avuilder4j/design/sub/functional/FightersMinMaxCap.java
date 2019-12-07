package avuilder4j.design.sub.functional;

import avuilder4j.data.DataMaps;
import avuilder4j.util.java.NullSafe;
import avuilder4j.util.keys.Cons;

public class FightersMinMaxCap {

	public FightersMinMaxCap() {}

	public FightersMinMaxCap(Integer maxFighters) {
		this.maxFighters = maxFighters;
	}

	public Integer maxFighters;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HangarSpace [maxFighters=");
		builder.append(maxFighters);
		builder.append(", minFighters=");
		builder.append(getMinFighters());
		builder.append("]");
		return builder.toString();
	}

	public Integer getMaxFighters() { return maxFighters; }

	public void setMaxFighters(Integer maxFighters) { this.maxFighters = maxFighters; }

	public Integer getMinFighters() {
		return NullSafe.get(() -> maxFighters / DataMaps.getConstantValue(Cons.MAX_FIGHTER_SIZE).intValue());
	}

}
