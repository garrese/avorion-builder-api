package avuilder4j.design.sub.dimensional;

import java.io.Serializable;
import java.util.ArrayList;

import avuilder4j.design.enums.Axis;
import avuilder4j.design.enums.Face;
import avuilder4j.error.AvValidations;
import avuilder4j.util.java.Chainable;
import avuilder4j.util.java.Copyable;

/**
 * Block orientation
 */
public class Orientation implements Serializable, Copyable<Orientation>, Chainable<Orientation> {
	private static final long serialVersionUID = -7559284143961816577L;

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
		set(look, up);
	}

	public Orientation(Integer look, Integer up) {
		set(look, up);
	}

	public static Orientation copy(Orientation copyFrom, Orientation copyTo) {
		if (copyFrom != null && copyTo != null) {
			copyTo.set(copyFrom.getLook(), copyFrom.getUp());
		}
		return copyTo;
	}

	public static boolean isDefined(Orientation orientation) {
		if (orientation != null && orientation.getLook() != null && orientation.getUp() != null)
			return true;
		else
			return false;
	}

	@Override
	public Orientation chain() {
		return this;
	}

	@Override
	public Orientation getCopy() { return Orientation.copy(this, new Orientation()); }

	public Face getLook() { return look; }

	public Face getUp() { return up; }

	public Orientation rotate(Axis rotationAxis) {
		rotate(rotationAxis, 1);
		return this;
	}

	public Orientation rotate(Axis rotationAxis, int times) {
		ArrayList<Face> faces = Face.getFacesInvolvedInRotation(rotationAxis, false);

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

	public Orientation set(Integer look, Integer up) {
		setLook(Face.getByIndex(look));
		setUp(Face.getByIndex(up));
		return chain();
	}

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

	public Orientation setLook(Integer look) {
		return setLook(Face.getByIndex(look));
	}

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

	public Orientation setUp(Integer up) {
		return setUp(Face.getByIndex(up));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Orientation [look=");
		builder.append(look);
		builder.append(", up=");
		builder.append(up);
		builder.append("]");
		return builder.toString();
	}

}
