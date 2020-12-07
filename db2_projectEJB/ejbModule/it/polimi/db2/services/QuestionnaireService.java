package it.polimi.db2.services;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import it.polimi.db2.entities.Question;

@Stateful
public class QuestionnaireService {
	@PersistenceContext(unitName = "db2_project", type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	private int currentPage = 0;
	
	public QuestionnaireService() {}
	
	/**
	 * Method to find all the questions about the product of the day
	 * @param prodId product ID
	 * @return list with all the questions about the product or null if there are no questions
	 */
	public List<Question> findQuestionsOfTheProduct (int prodId) {
		List<Question> results = em.createNamedQuery("Question.findByProd", Question.class).setParameter("prodId", prodId).getResultList();
		if (results.isEmpty())
			return null;
		return results;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	//TODO next sarebbe presente solo alla prima pagina, mentre previous, cancel, submit alla seconda
	//perciò forse semplificare sotto
	public void next() {
		currentPage++; 
		//if (currentPage > maxPage) {
			//currentPage = (int) maxPage;
		//}
	}

	public void previous() {
		currentPage--;
		if (currentPage < 0) {
			currentPage = 0;
		}
	}
	@Remove
	public void remove() {}
}
