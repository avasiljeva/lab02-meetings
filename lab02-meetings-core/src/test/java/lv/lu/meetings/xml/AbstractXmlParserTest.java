package lv.lu.meetings.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import lv.lu.meetings.base.BaseTest;
import lv.lu.meetings.domain.jpa.User;
import lv.lu.meetings.interfaces.XmlParser;

import org.junit.Test;

/**
 * An abstract test case for testing XmlParser implementations.
 * The actual tested implementation is set in a subclass.
 */
public abstract class AbstractXmlParserTest extends BaseTest {

	private XmlParser parser;

	@Test
	public void testParseUsers() throws UnsupportedEncodingException 
	{
		List<User> users = parser.parseUsers(
				new ByteArrayInputStream(XmlTestData.USERS_XML.getBytes("UTF-8")));
		
		assertNotNull("Users list is NULL", users);
		assertEquals("Wrong number of people extracted from XML", 3, users.size());
		
		User user1 = users.get(0);
		assertEquals("Wrong user1 username", "jwhite", user1.getUsername());
		assertEquals("Wrong user1 password", "jwhitepass", user1.getPassword());
		assertEquals("Wrong user1 first name", "John", user1.getFirstName());
		assertEquals("Wrong user1 last name", "White", user1.getLastName());
		
		User user2 = users.get(1);
		assertEquals("Wrong user2 username", "dgreen", user2.getUsername());
		assertEquals("Wrong user2 password", "dgreenpass", user2.getPassword());
		assertEquals("Wrong user2 first name", "David", user2.getFirstName());
		assertEquals("Wrong user2 last name", "Green", user2.getLastName());
		
		User user3 = users.get(2);
		assertEquals("Wrong user3 username", "ablue", user3.getUsername());
		assertEquals("Wrong user3 password", "abluepass", user3.getPassword());
		assertEquals("Wrong user3 first name", "Ann", user3.getFirstName());
		assertEquals("Wrong user3 last name", "Blue", user3.getLastName());
	}

	protected void setParser(XmlParser parser) {
		this.parser = parser;
	}

	protected XmlParser getParser() {
		return parser;
	}
}
