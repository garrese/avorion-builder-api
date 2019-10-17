package avuilder.core.entities.dimensional;

import avuilder.core.error.AvuilderCoreRuntimeException;
import avuilder.core.utils.K;

/**
 * Represents a cuboid in a Cartesian coordinate system.
 * <p>
 * The cuboid is defined by one {@link AxisLine} in each of the three coordinate axis.
 */
public class Cuboid {

	/**
	 * X axis line.
	 */
	private AxisLine lineX = new AxisLine();

	/**
	 * Y axis line.
	 */
	private AxisLine lineY = new AxisLine();

	/**
	 * Z axis line.
	 */
	private AxisLine lineZ = new AxisLine();

	public Cuboid() {
	}

	/**
	 * @param lineX the {@link #lineX}
	 * @param lineY the {@link #lineY}
	 * @param lineZ the {@link #lineZ}
	 */
	public Cuboid(AxisLine lineX, AxisLine lineY, AxisLine lineZ) {
		this.lineX = lineX;
		this.lineY = lineY;
		this.lineZ = lineZ;
	}

	public static Cuboid deepCopy(Cuboid cuboid) {
		Cuboid c = null;
		if (cuboid != null) {
			c = new Cuboid();
			c.setLineX(AxisLine.deepCopy(cuboid.getLineX()));
			c.setLineY(AxisLine.deepCopy(cuboid.getLineY()));
			c.setLineZ(AxisLine.deepCopy(cuboid.getLineZ()));
		}
		return c;
	}

	/**
	 * Calculates the central point of the cuboid.
	 * 
	 * @return the central point of the cuboid.
	 */
	public Point getCenter() {
		Point p = new Point();
		try {
			p.x = lineX.getCenter();
			p.y = lineY.getCenter();
			p.z = lineZ.getCenter();
		} catch (Exception e) {
			throw new AvuilderCoreRuntimeException("Impossible to calculate cuboid center", e);
		}
		return p;
	}

	public Point getFaceCenter(String face) {
		Point p = null;

		try {
			p = getCenter();
			switch (face) {
			case K.FACE_UX:
				p.x = getLineX().getUP();
				break;
			case K.FACE_LX:
				p.x = getLineX().getLP();
				break;
			case K.FACE_UY:
				p.y = getLineY().getUP();
				break;
			case K.FACE_LY:
				p.y = getLineY().getLP();
				break;
			case K.FACE_UZ:
				p.z = getLineZ().getUP();
				break;
			case K.FACE_LZ:
				p.z = getLineZ().getLP();
				break;
			default:
				throw new IllegalArgumentException("Illegal face: " + face + ".");
			}
		} catch (Exception e) {
			throw new AvuilderCoreRuntimeException("Impossible to calculate cuboid face center", e);
		}

		return p;
	}

	public Point getCorner(String corner) {
		Point p = getCenter();
		switch (corner) {
		case K.FACE_UX:
			p.x = getLineX().getUP();
			break;
		case K.FACE_LX:
			p.x = getLineX().getLP();
			break;
		case K.FACE_UY:
			p.y = getLineY().getUP();
			break;
		case K.FACE_LY:
			p.y = getLineY().getLP();
			break;
		case K.FACE_UZ:
			p.z = getLineZ().getUP();
			break;
		case K.FACE_LZ:
			p.z = getLineZ().getLP();
			break;
		default:
			throw new IllegalArgumentException("Illegal corner: " + corner + ".");
		}

		return p;
	}

	/**
	 * Gets the {@link #lineX}.
	 * 
	 * @return the {@link #lineX}.
	 */
	public AxisLine getLineX() {
		return lineX;
	}

	/**
	 * Sets the {@link #lineX}.
	 * 
	 * @param lineX the {@link #lineX} to set.
	 */
	public void setLineX(AxisLine lineX) {
		this.lineX = lineX;
	}

	/**
	 * Gets the {@link #lineY}.
	 * 
	 * @return the {@link #lineY}.
	 */
	public AxisLine getLineY() {
		return lineY;
	}

	/**
	 * Sets the {@link #lineY}.
	 * 
	 * @param lineY the {@link #lineY} to set.
	 */
	public void setLineY(AxisLine lineY) {
		this.lineY = lineY;
	}

	/**
	 * Gets the {@link #lineZ}.
	 * 
	 * @return the {@link #lineZ}.
	 */
	public AxisLine getLineZ() {
		return lineZ;
	}

	/**
	 * Sets the {@link #lineZ}.
	 * 
	 * @param lineZ the {@link #lineZ} to set.
	 */
	public void setLineZ(AxisLine lineZ) {
		this.lineZ = lineZ;
	}

}
