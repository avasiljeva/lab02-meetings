package lv.lu.meetings.impl.users;

import java.io.FileInputStream;
import java.io.InputStream;

import lv.lu.meetings.interfaces.XmlDataProvider;

import org.springframework.stereotype.Component;

/**
 * Loads users data from local XML file
 */
@Component("usersDataProvider")
public class UsersDataProvider implements XmlDataProvider {

    // property value is specified in Spring configuration file
    // see /lab01-meetings/src/main/resources/applicationContext.xml
    private String usersFilePath;		// path to users.xml file

    /**
     * InputStream has to be closed after usage.
     */
    public InputStream loadXml() {
        return loadXml(usersFilePath);
    }

    private InputStream loadXml(String filePath) {
        try {
            InputStream is = new FileInputStream(filePath);
            return is;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setUsersFilePath(String usersFilePath) {
        this.usersFilePath = usersFilePath;
    }
}
