package avuilder.core.entities.dimensional;

import avuilder.core.error.AvuilderCoreRuntimeException;

public class Line3D {

	private Point p1;
	private Point p2;

	public Line3D() {
	}

	/**
	 * @param p1 the {@link #p1}
	 * @param p2 the {@link #p2}
	 */
	public Line3D(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public double getLength() {
		double l = 0;
		try {
			l = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2) + Math.pow(p1.z - p2.z, 2));
		} catch (Exception e) {
			throw new AvuilderCoreRuntimeException("Impossible to calculate line length. Line not enough defined.", e);
		}
		return l;
	}

	public Point getCenter() {
		Point p = new Point();
		try {
			p.x = (p1.x + p2.x) / 2;
			p.y = (p1.y + p2.y) / 2;
			p.z = (p1.z + p2.z) / 2;
		} catch (Exception e) {
			throw new AvuilderCoreRuntimeException("Impossible to calculate line center. Line not enough defined.", e);
		}
		return p;
	}

}
