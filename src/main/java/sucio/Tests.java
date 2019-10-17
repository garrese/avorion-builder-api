package sucio;

import avuilder.core.entities.dimensional.AxisLine;
import avuilder.core.entities.dimensional.Cuboid;
import avuilder.core.utils.K;

public class Tests {

	public static void main(String[] args) {

//
//		Mats mf = new Mats();
//		Material m = mf.getAllMaterials().get(Mats.AVORION);
//		System.out.println(m);

		Cuboid o = new Cuboid();
		o.setAxisX(new AxisLine(0.0, 1.0));
		o.setAxisY(new AxisLine(0.0, 1.0));
		o.setAxisZ(new AxisLine(0.0, 1.0));
//		
//		System.out.println(p.getCenter());
//		System.out.println(p.getSideCenter(K.UX));
//		System.out.println(p.getSideCenter(K.LX));
//		System.out.println(p.getSideCenter(K.UY));
//		System.out.println(p.getSideCenter(K.LY));
//		System.out.println(p.getSideCenter(K.UZ));
//		System.out.println(p.getSideCenter(K.LZ));

		// Line line = new Line(new Point(null, 0.0, 0.0), new Point(-1.0, -1.0, -1.0));
		// System.out.println(line.getLength());
		// System.out.println(line.getCenter());
		
		System.out.println(o.getFaceCenter(K.FACE_BASE));
		System.out.println(o.getFaceCenter(K.FACE_TOP));
		System.out.println(o.getFaceCenter(K.FACE_WALL_1));
		System.out.println(o.getFaceCenter(K.FACE_WALL_2));
		System.out.println(o.getFaceCenter(K.FACE_WALL_3));
		System.out.println(o.getFaceCenter(K.FACE_WALL_4));
		System.out.println();
		System.out.println(o.getCorner(K.CORNER_BASE_1));
		System.out.println(o.getCorner(K.CORNER_BASE_2));
		System.out.println(o.getCorner(K.CORNER_BASE_3));
		System.out.println(o.getCorner(K.CORNER_BASE_4));
		System.out.println(o.getCorner(K.CORNER_TOP_1));
		System.out.println(o.getCorner(K.CORNER_TOP_2));
		System.out.println(o.getCorner(K.CORNER_TOP_3));
		System.out.println(o.getCorner(K.CORNER_TOP_4));
		

	}

}
