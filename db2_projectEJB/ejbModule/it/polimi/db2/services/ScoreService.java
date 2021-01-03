package it.polimi.db2.services;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.entities.Product;
import it.polimi.db2.entities.Score;
import it.polimi.db2.entities.User;

@Stateless
public class ScoreService {

	@PersistenceContext(unitName = "db2_project")
	private EntityManager em;

	public ScoreService() {
	}
	
	// take user from cache ,i.e. from entities of the persistence context(if i understood well)
	public List<Score> findScoresByUser(int userId) {
		User user = em.find(User.class, userId);
		List<Score> scores = user.getScores();
		return scores;
	}

	// no cache
	public List<Score> findScoreByUserNoCache(int userId) {
		List<Score> scores = em.createQuery("Select s from Score s where s.user.id = :userId ", Score.class)
				.setHint("javax.persistence.cache.storeMode", "REFRESH").setParameter("userId", userId).getResultList();

		return scores;
	}
	
//
//	public List<Score> findScoresByProd(int prodId) {
//		Product prod = em.find(Product.class, prodId);
//		List<Score> scores = prod.getScores();
//		return scores;
//
//	}

	// no cache
	public List<Score> findScoreByProdNoCache(int prodId) {
		List<Score> scores = em.createQuery("Select s from Score s where s.prod.id = :prodId ", Score.class)
				.setHint("javax.persistence.cache.storeMode", "REFRESH").setParameter("prodId", prodId).getResultList();

		return scores;
	}
	
	
	
	public void createScore(int userId, int prodId, int points) {
		User user = em.find(User.class, userId);
		Product prod = em.find(Product.class, prodId);
		
		Score score = new Score(user,prod,points);
		// for debugging: let's check if mission is managed
		System.out.println("Method createScore");
		System.out.println("Is score object managed?  " + em.contains(score));
		
		// both side of the relation is updated
		user.addScore(score);
		//prod.addScore(score);
		em.persist(user);
		em.persist(prod);
	}
	
	public Map<String, String> createScoreBoard() {

		List<Object[]> scores = em.createNamedQuery("Score.createScoreBoard").getResultList();
		Map<String, String> map=new LinkedHashMap<String, String>();
		
		
		for (Object[] result : scores) {
	        map.put(result[1].toString(), result[2].toString());
	    }
		

	    return map;
	}
	
	
}
