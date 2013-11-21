package lv.lu.meetings;

import lv.lu.meetings.interfaces.DataImportProcessor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Entry point into users data importing application.
 * Execute to import user profiles into database.
 * In default configuration loads user data from: 
 *      /lab01-meetings/src/main/resources/data/users.xml
 * 
 * You need to create database schema before running this program.
 * See /lab01-meetings/src/main/resources/init.sql
 * Tables will be created automatically by JPA engine.
 */
public class UsersImportTool {

	public static final String SPRING_CONFIG_FILE = "/applicationContext.xml";
	private static final String SPRING_BEAN = "usersProcessor";
	
	@SuppressWarnings("resource")
	public static void main(String[] args) 
	{
		log("Application started");
		
		// initialize Spring application context from configuration file
		ApplicationContext appCtxt = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);
		DataImportProcessor importProcessor = (DataImportProcessor)appCtxt.getBean(SPRING_BEAN);
		importProcessor.importData();
		
		log("Application execution successfully completed");
		System.exit(0);
	}
	
	private static void log(String message){
		System.out.println("[UsersImportTool] " + message);
	}
}
