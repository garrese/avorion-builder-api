import avuilder4j.design.DesignExporter;
import avuilder4j.design.DesignIndexer;
import avuilder4j.entities.game.Block;
import avuilder4j.entities.spatial.util.Lengths;
import avuilder4j.error.Avuilder4jException;
import avuilder4j.values.Spatial;
import avuilder4j.values.Types;

class Test {

	@org.junit.jupiter.api.Test
	void test2() {
		DesignIndexer i = new DesignIndexer();
		System.out.println(i);

		Block b1 = i.createBlock();

		Block b2 = i.createBlock();
		b2.getAxisZ().setLength(5.0);
		b2.attachTo(b1, Spatial.FACE_WALL_ZU);

		Block b3 = i.createBlock();
		b3.attachTo(b2, Spatial.FACE_WALL_ZU);

		Block b4 = i.createBlock();
		b4.getAxisZ().setLength(5.0);
		b4.attachTo(b3, Spatial.FACE_WALL_ZU);

		Block b5 = i.createBlock();
		b5.attachTo(b4, Spatial.FACE_WALL_ZU);

		Block b6 = i.createBlock();
		b6.getAxisZ().setLength(5.0);
		b6.attachTo(b5, Spatial.FACE_WALL_ZU);

		b1.setType(Types.GLOW);
		b3.setType(Types.GLOW);
		b5.setType(Types.GLOW);
		b1.setColor("00000000");
		b3.setColor("88000000");
		b5.setColor("ff000000");

		b2.setType(Types.THRUSTER);
		b4.setType(Types.THRUSTER);
		b6.setType(Types.THRUSTER);
		b2.setColor("ffff88ff");
		b4.setColor("88ff88ff");
		b6.setColor("00ff88ff");

		DesignExporter e = new DesignExporter();
		try {
			e.export(i.getStructure(), "brigthness");
			System.out.println("exported");
		} catch (Avuilder4jException e1) {
			e1.printStackTrace();
		}
	}

	@org.junit.jupiter.api.Test
	void test() {
		DesignIndexer b = new DesignIndexer();
		System.out.println(b);

		Block root = b.createBlock();

		Block front = b.createBlock();
		front.attachTo(root, Spatial.FACE_WALL_ZU);

		Block rigth = b.createBlock();
		rigth.attachTo(root, Spatial.FACE_WALL_XU);

		System.out.println("base:");
		System.out.println(b.getDesignReport());
		System.out.println();

		Block mid = b.createBlock();
		mid.setTags(" ");
		mid.attachTo(rigth, Spatial.FACE_WALL_ZU);
		mid.setLengths(new Lengths(3, 4, 5), Spatial.FACE_WALL_XL, Spatial.FACE_WALL_ZL);
		System.out.println("mid:");
		System.out.println(b.getDesignReport());
		System.out.println();

		for (Block k : b.getStructure()) {
			k.setType(Types.GLOW);
		}
		root.setColor("ff00ff00");
		front.setColor("8800ff00");
		mid.setColor("0000ff00");

		DesignExporter e = new DesignExporter();
		try {
			e.export(b.getStructure(), "brigthness");
			System.out.println("exported");
		} catch (Avuilder4jException e1) {
			e1.printStackTrace();
		}
	}

}
