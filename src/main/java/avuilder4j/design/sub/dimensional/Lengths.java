package avuilder4j.design.sub.dimensional;

import java.io.Serializable;

public class Lengths extends Xyz<Lengths> implements Serializable {
	private static final long serialVersionUID = -3737835992118370776L;

	@Override
	public Lengths returnThis() {
		return this;
	}

	@Override
	public Lengths getCopy() { return new Lengths().copyXyz(this); }

	public Lengths() {
		super();
	}

	public Lengths(double x, double y, double z) {
		super(x, y, z);
	}

	public Lengths(Double x, Double y, Double z) {
		super(x, y, z);
	}

}
