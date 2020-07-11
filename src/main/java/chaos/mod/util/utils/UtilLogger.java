package chaos.mod.util.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilLogger {
	public static final Logger LOGGER = LogManager.getLogger();
	public static void info(String s) {
		LOGGER.info(s);
	}
	
	public static void info(Object obj) {
		LOGGER.info(obj);
	}
}
