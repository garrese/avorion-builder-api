package avuilder4j.design;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import avuilder4j.design.base.CuboidGeneric;
import avuilder4j.design.enums.Axis;
import avuilder4j.design.sub.dimensional.Point;
import avuilder4j.error.Avuilder4jRuntimeException;
import avuilder4j.util.java.Nullable;
import avuilder4j.util.java.NumberUtils;

public class Mirror {

	protected Set<Axis> mirroringAxes = new TreeSet<Axis>();

	protected Point mirroringPoint = new Point(0);

	public Mirror() {}

	public Mirror(Axis... axes) {
		if (axes != null) {
			for (int i = 0; i < axes.length; i++) {
				getMirroringAxes().add(axes[i]);
			}
		}
	}

	public void addMirroringAxes(Axis... axes) {
		if (axes != null) {
			for (Axis axis : axes) {
				getMirroringAxes().add(axis);
			}
		}
	}

	public void clearMirroringAxes() {
		getMirroringAxes().clear();
	}

	public <T extends CuboidGeneric<T>> T findRefectedParent(T cuboid, List<T> structureContext) {
		Point reflectedParentCenter = Nullable.m(() -> reflect(cuboid.getParent().getCenter()));
		for (T t : structureContext) {
			if (Nullable.m(() -> t.getCenter().equals(reflectedParentCenter), false)) {
				return t;
			}
		}
		return null;
	}

	public Set<Axis> getMirroringAxes() { return mirroringAxes; }

	public Point getMirroringPoint() { return mirroringPoint; }

	public <T extends CuboidGeneric<T>> T move(T cuboid) {
		validateActiveAxes();
		Point newCenter = reflect(cuboid.getCenter());
		cuboid.moveCenterToPoint(newCenter);
		return cuboid;
	}

	public Point reflect(Point point) {
		Point p = new Point(point);
		for (Axis axis : getMirroringAxes()) {
			double aux = p.getV3Axis(axis);
			double newCoord = NumberUtils.negate(aux);
			p.setV3Axis(axis, newCoord);
		}
		return p;
	}

	public <T extends CuboidGeneric<T>> T reflect(T cuboid) {
		return move(cuboid.getCopy());
	}

	public <T extends CuboidGeneric<T>> T reflect(T cuboid, List<T> structureContext) {
		T reflected = reflect(cuboid);
		T reflectedParent = findRefectedParent(cuboid, structureContext);
		if (reflectedParent == null)
			throw new Avuilder4jRuntimeException("Reflected parent not found");
		reflected.setParent(reflectedParent);
		return reflected;
	}

	public void removeMirroringAxes(Axis... axes) {
		if (axes != null) {
			for (Axis axis : axes) {
				getMirroringAxes().remove(axis);
			}
		}
	}

	public void setMirroringAxes(Axis... axes) {
		getMirroringAxes().clear();
		if (axes != null) {
			for (Axis axis : axes) {
				getMirroringAxes().add(axis);
			}
		}
	}

	public void setMirroringAxes(Set<Axis> activeAxes) { this.mirroringAxes = activeAxes; }

	public void setMirroringPoint(Point mirroringPoint) { this.mirroringPoint = mirroringPoint; }

	public void validateActiveAxes() {
		if (getMirroringAxes().isEmpty()) {
			throw new Avuilder4jRuntimeException("There are not active mirroring axes");
		}
	}

}
