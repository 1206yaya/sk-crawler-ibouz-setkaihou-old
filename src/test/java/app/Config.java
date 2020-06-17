package app;

import java.io.File;
import java.nio.file.Paths;

public class Config {
	public final static String PROJ_DIR = Paths.get(System.getProperty("user.dir")).toString();
	private static final String RESOURCES_DIR = Paths.get(PROJ_DIR, "src", "main", "resources").toString();
	private static final String CHROMEDRIVER_DIR = Paths.get(RESOURCES_DIR, "chromedriver").toString();
	public static final String CHROMEDRIVER_FOR_WIN_PATH = Paths.get(CHROMEDRIVER_DIR, "chromedriver72.exe").toString();
	public static final String CHROMEDRIVER_FOR_LINUX_PATH = Paths.get(CHROMEDRIVER_DIR, "chromedriver").toString();
	
	public static  final File TITLE_FILE = Paths.get(PROJ_DIR, "TITLES.txt").toFile();
	public static  final File SETTING_FILE = Paths.get(PROJ_DIR, "SETTINGS.txt").toFile();
}
