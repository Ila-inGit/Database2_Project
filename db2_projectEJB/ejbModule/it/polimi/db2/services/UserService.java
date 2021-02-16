package it.polimi.db2.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import it.polimi.db2.entities.User;

@Stateless
public class UserService {
	
	@PersistenceContext(unitName = "db2_project")
	private EntityManager em;
	
	public UserService() {
	}

	/**
	 * method to access with user's credentials
	 * 
	 * @param usrn userName of the new user
	 * @param pwd password of the new user
	 * @return the new user 
	 * @throws NonUniqueResultException there is already a User with those credentials
	 */
	
	public User checkCredentials(String usrn, String pwd) throws NonUniqueResultException {
		List<User> uList = null;
		try {
			uList = em.createNamedQuery("User.checkCredentials", User.class).setParameter(1, usrn).setParameter(2, pwd)
					.getResultList();
		} catch (PersistenceException e) {
			System.out.print("Credetials not found");
		}
		if (uList == null || uList.isEmpty())
			return null;
		else if (uList.size() == 1)
			return uList.get(0);
		throw new NonUniqueResultException("More than one user registered with same credentials");

	}
	
	/**
	 * Method for register a new user to the database
	 * @param email
	 * @param usrn userName of the new user
	 * @param pwd password of the new user
	 * @return true if the registration is made
	 */
	public boolean registerUser(String email, String usrn, String pwd){
		
		List<User> users = em.createNamedQuery("User.findByEmail", User.class).setParameter("email", email).getResultList();
		
		
		if(users.isEmpty()) {
			User newUser= new User();
			newUser.setEmail(email);
			newUser.setUserName(usrn);
			newUser.setPassword(pwd);
			em.persist(newUser);
			return true;
		}else {
			return false;
		}
				
	}	
}
