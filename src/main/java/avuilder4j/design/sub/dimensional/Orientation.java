package avuilder4j.design.sub.dimensional;

import java.io.Serializable;
import java.util.ArrayList;

import avuilder4j.design.enums.Face;
import avuilder4j.design.enums.Rotation;
import avuilder4j.error.AvValidations;

/**
 * Block orientation
 */
public class Orientation implements Serializable {
	private static final long serialVersionUID = -7559284143961816577L;

	/*
	 * Face numbers for look and up: lx 1 ux 0 ly 3 uy 2 lz 4 uz 5
	 */

	/**
	 * Look component of the piece orientation.
	 */
	private Face look = Face.UZ;

	/**
	 * Up component of the piece orientation.
	 */
	private Face up = Face.UY;

	public Orientation() {}

	/**
	 * @param look the {@link #look}
	 * @param up   the {@link #up}
	 */
	public Orientation(Face look, Face up) {
		setLook(look);
		setUp(up);
	}

	public void rotate(Rotation rotation) {
		rotate(rotation, 1);
	}

	public void rotate(Rotation rotation, int times) {
		ArrayList<Face> faces = Face.getFacesInvolvedInBlockRotation(rotation);

		for (int i = 0; i < times; i++) {
			int lookPos = faces.indexOf(getLook());
			int upPos = faces.indexOf(getUp());

			if (lookPos != -1) {
				lookPos++;
				if (lookPos == faces.size())
					lookPos = 0;
				look = faces.get(lookPos);
			}

			if (upPos != -1) {
				upPos++;
				if (upPos == faces.size())
					upPos = 0;
				up = faces.get(upPos);
			}
		}
	}

	public static Orientation deepCopy(Orientation typeLook) {
		Orientation o = null;
		if (typeLook != null) {
			o = new Orientation(typeLook.getLook(), typeLook.getUp());
		}
		return o;
	}

	public Face getLook() { return look; }

	protected void setLook(Face look) {
		AvValidations.orientations(true, this);
		this.look = look;
	}

	public Face getUp() { return up; }

	protected void setUp(Face up) {
		AvValidations.orientations(true, this);
		this.up = up;
	}

	@Override
	public String toString() {
		return "[look=" + look.getIndex() + ", up=" + up.getIndex() + "]";
	}
//
//	public List<Face> getComponents() {
//		List<Face> faces = new ArrayList<Face>();
//		if (getLook() != null)
//			faces.add(getLook());
//		if (getUp() != null)
//			faces.add(getUp());
//		return faces;
//	}
}
