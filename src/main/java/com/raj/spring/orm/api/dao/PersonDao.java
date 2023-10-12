package com.raj.spring.orm.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.raj.spring.orm.api.model.Person;

import jakarta.persistence.criteria.CriteriaQuery;

@Repository
@Transactional
public class PersonDao {
	
	@Autowired
	private SessionFactory factory;
	
	public void savePerson(Person person) {
		getSession().persist(person);
	}
	
	public List<Person> getPersons(){
		CriteriaQuery<Person> cq = getSession().getCriteriaBuilder().createQuery(Person.class);
        cq.from(Person.class);
        List<Person> persons = getSession().createQuery(cq).getResultList();
        
        return persons;
	}
	
	private Session getSession() {
		Session session = factory.getCurrentSession();
		
		if(null == session) {
			session = factory.openSession();
		}
		
		return session;
	}
}
