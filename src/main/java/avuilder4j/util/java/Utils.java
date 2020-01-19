package avuilder4j.util.java;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class Utils {

	public static long getBytesFromList(List<? extends Object> list) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(baos);
		out.writeObject(list);
		out.close();
		return baos.toByteArray().length;
	}

}
