package avuilder4j.design.sub.functional;

import avuilder4j.design.sub.dimensional.V3Generic;

public class IFGAura extends V3Generic<IFGAura> {

	public IFGAura() {
		super();
	}

	public IFGAura(Number x, Number y, Number z) {
		super(x, y, z);
	}

	public IFGAura(Number xyz) {
		super(xyz);
	}

	public IFGAura(IFGAura xyz) {
		super(xyz);
	}

	@Override
	public IFGAura chain() {
		return this;
	}

}
