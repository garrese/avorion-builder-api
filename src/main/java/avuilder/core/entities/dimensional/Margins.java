package avuilder.core.entities.dimensional;

public class Margins {

	public double uX;
	public double lX;
	public double uY;
	public double lY;
	public double uZ;
	public double lZ;

	public Margins() {

	}

	public Margins(double uX, double lX, double uY, double lY, double uZ, double lZ) {
		this.uX = uX;
		this.lX = lX;
		this.uY = uY;
		this.lY = lY;
		this.uZ = uZ;
		this.lZ = lZ;
	}

	public Margins(double x, double y, double z) {
		this.uX = x;
		this.lX = x;
		this.uY = y;
		this.lY = y;
		this.uZ = z;
		this.lZ = z;
	}

	public Margins(double margin) {
		this.uX = margin;
		this.lX = margin;
		this.uY = margin;
		this.lY = margin;
		this.uZ = margin;
		this.lZ = margin;
	}

	public void setX(double uX, double lX) {
		this.uX = uX;
		this.lX = lX;
	}

	public void setY(double uY, double lY) {
		this.uY = uY;
		this.lY = lY;
	}

	public void setZ(double uZ, double lZ) {
		this.uZ = uZ;
		this.lZ = lZ;
	}

}
