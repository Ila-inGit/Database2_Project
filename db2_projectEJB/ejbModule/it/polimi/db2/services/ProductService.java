package it.polimi.db2.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
			return em.find(Product.class, prodId);
			
		} catch(NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Check if the user has answered to the questions of the product of the day
	 * @param userId
	 * @return true only if the user has answered, otherwise return false even if there is no product or question
	 */
	public boolean userHasAnsweredToTodayQuestions(int userId)
	{
		try 
		{
			int ans_cnt = em.createNamedQuery("User.hasFilledTodayQuestions").setParameter(1, userId).getResultList().size();
			if(ans_cnt > 0)
				return true;
		}
		catch(Exception e) { }
		
		return false;
	}
	
	
	public Map<User, Boolean> getUsersQuestionnaire(int prodId){
		
		Product pr;
		///true if he submitted the questionnaire
		Map<User, Boolean> usersOfQuest = new HashMap<>();
		
		try 
		{
			pr = em.find(Product.class, prodId);
			
		} catch(NoResultException e) {
			pr = new Product();
		}
		
		Question question= pr.getQuestions().get(0);
		
		List<Answer> allAnswers;
		
		
		if(question != null) {
			allAnswers = question.getAnswers();
			
			for( Answer a : allAnswers) {
				usersOfQuest.put(a.getUser(), true );
			}
			
			List<QuestionnaireLog> qLogs ;
			
			qLogs = pr.getQLogs();
			
			for( QuestionnaireLog q : qLogs) {
				if(!usersOfQuest.containsKey(q.getUser()))
					usersOfQuest.put(q.getUser(), false );
			}
			
			return usersOfQuest;
			
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
