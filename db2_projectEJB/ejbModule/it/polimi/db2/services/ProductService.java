package it.polimi.db2.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.time.DateUtils;

import it.polimi.db2.entities.*;
import it.polimi.db2.exceptions.NotAvailableDateException;

@Stateless
public class ProductService {
	@PersistenceContext(unitName="db2_project")
	private EntityManager em;
	
	/**
	 * Get todays product of the day, can be null
	 */
	public Product getTodayProduct() {
		
		var res = em.createNamedQuery("Product.today", Product.class).setHint("javax.persistence.cache.storeMode", "REFRESH").getResultList();
		if(res.size() >= 1)
			return res.get(0);  // always return first product of the day
								//TODO: create trigger in db to check duplicate prod of day
		return null;
			
	}
	
	/**
	 * Create new product in the database
	 * 
	 * @param name name of product
	 * @param image raw image data, can be null
	 * @param displayDate date when this product should be displayed, leave null for today
	 * @throws NotAvailableDateException if displayDate is not available
	 */
	public void createProduct(String name, byte[] image, Date displayDate) throws NotAvailableDateException {
		
		//TODO: escape name?
		
		var today = new Date();
		
		if(displayDate == null) displayDate = today;
		
		
		// can't create products in the past
		if(displayDate.before(today) && !DateUtils.isSameDay(today, displayDate))
			throw new NotAvailableDateException("Selected date is in the past.");
	
		
		// check if there are other products in the selected date
		try {
			em.createNamedQuery("Product.ofDate")
			.setHint("javax.persistence.cache.storeMode", "REFRESH")
			.setParameter("date", displayDate, TemporalType.DATE)
			.getSingleResult();
			// here we found a result otherwise the above function throws an exception
			// what a trash design decision, a null value could be A WAY BETTER SOLUTION
			// but this is java and you must pollute your code with trash exceptions
			throw new NotAvailableDateException("Selected date is already used by another product.");
		}catch(NoResultException e) {}
		
		// create product
		var prod = new Product();
		prod.setName(name);
		prod.setPhoto(image.length > 0 ? image : null);
		prod.setDisplayDate(displayDate); // set default date to today if missing
		
		em.persist(prod);
	}
	
	/**
	 * Get current and upcoming products of the day starting from today
	 */
	public List<Product> getNextProducts() {
		try 
		{
			return em.createNamedQuery("Product.upcoming", Product.class).setHint("javax.persistence.cache.storeMode", "REFRESH").getResultList();
		
		} catch(NoResultException e) {
			return new ArrayList<Product>();
		}
	}
	
	/**
	 * Get products  
	 */
	public List<Product> getOldProducts() {
		try 
		{
			return em.createNamedQuery("Product.old", Product.class).setHint("javax.persistence.cache.storeMode", "REFRESH").getResultList();
			
		} catch(NoResultException e) {
			return new ArrayList<Product>();
		}
	}
	
	
	public Product getProductById(int prodId) {
		try 
		{
			return em.createNamedQuery("Product.findById", Product.class).setParameter("id", prodId ).setHint("javax.persistence.cache.storeMode", "REFRESH").getSingleResult();
			
		} catch(NoResultException e) {
			return new Product();
		}
	}
	
	
	
	public List<Answer> getResultForQuestion(int questId, int prodId){
		
		Product pr;
		try 
		{
			pr = em.createNamedQuery("Product.findById", Product.class).setParameter("id", prodId ).setHint("javax.persistence.cache.storeMode", "REFRESH").getSingleResult();
			
		} catch(NoResultException e) {
			pr = new Product();
		}
		
		Question question= pr.getQuestions().stream().filter((q) -> q.getId() == questId).findFirst().orElse(null);
		
		if(question != null) {
			return question.getAnswers();
		}
		
		return new ArrayList<Answer>(); 
		
	}
	
	public List<User> getUsersOfDeletedQuestionnaire(int prodId){
		
		Product pr;
		try 
		{
			pr = em.createNamedQuery("Product.findById", Product.class).setParameter("id", prodId ).setHint("javax.persistence.cache.storeMode", "REFRESH").getSingleResult();
			
		} catch(NoResultException e) {
			pr = new Product();
		}
		
		Question question= pr.getQuestions().get(0);
		
		List<Answer> allAnswers;
		
		
		if(question != null) {
			allAnswers = question.getAnswers();
			List<User> allUsers = new ArrayList<User>();
			List<User> usersInLogs = new ArrayList<User>();
			
			for( Answer a : allAnswers) {
				allUsers.add(a.getUser());
			}
			
			List<QuestionnaireLog> qLogs ;
			
			qLogs = pr.getQLogs();
			
			for( QuestionnaireLog q : qLogs) {
				if(!usersInLogs.contains(q.getUser()))
					usersInLogs.add(q.getUser());
			}
			
			for( User u : allUsers) {
				usersInLogs.remove(u);
			}
			return usersInLogs;
			
		}
		return null;
	}
	
	
	
	
	
	/**
	 * Delete product by id
	 * @param id id of the produt that should be removed
	 * @return true if product is deleted
	 */
	public boolean deleteProduct(int id) {
		
		try 
		{
			
			Product prod = em.find(Product.class, id);
			
			Date today = new Date();
			
			//prevent delete of future products that have a question associated
			if(prod.getDisplayDate().after(today) || DateUtils.isSameDay(prod.getDisplayDate(), today))
				return false;
			
			em.remove(prod);
			return true;
			
		} catch(NoResultException e) {
			return false;
		}
		
	}
	
}
