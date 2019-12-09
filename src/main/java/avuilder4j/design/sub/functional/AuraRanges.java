package avuilder4j.design.sub.functional;

import avuilder4j.design.sub.dimensional.Xyz;

public class AuraRanges extends Xyz<AuraRanges> {

	@Override
	public AuraRanges returnThis() {
		return this;
	}

	@Override
	public AuraRanges getCopy() { return new AuraRanges().copyXyz(this); }

}
