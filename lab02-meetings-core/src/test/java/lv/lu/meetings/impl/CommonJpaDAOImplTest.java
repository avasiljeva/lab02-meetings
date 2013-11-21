package lv.lu.meetings.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import lv.lu.meetings.base.JpaBaseTest;
import lv.lu.meetings.domain.jpa.PersistentEntity;
import lv.lu.meetings.domain.jpa.PersistentEntityType;

import org.junit.Test;

/**
 * This test ensures that CommonJpaDAOImpl operations are working well 
 * for all application persistent entities.
 * This test employs PersistentEntityType registry which lists all persistent entities. 
 * @see lv.lu.meetings.domain.jpa.PersistentEntityType
 * 
 * Use this JUnit for testing O/R mapping and JPA annotations.
 * 
 * TODO [explanation]
 * All tests in this class except 'testAllForUser()' are initially failing, because it is assumed 
 * that all classes defined in {@link PersistentEntityType} registry are persistent entities (marked with @Entity annotation).
 * lv.lu.meetings.domain.User class is already persistent, that's why 'testAllForUser()' passes successfully.
 * 
 * TODO [task]
 * Your task is to make all tests work!
 */
public class CommonJpaDAOImplTest extends JpaBaseTest {

	@Test
	public void testAllForUser(){
		Class<? extends PersistentEntity> userClass = PersistentEntityType.USER.getObjectClass();
		testSaveAndFindAllIteration(userClass);
		testSaveAndGetByIdIteration(userClass);
		testSaveAllIteration(userClass);
		testDeleteIteration(userClass);
	}
	
	@Test
	public void testAllForVenue(){
	    Class<? extends PersistentEntity> venueClass = PersistentEntityType.VENUE.getObjectClass();
	    testSaveAndFindAllIteration(venueClass);
	    testSaveAndGetByIdIteration(venueClass);
	    testSaveAllIteration(venueClass);
	    testDeleteIteration(venueClass);
	}
	
	@Test
	public void testAllForInvite(){
	    Class<? extends PersistentEntity> inviteClass = PersistentEntityType.INVITE.getObjectClass();
	    testSaveAndFindAllIteration(inviteClass);
	    testSaveAndGetByIdIteration(inviteClass);
	    testSaveAllIteration(inviteClass);
	    testDeleteIteration(inviteClass);
	}
	
	@Test
	public void testAllForAttendance(){
	    Class<? extends PersistentEntity> attendanceClass = PersistentEntityType.ATTENDANCE.getObjectClass();
	    testSaveAndFindAllIteration(attendanceClass);
	    testSaveAndGetByIdIteration(attendanceClass);
	    testSaveAllIteration(attendanceClass);
	    testDeleteIteration(attendanceClass);
	}
	
	/**
	 * For each persistent entity type tests if number of entries found
	 * by findAll() operation increases by one after new entry is added by save()
	 */
	@Test
	public void  testSaveAndFindAll() {
		for (PersistentEntityType objectType: PersistentEntityType.values()){	
			if (objectType.getSubClasses() != null){
				// this is for UserActivity case, where exactly sub-classes have to be tested
				for (Class<? extends PersistentEntity> c: objectType.getSubClasses()){
					testSaveAndFindAllIteration(c);					
				}
			} else{
				testSaveAndFindAllIteration(objectType.getObjectClass());
			}
		}
	}
	
	/**
	 * Performs test iteration for a certain class.
	 * 1) Finds all class instances using findAll()
	 * 2) Creates new object using save()
	 * 3) Executes findAll() once again an checks that number of entries increased by one 
	 */
	private void testSaveAndFindAllIteration(Class<? extends PersistentEntity> clazz)
	{	
		List<? extends  PersistentEntity> allBeforeSave = jpaDAO.findAll(clazz);
		int beforeSave = allBeforeSave.size();
		
		createAndSaveObject(clazz);
		
		List<? extends  PersistentEntity> allAfterSave = jpaDAO.findAll(clazz);
		assertEquals("Number of " + clazz.getCanonicalName() + " instances in DB didn't increase after save", 
				beforeSave + 1, allAfterSave.size());		
	}
	
	/**
	 * For each persistent entity type tests if 
	 * just created object can be retrieved from DB by getById() operation
	 */
	@Test
	public void  testSaveAndGetById() {
		for (PersistentEntityType objectType: PersistentEntityType.values()){	
			if (objectType.getSubClasses() != null){
				// this is for UserActivity case, where exactly sub-classes have to be tested
				for (Class<? extends PersistentEntity> c: objectType.getSubClasses()){
					testSaveAndGetByIdIteration(c);					
				}
			} else{
				testSaveAndGetByIdIteration(objectType.getObjectClass());
			}
		}
	}
	
