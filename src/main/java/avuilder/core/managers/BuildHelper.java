package avuilder.core.managers;

import avuilder.core.entities.dimensional.Cuboid;
import avuilder.core.entities.dimensional.Margins;
import avuilder.core.entities.dimensional.Point;
import avuilder.core.utils.K;
import avuilder.core.utils.ValidationUtils;

public class BuildHelper {

	public void shapeCuboid(Cuboid cuboid, double lengthX, double lengthY, double lengthZ) {
		ValidationUtils.validateLengthArgs(lengthX, lengthY, lengthZ);
		Point c = getTrivialCenter(cuboid);

		Double midX = lengthX / 2;
		Double midY = lengthY / 2;
		Double midZ = lengthZ / 2;
		cuboid.setUX(c.x + midX);
		cuboid.setLX(c.x - midX);
		cuboid.setUY(c.y + midY);
		cuboid.setLY(c.y - midY);
		cuboid.setUZ(c.z + midZ);
		cuboid.setLZ(c.z - midZ);
	}

	public void shapeCube(Cuboid cuboid, double sideLength) {
		shapeCuboid(cuboid, sideLength, sideLength, sideLength);
	}

	public void escalate(Cuboid cuboid, double ratio, int... dimensions) {
		Cuboid.validate(cuboid);
		ValidationUtils.validateRatios(ratio);
		ValidationUtils.validateDimensions(dimensions);

		if (dimensions.length == 0) {
			dimensions = K.ALL_DIMENSIONS;
		}
		for (int d : dimensions) {
			cuboid.setUD(d, cuboid.getUD(d) * ratio);
			cuboid.setLD(d, cuboid.getLD(d) * ratio);
		}
	}

	public void escalateByVolume(Cuboid cuboid, double finalVolume, int... dimensions) {
		Cuboid.validate(cuboid);
		ValidationUtils.validateVolumes(finalVolume);
		ValidationUtils.validateDimensions(dimensions);

		double ratio;

		switch (dimensions.length) {
		case 0:
		case 3:
			ratio = Math.cbrt(finalVolume / cuboid.getVolume());
			break;
		case 2:
			ratio = Math.sqrt(finalVolume / cuboid.getVolume());
			break;
		case 1:
			ratio = finalVolume / cuboid.getVolume();
			break;
		default:
			throw new RuntimeException("Error solving dimensions amount.");
		}
		escalate(cuboid, ratio, dimensions);
	}

	public Point getTrivialCenter(Cuboid cuboid) {
		Point center;
		if (Cuboid.isDefined(cuboid)) {
			center = cuboid.getCenter();
		} else {
			center = K.DEFAULT_CENTER;
		}
		return center;
	}

	public void matchFace(Cuboid cuboid, Cuboid parent, String parentFace, Margins margins) {

	}

}
