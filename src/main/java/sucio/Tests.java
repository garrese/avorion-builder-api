package sucio;

import avuilder.core.entities.dimensional.Line3D;
import avuilder.core.entities.dimensional.Point;

public class Tests {

	public static void main(String[] args) {

//
//		Mats mf = new Mats();
//		Material m = mf.getAllMaterials().get(Mats.AVORION);
//		System.out.println(m);

//		Piece p = new Piece();
//		p.setLineX(new Line(-0.5,0.5));
//		p.setLineY(new Line(-0.5,0.5));
//		p.setLineZ(new Line(-0.5,0.5));
//		
//		System.out.println(p.getCenter());
//		System.out.println(p.getSideCenter(K.UX));
//		System.out.println(p.getSideCenter(K.LX));
//		System.out.println(p.getSideCenter(K.UY));
//		System.out.println(p.getSideCenter(K.LY));
//		System.out.println(p.getSideCenter(K.UZ));
//		System.out.println(p.getSideCenter(K.LZ));

		Line3D line = new Line3D(new Point(null, 0.0, 0.0), new Point(-1.0, -1.0, -1.0));

		System.out.println(line.getLength());
		System.out.println(line.getCenter());

	}

}
