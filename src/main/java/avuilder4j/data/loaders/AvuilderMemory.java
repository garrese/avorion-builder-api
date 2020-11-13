package avuilder4j.data.loaders;

import java.util.HashMap;
import java.util.Map;

import groovy.lang.GroovyObject;

public class AvuilderMemory {

	private static Map<String, GroovyObject> scripts = new HashMap<>();
	private static Map<String, Data> tables = new HashMap<>();

	public static Object executeScript(String scriptName, String scriptMethod, Object... args) {
		return scripts.get(scriptName).invokeMethod(scriptMethod, args);
	}

	public static GroovyObject getScript(String scriptName) {
		return scripts.get(scriptName);
	}

	public static Map<String, GroovyObject> getScripts() { return scripts; }

	public static Data getTable(String tableName) {
		return tables.get(tableName);
	}

	public static Map<String, Data> getTables() { return tables; }

	public static void setScripts(Map<String, GroovyObject> scripts) { AvuilderMemory.scripts = scripts; }

	public static void setTables(Map<String, Data> tables) { AvuilderMemory.tables = tables; }

}
