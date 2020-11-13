import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

import avuilder4j.data.loaders.AvuilderMemory

class MemoryLoad {
	
	private String dbFilePath = "db/avorion-db.sqlite3";

	public static List<String> getScriptList(){
		List<String> scriptNames = new ArrayList<>();

		//--- Add your scripts here
		scriptNames.add("MemoryLoad");
		scriptNames.add("Test1");

		return scriptNames;
	}
	
	public static void loadScripts(String scriptsFolder) {
		List<String> scriptNames = getScriptList();
		GroovyClassLoader groovyLoader = new GroovyClassLoader();

		Map<String, GroovyObject> scripts = new HashMap<>();
		for (String scriptName : scriptNames) {
			Class scriptClass = groovyLoader.parseClass(new File(scriptsFolder+scriptName+".groovy"));
			scripts.put(scriptName, scriptClass);
		}
		AvuilderMemory.setScripts(scripts);
		groovyLoader.close();
	}
	
	protected Connection getDBConnection() throws SQLException {
		Connection connection = null;
		
//		connection = DriverManager.getConnection("jdbc:sqlite:" + getClass().getClassLoader().getResource(dbFilePath)); // TODO limpiar
//		System.out.println(getClass().getClassLoader().getResource(dbFilePath));
		connection = DriverManager.getConnection("jdbc:sqlite:" + "src/main/resources/db/avorion-db.sqlite3");
		return connection;
	}


	
	public static void loadTables() {
		
		
		
	}
	
	
}
