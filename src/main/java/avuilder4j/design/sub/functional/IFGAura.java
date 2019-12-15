package avuilder4j.design.sub.functional;

import avuilder4j.design.sub.dimensional.Xyz;

public class IFGAura extends Xyz<IFGAura> {

	@Override
	public IFGAura chain() {
		return this;
	}

	@Override
	public IFGAura getCopy() { return new IFGAura().copyXyz(this); }

}
