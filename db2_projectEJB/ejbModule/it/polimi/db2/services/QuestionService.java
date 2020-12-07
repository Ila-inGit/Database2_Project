package it.polimi.db2.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.entities.Product;
import it.polimi.db2.entities.Question;

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
}
