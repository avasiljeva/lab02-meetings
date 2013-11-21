package lv.lu.meetings.impl;

import java.util.Collection;
import java.util.List;

import lv.lu.meetings.domain.jpa.PersistentEntity;
import lv.lu.meetings.domain.jpa.PersistentEntityType;
import lv.lu.meetings.domain.jpa.User;
import lv.lu.meetings.interfaces.CommonJpaDAO;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA (Java Persistence API) data access object implementation.
 * For JavaDocs see {@link CommonJpaDAO} interface.
 */
@Component("jpaDAO")
@Transactional
public class CommonJpaDAOImpl extends AbstractDAOImpl implements CommonJpaDAO {
	
	public void save(PersistentEntity object) {
		if (object.getId() == null) {
			getEntityManager().persist(object);
		} else {
			getEntityManager().merge(object);
		}
	}
	
	public void saveAll(Collection<? extends PersistentEntity> objects) {
		for (PersistentEntity object: objects){
			save(object);
		}
	}

	public <T extends PersistentEntity> T getById(Class<T> clazz, Long id) {
		return getEntityManager().find(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends PersistentEntity> List<T> findAll(Class<T> clazz) {
		String entity = clazz.getSimpleName();
		return getEntityManager().createQuery("select e FROM " + entity + " e").getResultList();
	}

	public void delete(PersistentEntity object) {
		getEntityManager().remove(object);
	}
	
	public void cleanupDB(){
		for (PersistentEntityType clazz: PersistentEntityType.values()){
			List<? extends PersistentEntity> objects = findAll(clazz.getObjectClass());
			for (PersistentEntity o: objects){
				delete(o);
			}
		}
		System.out.println("Database is cleaned");
	}

	@Override
	public void cleanupUsers() {
		// TODO [task]: after making all entities persistent do not forget to cleanup also all related entities
//		List<? extends PersistentEntity> objects = findAll(Attendance.class);
//		for (PersistentEntity o: objects){
//			delete(o);
//		}
//		objects = findAll(Invite.class);
//		for (PersistentEntity o: objects){
//			delete(o);
//		}
		List<? extends PersistentEntity> objects = findAll(User.class);
		for (PersistentEntity o: objects){
			delete(o);
		}
		System.out.println("Users and related data deleted from database");
	}

	@Override
	public void cleanupVenues() {
		for (PersistentEntityType clazz: PersistentEntityType.values()){
			if (clazz != PersistentEntityType.USER){
				List<? extends PersistentEntity> objects = findAll(clazz.getObjectClass());
				for (PersistentEntity o: objects){
					delete(o);
				}
			}
		}
		System.out.println("Venues and related data deleted from database");
	}
}
