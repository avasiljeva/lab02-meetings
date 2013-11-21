package lv.lu.meetings;

import lv.lu.meetings.domain.DataModelTest;
import lv.lu.meetings.impl.CommonJpaDAOImplTest;
import lv.lu.meetings.xml.DomXmlParserTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Main test suite. Run as JUnit to execute all tests.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {
		DomXmlParserTest.class,
		CommonJpaDAOImplTest.class,
		DataModelTest.class})

public class MeetingsTestSuite {
}