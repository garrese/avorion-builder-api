package avuilder4j.design.sub.dimensional;

import java.io.Serializable;

public class Lengths extends Xyz<Lengths> implements Serializable {
	private static final long serialVersionUID = -3737835992118370776L;

	public Lengths(Number x, Number y, Number z) {
		super(x, y, z);
	}

	public Lengths(Number xyz) {
		super(xyz);
	}

	public Lengths(Lengths xyz) {
		super(xyz);
	}

	public Lengths() {
		super();
	}

	@Override
	public Lengths chain() {
		return this;
	}

}
