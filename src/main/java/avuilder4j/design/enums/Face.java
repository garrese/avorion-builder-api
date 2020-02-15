package avuilder4j.design.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;

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

	public static List<Face> getFacesByAxis(Axis axis) {
		ArrayList<Face> list = new ArrayList<>();
		switch (axis) {
		case X:
			list.add(XL);
			list.add(XU);
			break;
		case Y:
			list.add(YL);
			list.add(YU);
			break;
		case Z:
			list.add(ZL);
			list.add(ZU);
			break;
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
		return list;
	}

	public static ArrayList<Face> getFacesInvolvedInRotation(Axis rotationAxis, boolean inverse) {
		Face[] faces = null;

		if (!inverse) {
			switch (rotationAxis) {
			case X:
				faces = new Face[] { YU, ZU, YL, ZL };
				break;
			case Y:
				faces = new Face[] { ZU, XU, ZL, XL };
				break;
			case Z:
				faces = new Face[] { YU, XL, YL, XU };
				break;
			default:
				throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
			}
		} else {
			switch (rotationAxis) {
			case X:
				return reverseFacesInvolvedInBlockRotation(Axis.X);
			case Y:
				return reverseFacesInvolvedInBlockRotation(Axis.Y);
			case Z:
				return reverseFacesInvolvedInBlockRotation(Axis.Z);
			default:
				throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
			}
		}

		return new ArrayList<>(Arrays.asList(faces));
	}

	private static ArrayList<Face> reverseFacesInvolvedInBlockRotation(Axis rotationAxis) {
		ArrayList<Face> facesIn = getFacesInvolvedInRotation(rotationAxis, false);
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
		if (index != null) {
			for (Face f : Face.values()) {
				if (f.getIndex() == index.intValue())
					return f;
			}
			throw new Avuilder4jRuntimeException(String.format(AvErrors.FACE_NOT_EXISTS, index));
		} else
			return null;
	}

	public int getIndex() { return index; }

}
