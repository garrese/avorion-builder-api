package avuilder4j.data.loaders;

import java.io.File;

import avuilder4j.error.AvuilderException;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

public class AvuilderMemoryLoader {

	public static final String LOADING_SCRIPT = "MemoryLoad.groovy";
	public static final String LOADING_SCRIPTS_METHOD = "loadScripts";
	private static String loadingScriptsFolder = "scripts/";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void invokeScriptsLoader() throws AvuilderException {
		try {
			GroovyClassLoader groovyLoader = new GroovyClassLoader();
			Class calcClass = groovyLoader.parseClass(new File(loadingScriptsFolder + LOADING_SCRIPT));
			GroovyObject script = (GroovyObject) calcClass.getDeclaredConstructor().newInstance();
			script.invokeMethod(LOADING_SCRIPTS_METHOD, new Object[] { loadingScriptsFolder });
			groovyLoader.close();
		} catch (Exception e) {
			throw new AvuilderException(e);
		}
	}

	public String getLoadingScriptsFolder() { return loadingScriptsFolder; }

	public void setLoadingScriptsFolder(String loadingScriptsFolder) { AvuilderMemoryLoader.loadingScriptsFolder = loadingScriptsFolder; }

	public static void loadAll() {

	}
}
