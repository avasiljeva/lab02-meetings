package lv.lu.meetings;

import lv.lu.meetings.interfaces.DataImportProcessor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Entry point into a venues data importing application.
 * Run to import venues into database.
 * 
 * You need to create a database schema before running this program.
 * See \lab01-meetings\src\main\resources\init.sql
 * Tables will be created automatically by JPA engine.
 */
public class VenueImportTool {

	private static final String SPRING_CONFIG_FILE = "/applicationContext.xml";
	private static final String SPRING_BEAN = "venuesProcessor";
	
	@SuppressWarnings("resource")
	public static void main(String[] args) 
	{
		log("Application started");
		
		// initialize Spring application context from configuration file
		ApplicationContext appContext = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);
		DataImportProcessor importProcessor = (DataImportProcessor)appContext.getBean(SPRING_BEAN);
		importProcessor.importData();
	
		log("Application execution successfully completed");
		System.exit(0);
	}
	
	private static void log(String message){
		System.out.println("[VenueImportTool] " + message);
	}

}
