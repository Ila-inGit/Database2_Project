package it.polimi.db2.entities;


import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "reviews", schema = "db2_project")
@NamedQueries({ @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r"),
	@NamedQuery(name = "Review.findByUser", query = "Select r FROM Review r WHERE r.user.id = :userId "),
	@NamedQuery(name = "Review.findByProd", query = "Select r FROM Review r WHERE r.prod.id = :prodId ") })

public class Review implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "prodId")
	private Product prod;
	
	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@Column(name = "body", nullable = false)
	private String body;

	
	
	public Review() {
		super();
	}

	public Review(Product prod, User user, String body) {
		this.prod = prod;
		this.user = user;
		this.body = body;
	}

	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	
}

