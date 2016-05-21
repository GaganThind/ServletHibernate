package in.gagan.common.util;

import org.apache.log4j.Logger;

public class LoggingUtil {
	private static Logger logger = Logger.getLogger("ServletHibernate");
	
	public static Logger getLoggerInsance(){
		return logger;
	}
}
