package it.polimi.db2.services;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import it.polimi.db2.entities.Answer;
import it.polimi.db2.entities.ExpLvl;
import it.polimi.db2.entities.Gender;
import it.polimi.db2.entities.Product;
import it.polimi.db2.entities.Question;
import it.polimi.db2.entities.StatisticAnswer;
import it.polimi.db2.entities.User;

@Stateful
public class QuestionnaireService {
	@PersistenceContext(unitName = "db2_project", type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	private int currentPage = 0;
	private List<Answer> answersList = null;
	private StatisticAnswer statAnswer;
	
	/**
	 * Method to find all the questions about the product of the day
	 * @param prodId product ID
	 * @return list with all the questions about the product or null if there are no questions
	 */
	//it is the first method that it will be called
	public List<Question> findQuestionsOfTheProduct (int prodId) {
		List<Question> results = em.createNamedQuery("Question.findByProd", Question.class).setParameter("prodId", prodId).getResultList();
		if (results.isEmpty())
			return null;
		return results;
	}
	
	public void marketingAnswers (int[] questionIds, int userId, String[] answersBody) {
		for (int i = 0; i <= answersBody.length - 1; i++) {
			Answer answer = new Answer();
			Question question = em.find(Question.class, questionIds[i]);
			User user = em.find(User.class, userId);

			answer.setBody(answersBody[i]);
			answer.setQuest(question);
			answer.setUser(user);

			answersList.add(answer);
		}
		//Ottengo le risposte delle domande sul prodotto e me le memorizzo
	}
	
	public void statisticAnswer (int prodId, int userId, String gender, int age, String expLvl) {
		Gender userGender = null;
		ExpLvl level = null;
		User user = em.find(User.class, userId);
		Product prod = em.find(Product.class, prodId);
		
		if(gender.equals("female"))
			userGender = Gender.female;
		else if (gender.equals("male"))
			userGender = Gender.male;
		
		if(expLvl.equals("low"))
			level = ExpLvl.low;
		else if (expLvl.equals("medium"))
			level = ExpLvl.medium;
		else if (expLvl.equals("high"))
			level = ExpLvl.high;
		
		statAnswer.setProd(prod);
		statAnswer.setUser(user);
		statAnswer.setAge(age);
		statAnswer.setExpLvl(level);
		statAnswer.setGender(userGender);
		//Ottengo le risposte statistiche e le memorizzo
	}
	
	public void submit () {
		for (Answer an : answersList) {
			em.persist(an);
		}
		em.persist(statAnswer);
		//I dati memorizzati, li "pusho" sul database
	}
	
	@Remove
	public void remove() {}
}
