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
	
	
	/**
	 * Check if the message contains one or more filtered words
	 * @param message message to check
	 * @return true if massage contains one or more not allowed words
	 */
	public boolean ContainsNotAllowedWord(String message) {
		/* performance goes brrrrrr */
		for(var  w : getNotAllowedWords()) {
			if(message.contains(w.getText()))
				return true;
		}
		
		return false;
	}
}
