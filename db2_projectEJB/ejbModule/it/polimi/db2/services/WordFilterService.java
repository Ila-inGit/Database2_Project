package it.polimi.db2.services;

import it.polimi.db2.entities.*;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class WordFilterService {
	@PersistenceContext(unitName="db2_project")
	private EntityManager em;
	
	
	/**
	 * Get all not allowed words saved in the database
	 * @return
	 */
	public List<ForbiddenWord> getNotAllowedWords(){
		return em.createNamedQuery("ForbiddenWord.findAll", ForbiddenWord.class).getResultList();
	}
}
