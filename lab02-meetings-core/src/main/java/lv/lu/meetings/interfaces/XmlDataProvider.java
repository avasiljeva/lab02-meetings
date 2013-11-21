package lv.lu.meetings.interfaces;

import java.io.InputStream;

/**
 * Interface for loading XML files from some source
 */
public interface XmlDataProvider {

    /**
     * Load XML file from some source
     * 
     * @return input stream of XML file
     */
	public InputStream loadXml();
}
