package avuilder.core.managers;

import avuilder.core.entities.dimensional.Cuboid;
import avuilder.core.entities.dimensional.Margins;
import avuilder.core.entities.dimensional.Point;
import avuilder.core.utils.K;
import avuilder.core.utils.ValidationUtils;

public class BuildHelper {

	public void shapeCube(Cuboid cuboid, double lengthX, double lengthY, double lengthZ) {
		ValidationUtils.validateLengthArgs(lengthX, lengthY, lengthZ);
		Point c = getTrivialCenter(cuboid);

		Double midX = lengthX / 2;
		Double midY = lengthY / 2;
		Double midZ = lengthZ / 2;
		cuboid.getAxisX().setUP(c.x + midX);
		cuboid.getAxisX().setLP(c.x - midX);
		cuboid.getAxisY().setUP(c.y + midY);
		cuboid.getAxisY().setLP(c.y - midY);
		cuboid.getAxisZ().setUP(c.z + midZ);
		cuboid.getAxisZ().setLP(c.z - midZ);
	}

	public void shapeCube(Cuboid cuboid, double side) {
		shapeCube(cuboid, side, side, side);
	}

	public void escalateToVolume(Cuboid cuboid, double volume) {
		Cuboid.validate(cuboid);
		ValidationUtils.validateVolumeArgs(volume);

		// double a =
	}

	public void escalate(Cuboid cuboid, double ratio, int... dimensions) {
		Cuboid.validate(cuboid);
		ValidationUtils.validateRatioArgs(ratio);

		if (dimensions.length == 0) {
			dimensions = K.ALL_DIMENSIONS;
		}
		for (int d : dimensions) {
			cuboid.setUD(d, cuboid.getUD(d) * ratio);
			cuboid.setLD(d, cuboid.getLD(d) * ratio);
		}
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

	public void matchByVolume(Cuboid cuboid, double cuboidVolume, Cuboid parent, String parentFace, Margins margins) {

	}

}
