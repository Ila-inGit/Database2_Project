package it.polimi.db2.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.entities.Product;
import it.polimi.db2.entities.Review;
import it.polimi.db2.entities.User;

public class ReviewsService {

	@PersistenceContext(unitName = "db2_project")
	private EntityManager em;

	public ReviewsService() {
	}

	// take user from cache ,i.e. from entities of the persistence context(if i
	// understood well)
	public List<Review> findReviewsByUser(int userId) {
		User user = em.find(User.class, userId);
		List<Review> reviews = user.getReviews();
		return reviews;

	}

	// no cache
	public List<Review> findReviewsByUserNoCache(int userId) {
		List<Review> reviews = em.createQuery("Select r from Review r where r.user.id = :userId ", Review.class)
				.setHint("javax.persistence.cache.storeMode", "REFRESH").setParameter("userId", userId).getResultList();

		return reviews;
	}
	

	public List<Review> findReviewsByProd(int prodId) {
		Product prod = em.find(Product.class, prodId);
		List<Review> reviews = prod.getReviews();
		return reviews;

	}

	// no cache
	public List<Review> findReviewsByProdNoCache(int prodId) {
		List<Review> reviews = em.createQuery("Select r from Review r where r.prod.id = :prodId ", Review.class)
				.setHint("javax.persistence.cache.storeMode", "REFRESH").setParameter("prodId", prodId).getResultList();

		return reviews;
	}

	public void createReview(int userId, int prodId, String body) {
		User user = em.find(User.class, userId);
		Product prod = em.find(Product.class, prodId);
		Review review = new Review(prod, user, body);
		// for debugging: let's check if mission is managed
		System.out.println("Method createReview");
		System.out.println("Is review object managed?  " + em.contains(review));

		// both side of the relation is updated
		user.addReview(review);
		/// TODO
		// prod.addReview(review);
		em.persist(user);
		// em.persist(prod);
	}

}
