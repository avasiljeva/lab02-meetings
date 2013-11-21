package lv.lu.meetings.xml;

/**
 * XML data as String to be used during tests.
 */
public final class XmlTestData {
	
	// version of users.xml file used in tests
	public static final String USERS_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
		+ "<users>"
			+ "<user>"
				+ "<first-name>John</first-name>"
				+ "<last-name>White</last-name>"
				+ "<username>jwhite</username>"
				+ "<password>jwhitepass</password>"
			+ "</user>"
			+ "<user>"
				+ "<first-name>David</first-name>"
				+ "<last-name>Green</last-name>"
				+ "<username>dgreen</username>"
				+ "<password>dgreenpass</password>"
			+ "</user>"
			+ "<user>"
				+ "<first-name>Ann</first-name>"
				+ "<last-name>Blue</last-name>"
				+ "<username>ablue</username>"
				+ "<password>abluepass</password>"
			+ "</user>"
		+ "</users>";	

	private XmlTestData() {
	}
}