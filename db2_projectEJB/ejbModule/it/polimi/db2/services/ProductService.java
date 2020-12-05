package it.polimi.db2.services;


import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.entities.*;

@Stateless
public class ProductService {
	@PersistenceContext(unitName="db2_project")
	private EntityManager em;
	
	public Product getTodayProduct() {
		
		var res = em.createNamedQuery("Product.today", Product.class).getResultList();
		if(res.size() >= 1)
			return res.get(0);  // always return first product of the day
								//TODO: create trigger in db to check duplicate prod of day
		return null;
			
	}
	
}
