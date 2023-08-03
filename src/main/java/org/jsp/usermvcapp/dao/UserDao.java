package org.jsp.usermvcapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.usermvcapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Repository
public class UserDao {
    @Autowired
	private EntityManager m;
	
	public User saveuser(User u) {
		EntityTransaction t=m.getTransaction();
		m.persist(u);
		t.begin();
		t.commit();
		return u;
	}
	public User updateuser(User u) {
		EntityTransaction t=m.getTransaction();
		m.merge(u);
		t.begin();
		t.commit();
		return u;
	}
	public User verifyuser(long phone,String password) {
		Query q=m.createQuery("select u from User u where u.phone=?1 and u.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (User)q.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
	}
	public User verifyuser(String email,String password) {
		Query q=m.createQuery("select u from User u where u.email=?1 and u.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (User)q.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
	}
	public User verifyuser(int id,String password) {
		Query q=m.createQuery("select u from User u where u.id=?1 and u.password=?2");
		q.setParameter(1, id);
		q.setParameter(2, password);
		try {
			return (User)q.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
	}
	public boolean deleteuser(int id) {
		User u=m.find(User.class,id);
		if(u!=null) {
			EntityTransaction t=m.getTransaction();
			m.remove(u);
			t.begin();
			t.commit();
			return true;
		}
		else {
			return false;
		}
	}
	public User finduser(int id) {
        return m.find(User.class, id);		
	}
}
