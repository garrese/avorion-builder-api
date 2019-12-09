package avuilder4j.design.enums;

import java.util.ArrayList;
import java.util.Arrays;

import avuilder4j.error.AvErrors;

public enum Face {

	LX(0),
	UX(1),
	LY(2),
	UY(3),
	LZ(4),
	UZ(5);

	private int index;

	private Face(int index) {
		this.index = index;
	}

	public static Face[] getFacesByAxis(Axis axis) {
		switch (axis) {
		case X:
			return new Face[] { LX, UX };
		case Y:
			return new Face[] { LY, UY };
		case Z:
			return new Face[] { LZ, UZ };
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	public static ArrayList<Face> getFacesInvolvedInBlockRotation(Rotation rotation) {
		Face[] faces;
		switch (rotation) {
		case AROUND_X:
			faces = new Face[] { UY, UZ, LY, LZ };
			break;
		case AROUND_X_INVERSE:
			return reverseFacesInvolvedInBlockRotation(Rotation.AROUND_X);
		case AROUND_Y:
			faces = new Face[] { UZ, UX, LZ, LX };
			break;
		case AROUND_Y_INVERSE:
			return reverseFacesInvolvedInBlockRotation(Rotation.AROUND_Y);
		case AROUND_Z:
			faces = new Face[] { UY, LX, LY, UX };
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
		case LX:
			return UX;
		case UX:
			return LX;
		case LY:
			return UY;
		case UY:
			return LY;
		case LZ:
			return UZ;
		case UZ:
			return LZ;

		default:
			throw new IllegalArgumentException(AvErrors.FACE_NOT_RECOGNIZED);
		}
	}

	public int getIndex() { return index; }

}
