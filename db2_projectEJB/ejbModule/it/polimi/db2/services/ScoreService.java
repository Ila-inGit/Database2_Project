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
	
	
	public Map<String, String> createScoreBoard() {

		List<Object[]> scores = em.createNamedQuery("Score.createScoreBoard").getResultList();
		Map<String, String> map=new LinkedHashMap<String, String>();
		
		
		for (Object[] result : scores) {
	        map.put(result[1].toString(), result[2].toString());
	    }
		

	    return map;
	}
	
	
}
