package avuilder4j.util.java;

import java.sql.SQLException;

public interface SqlFunction<T, R> {
	public R run(T t) throws SQLException;
}
