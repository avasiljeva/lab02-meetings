********************************
*** LAB 2 - Meetings Portal  ***
********************************

This is a sample project for LAB 2 in "Web Programming" 2013 course at the University of Latvia.

*******************************
***   Application modules   ***
*******************************

Application consists of two modules:

    - "lab02-meetings-core" : contains Java classes - domain model, services, Spring MVC classes
							: is packaged in a JAR file, which is later placed into Web module's folder "/WEB-INF/lib/"

    - "lab02-meetings-portal": Web module
                          : "\lab02-meetings-portal\src\main\webapp" contains Web application resources (jsp, css, images etc)


*******************************
***       Preparation       ***
*******************************

1) Create "meetings" database and user in MySQL using a script
\lab02-meetings\lab02-meetings-core\src\main\resources\init.sql

If database and user already exist (you have created it during the first lab),
then better drop schema (delete all tables) to avoid incompatibility.
Otherwise you may experience persistence problems during execution!
Necessary tables will be created on application start up.

2) To modify projects in Eclipse execute "mvn eclipse:eclipse" respectively from
"lab02-meetings-core" and "lab02-meetings-portal".
In Eclipse delete main "lab02-meetings" root project (do not delete contents) and import sub-projects into Eclipse separately.

3) Run Java program lv.lu.meetings.UsersImportTool (you have to be familiar with this program from the first lab).
Program will import users data into the database.

4) Execute "mvn -Dmaven.test.skip=true install" from \lab02-meetings

*******************************
***       Packaging         ***
*******************************
To build a WAR file execute "mvn package -Dmaven.test.skip=true" from the root folder "lab02-meetings".
Application WAR file will be created in "\lab02-meetings\lab02-meetings-portal\target".
Deploy "lab02-meetings-portal.war" on application server (Jetty, Tomcat, JBoss or any other) and run application.
Application was tested on Jetty integrated with Maven.

*******************************
***  Running with Jetty     ***
*******************************

Jetty is a web server integrated with Maven, which can be simply launched from command line.
To rebuild application and start Jetty simply run \lab02-meetings\run_jetty.bat
To start with remote debugging enabled run \lab02-meetings\run_jetty_debug.bat

These scripts consist of two steps, which can be executed separately:

1) To package "lab02-meetings-core" in JAR file and install it into local Maven repository:
Go to \lab02-meetings\lab02-meetings-core\ and execute 
	mvn install
You have to perform this step each time when you change Java code of configuration in "lab02-meetings-core" and want these changes to appear in packaged web application.

2) To deploy lab02-meetings-portal.war and start Jetty (without re-packaging "lab02-meetings-core"):
Go to  \lab02-meetings\lab02-meetings-portal\ and execute
	mvn clean package jetty:run

*******************************
***  Launching application  ***
*******************************

Application can be accessed by opening the following URL in a browser: 
http://localhost:8080/meetings/

For login use any user present \lab02-meetings\lab02-meetings-core\src\main\resources\data\users.xml
E.g. mblack/mblack.
Or you may create your own user directly in database.

*******************************
***  Selenium tests         ***
*******************************

Install Selenium IDE (Firefox plugin), available at http://seleniumhq.org/download/
In Selenium IDE select: File > Open Test Suite... > \lab02-meetings\lab02-meetings-portal\src\main\webapp\tests\meetings-suite\TestSuite.html
Play suite tests.

***************************************
*** Integrating your LAB 1 solution ***
***************************************

For sure you will need to update "lab02-meetings-core" content with a
certain part of your solution for the first lab - domain model classes that you have made persistent entities, services etc.
Do it, but be careful, this may require additional tuning.

******************************
***   Remote debugging     ***
******************************

To run Jetty with remote debugging mode enabled execute: run_jetty_debug.bat

For a tip on how to debug server-side code see
http://files-ante-lv.googlecode.com/svn/trunk/Training.WebProgramming.Masters2013/presentations/Lekcija10 - 03_Server-Side Debugging_2013_11_07.ppt

****************************
*** Problems & Questions ***
****************************

If you experience any problem or observe any strangeness or have any question,
please don't delay it, write e-mail or ask during a lecture!

*********************
*** GOOD LUCK !!! ***
*********************