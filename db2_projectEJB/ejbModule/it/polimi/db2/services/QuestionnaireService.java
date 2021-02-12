package it.polimi.db2.services;

import java.util.ArrayList;
import java.util.Date;
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
import it.polimi.db2.entities.QuestionnaireLog;
import it.polimi.db2.entities.StatisticAnswer;
import it.polimi.db2.entities.User;

@Stateful
public class QuestionnaireService {
	
	@PersistenceContext(unitName = "db2_project", type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	private List<Answer> answersList = new ArrayList<Answer>();
	private StatisticAnswer statAnswer;
	
	private boolean displayStatisticsForm = false;
	
	/**
	 * Method to find all the questions about the product of the day
	 * @param prodId product ID
	 * @return list with all the questions about the product or null if there are no questions
	 */
	//it is the first method that it will be called
	public List<Question> findQuestionsOfTheProduct (int prodId) 
	{
		List<Question> results = em.createNamedQuery("Question.findByProd", Question.class).setParameter("prodId", prodId).getResultList();
		if (results.isEmpty())
			return null;
		return results;
	}
	
	/**
	 * Check if we should display the marketing questions or the statistical part of the poll
	 * @return true if statistics form should be displayed
	 */
	public boolean shouldDisplayStatisticsForm() 
	{
		return displayStatisticsForm;
	}
	
	
	public void setDisplayStatisticsForm(boolean show)
	{
		displayStatisticsForm = show;
	}
	
	
    /**
     * Insert a "user opened questionnaire at X" on the database
     * @param userId
     * @param prodId
     */
	public void createQuestionnaireLog(int userId, int prodId) 
	{
		User user = em.find(User.class, userId);
		Product prod = em.find(Product.class, prodId);
		QuestionnaireLog log = new QuestionnaireLog();
		
		log.setUser(user);
		log.setProduct(prod);
		log.setOpenDate( new Date());

		user.addQLog(log);
		prod.addQLogs(log);
		
		em.persist(user);
		em.persist(prod);
		em.persist(log);
	}
	
	/**
	 * Insert marketing answers into the bean
	 * @param questionIds
	 * @param userId
	 * @param answersBody
	 */
	public void addMarketingAnswers (int[] questionIds, int userId, String[] answersBody)
	{
		if (answersBody != null) 
		{
			for (int i = 0; i <= answersBody.length - 1; i++) 
			{
				Answer answer = new Answer();
				Question question = em.find(Question.class, questionIds[i]);
				User user = em.find(User.class, userId);

				answer.setBody(answersBody[i]);
				answer.setQuest(question);
				answer.setUser(user);

				answersList.add(answer);
			}
		}
		
		// move to next phase of questionnaire
		displayStatisticsForm = true;
	}
	
	/**
	 * Get current answers stored in this bean
	 * @return
	 */
	public List<Answer> getMarketingAnswers()
	{
		return answersList;
	}
	
	/**
	 * Insert statistic answers into the bean
	 * @param prodId
	 * @param userId
	 * @param gender
	 * @param age
	 * @param expLvl
	 */
	public void addStatisticAnswer (int prodId, int userId, String gender, int age, String expLvl)
	{
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
		
		// TODO: tirare un eccezione se valori invalidi
		
		statAnswer = new StatisticAnswer();
		
		statAnswer.setProd(prod);
		statAnswer.setUser(user);
		statAnswer.setAge(age);
		statAnswer.setExpLvl(level);
		statAnswer.setGender(userGender);
		//Ottengo le risposte statistiche e le memorizzo
		
	}
	
	/**
	 * Store answers in the database
	 */
	public void submit () 
	{
		for (Answer an : answersList) 
		{
			User user = an.getUser();
			Question question = an.getQuest();
			
			user.addAnswer(an);
			question.addAnswer(an);
			em.persist(an);
			//em.persist(user);
			//em.persist(question);
		}
		
		
		// statistical answers could be null because left empty
		if(statAnswer != null)
		{
		
			User user = statAnswer.getUser();
			Product prod = statAnswer.getProd();
			
			user.addStatAns(statAnswer);
			prod.addStatAnswer(statAnswer);
			
			//em.persist(user);
			//em.persist(prod);
			em.persist(statAnswer);
		}
	}
	
	/**
	 * Clear all current stored answers
	 */
	public void clearAnswers() {
		answersList.clear();
		displayStatisticsForm = false;
		statAnswer = null;
	}
	
	
	@Remove
	public void remove() {}
}