	/**
	 * Performs test iteration for a certain class.
	 * Creates new object and tries to retrieve it using getById() operation
	 */
	private void testSaveAndGetByIdIteration(Class<? extends PersistentEntity> clazz)
	{
		PersistentEntity object = createAndSaveObject(clazz);
		PersistentEntity fromDB = jpaDAO.getById(clazz, object.getId());
		assertNotNull("Just created instance of " + clazz.getCanonicalName() + 
				" is not retrieved from DB", fromDB);	
	}
	
	
	/**
	 * For each persistent entity type tests saveAll() operation.
	 */
	@Test
	public void  testSaveAll() {
		for (PersistentEntityType objectType: PersistentEntityType.values()){	
			if (objectType.getSubClasses() != null){
				// this is for UserActivity case, where exactly sub-classes have to be tested
				for (Class<? extends PersistentEntity> c: objectType.getSubClasses()){
					testSaveAllIteration(c);					
				}
			} else{
				testSaveAllIteration(objectType.getObjectClass());
			}
		}
	}
	
	/**
	 * Performs test iteration for a certain class.
	 * 1) Searches for all object of certain class in DB
	 * 2) Creates three new objects
	 * 3) Searched for all in DB and checks that number increased by 3
	 * 4) Retrieves each new object by id
	 */
	private void testSaveAllIteration(Class<? extends PersistentEntity> clazz)
	{	
		List<? extends  PersistentEntity> allBeforeSave = jpaDAO.findAll(clazz);
		int beforeSave = allBeforeSave.size();
		
		PersistentEntity object1 = createAndSaveObject(clazz);
		PersistentEntity object2 = createAndSaveObject(clazz);
		PersistentEntity object3 = createAndSaveObject(clazz);
		
		List<? extends  PersistentEntity> allAfterSave = jpaDAO.findAll(clazz);
		assertEquals("Number of " + clazz.getCanonicalName() + " instances in DB didn't increase by 3 after save", 
				beforeSave + 3, allAfterSave.size());
		
		// retrieve each object separately
		PersistentEntity fromDB1 = jpaDAO.getById(clazz, object1.getId());
		assertNotNull("[1] Just created instance of " + clazz.getCanonicalName() + 
				" is not retrieved from DB", fromDB1);
		PersistentEntity fromDB2 = jpaDAO.getById(clazz, object2.getId());
		assertNotNull("[2] Just created instance of " + clazz.getCanonicalName() + 
				" is not retrieved from DB", fromDB2);
		PersistentEntity fromDB3 = jpaDAO.getById(clazz, object3.getId());
		assertNotNull("[3] Just created instance of " + clazz.getCanonicalName() + 
				" is not retrieved from DB", fromDB3);
	}
	
	/**
	 * For each persistent entity type tests delete() operation.
	 */
	@Test
	public void  testDelete() {
		for (PersistentEntityType objectType: PersistentEntityType.values()){	
			if (objectType.getSubClasses() != null){
				// this is for UserActivity case, where exactly sub-classes have to be tested
				for (Class<? extends PersistentEntity> c: objectType.getSubClasses()){
					testDeleteIteration(c);					
				}
			} else{
				testDeleteIteration(objectType.getObjectClass());
			}
		}
	}
	
	/**
	 * Performs test iteration for a certain class.
	 * 1) Creates new object
	 * 2) Checks that it can be retrieved from DB by id
	 * 3) Deletes object
	 * 4) Checks that search by id returns null
	 */
	private void testDeleteIteration(Class<? extends PersistentEntity> clazz)
	{
		PersistentEntity object = createAndSaveObject(clazz);
		
		PersistentEntity fromDB = jpaDAO.getById(clazz, object.getId());
		assertNotNull("Just created instance of " + clazz.getCanonicalName() + 
				" is not retrieved from DB", fromDB);
		
		jpaDAO.delete(fromDB);
		
		fromDB = jpaDAO.getById(clazz, object.getId());
		assertNull("Test object was deleted, but still found by id", fromDB);
	}

	/**
	 * Creates an empty instance of certain class in DB.
	 */
	private PersistentEntity createAndSaveObject(Class<? extends PersistentEntity> clazz){
		PersistentEntity object = null;
		try {
			object = clazz.newInstance();
			jpaDAO.save(object);	
		} 
		catch (Exception e) {
			e.printStackTrace();
			fail("Failed to create instance of " + clazz.getCanonicalName());
		}
		return object;
	}
}