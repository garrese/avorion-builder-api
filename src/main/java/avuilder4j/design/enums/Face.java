package avuilder4j.design.enums;

import java.util.ArrayList;
import java.util.Arrays;

import avuilder4j.error.AvErrors;

public enum Face {

	XL(0),
	XU(1),
	YL(2),
	YU(3),
	ZL(4),
	ZU(5);

	private int index;

	private Face(int index) {
		this.index = index;
	}

	public static Face[] getFacesByAxis(Axis axis) {
		switch (axis) {
		case X:
			return new Face[] { XL, XU };
		case Y:
			return new Face[] { YL, YU };
		case Z:
			return new Face[] { ZL, ZU };
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	public static ArrayList<Face> getFacesInvolvedInBlockRotation(Rotation rotation) {
		Face[] faces;
		switch (rotation) {
		case AROUND_X:
			faces = new Face[] { YU, ZU, YL, ZL };
			break;
		case AROUND_X_INVERSE:
			return reverseFacesInvolvedInBlockRotation(Rotation.AROUND_X);
		case AROUND_Y:
			faces = new Face[] { ZU, XU, ZL, XL };
			break;
		case AROUND_Y_INVERSE:
			return reverseFacesInvolvedInBlockRotation(Rotation.AROUND_Y);
		case AROUND_Z:
			faces = new Face[] { YU, XL, YL, XU };
			break;
		case AROUND_Z_INVERSE:
			return reverseFacesInvolvedInBlockRotation(Rotation.AROUND_Z);
		default:
			throw new IllegalArgumentException(AvErrors.ROTATION_NOT_RECOGNIZED);
		}

		return new ArrayList<>(Arrays.asList(faces));
	}

	private static ArrayList<Face> reverseFacesInvolvedInBlockRotation(Rotation rotation) {
		ArrayList<Face> facesIn = getFacesInvolvedInBlockRotation(rotation);
		ArrayList<Face> faces = new ArrayList<Face>();
		for (int i = facesIn.size(); i > 0; i--) {
			faces.add(facesIn.get(i - 1));
		}
		return faces;
	}

	public static Face getOpposite(Face face) {
		switch (face) {
		case XL:
			return XU;
		case XU:
			return XL;
		case YL:
			return YU;
		case YU:
			return YL;
		case ZL:
			return ZU;
		case ZU:
			return ZL;

		default:
			throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
		}
	}

	public static Face getByIndex(Integer index) {
		if (index != null)
			for (Face f : Face.values())
				if (f.getIndex() == index.intValue())
					return f;
		return null;
	}

	public int getIndex() { return index; }

}
