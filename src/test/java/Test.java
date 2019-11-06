class Test {

//	@org.junit.jupiter.api.Test
//	void test() {
//		DesignIndexer b = new DesignIndexer();
//		System.out.println(b);
//
//		Block root = b.createBlock();
//
//		Block front = b.createBlock();
//		front.attachTo(root, Spatial.FACE_WALL_ZU);
//
//		Block rigth = b.createBlock();
//		rigth.attachTo(root, Spatial.FACE_WALL_XU);
//
//		System.out.println("base:");
//		System.out.println(b.getDesignReport());
//		System.out.println();
//
//		Block mid = b.createBlock();
//		mid.setTags(" ");
//		mid.attachTo(rigth, Spatial.FACE_WALL_ZU);
//		mid.setLengths(new Lengths(3, 4, 5), Spatial.FACE_WALL_XL, Spatial.FACE_WALL_ZL);
//		System.out.println("mid:");
//		System.out.println(b.getDesignReport());
//		System.out.println();
//
//		for (Block k : b.getStructure()) {
//			k.setType(Types.GLOW);
//		}
//		root.setColor("ff00ff00");
//		front.setColor("8800ff00");
//		mid.setColor("0000ff00");
//
//		DesignExporter e = new DesignExporter();
//		try {
//			e.export(b.getStructure(), "brigthness");
//			System.out.println("exported");
//		} catch (Avuilder4jException e1) {
//			e1.printStackTrace();
//		}
//	}

}
