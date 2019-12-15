package avuilder4j.design.sub.dimensional;

import java.io.Serializable;
import java.util.ArrayList;

import avuilder4j.design.enums.Face;
import avuilder4j.design.enums.Rotation;
import avuilder4j.error.AvValidations;
import avuilder4j.util.java.Chainable;
import avuilder4j.util.java.Copyable;

/**
 * Block orientation
 */
public class Orientation implements Serializable, Copyable<Orientation>, Chainable<Orientation> {
	private static final long serialVersionUID = -7559284143961816577L;

	/*
	 * Face numbers for look and up: lx 1 ux 0 ly 3 uy 2 lz 4 uz 5
	 */

	/**
	 * Look component of the piece orientation.
	 */
	private Face look;

	/**
	 * Up component of the piece orientation.
	 */
	private Face up;

	public Orientation() {}

	/**
	 * @param look the {@link #look}
	 * @param up   the {@link #up}
	 */
	public Orientation(Face look, Face up) {
		setLook(look);
		setUp(up);
	}

	public Orientation rotate(Rotation rotation) {
		rotate(rotation, 1);
		return this;
	}

	public Orientation rotate(Rotation rotation, int times) {
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
		return this;
	}

	public Face getLook() { return look; }

	public Orientation setLook(Face look) {
		Face savedLook = getLook();
		this.look = look;
		try {
			AvValidations.orientations(true, this);
		} catch (IllegalArgumentException e) {
			this.look = savedLook;
			throw e;
		}
		return this;
	}

	public Orientation set(Face look, Face up) {
		Face savedLook = getLook();
		Face savedUp = getUp();
		this.look = look;
		this.up = up;
		try {
			AvValidations.orientations(true, this);
		} catch (IllegalArgumentException e) {
			this.look = savedLook;
			this.up = savedUp;
			throw e;
		}
		return this;
	}

	public Face getUp() { return up; }

	public Orientation setUp(Face up) {
		Face savedUp = getUp();
		this.up = up;
		try {
			AvValidations.orientations(true, this);
		} catch (IllegalArgumentException e) {
			this.up = savedUp;
			throw e;
		}
		return this;
	}

	@Override
	public String toString() {
		return "[look=" + look + ", up=" + up + "]";
	}

	@Override
	public Orientation getCopy() { return new Orientation().set(this.look, this.up); }

	@Override
	public Orientation chain() {
		return this;
	}

	public static boolean isDefined(Orientation orientation) {
		if (orientation != null && orientation.getLook() != null && orientation.getUp() != null)
			return true;
		else
			return false;
	}

}
