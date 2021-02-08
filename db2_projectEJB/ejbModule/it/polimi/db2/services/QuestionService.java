package it.polimi.db2.services;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.time.DateUtils;

import it.polimi.db2.entities.Product;
import it.polimi.db2.entities.Question;
import it.polimi.db2.utils.FormatUtils;

@Stateless
public class QuestionService {
	@PersistenceContext(unitName = "db2_project")
	private EntityManager em;
	
	public QuestionService() {
	}
	
	/**
	 * Method to create a question for a product
	 * @param productId product ID
	 * @param body question about the product
	 */
	public void createQuestion(int productId, String body) {
		//TODO devo controllare che ci sia la stessa identica domanda per quel prodotto?
		Product prod = em.find(Product.class, productId);
		Question quest = new Question(prod, body);
		prod.addQuestion(quest);
		em.persist(prod);
	}
	
	/**
	 * Method to delete question by id
	 * @param id of the question that we want to remove
	 * @return true if the question is deleted
	 */
	public boolean deleteQuestion(int id) {
		try 
		{
			Question question = em.createNamedQuery("Question.findById", Question.class).setParameter("id", id).getSingleResult();
			em.remove(question);
			return true;
		} catch(NoResultException e) {
			return false;
		}
	}
	
	/**
	 * Delete all questions associated to a product
	 * Return false if product not found or unable to delete questions of future products
	 * @param productID
	 * @return
	 */
	public boolean deleteAllQuestions(int productID)
	{
		try
		{
			Product prod = em.find(Product.class, productID);
			
			Date today = new Date();
			
			//prevent delete of future products that have a question associated
			if(prod.getDisplayDate().after(today) || DateUtils.isSameDay(prod.getDisplayDate(), today))
				return false;
			
			for(var q: prod.getQuestions())
			{
				em.remove(q);
			}
			prod.clearQuestions();
			return true;
			
		}
		catch(NoResultException e)
		{
			return false;
		}
		
	}
}
