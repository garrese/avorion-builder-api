package avuilder.core.utils;
import avuilder.core.entities.avorion.Material;
import avuilder.core.entities.avorion.Mats;

public class Tests {

	public static void main(String[] args) {


		Mats mf = new Mats();
		Material m = mf.getAllMaterials().get(1);
		System.out.println(m);
	
		
	}

}
