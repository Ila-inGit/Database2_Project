package it.polimi.db2.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "score", schema = "db2_project")
@NamedQueries({ @NamedQuery(name = "Score.findAll", query = "SELECT s FROM Score s"),
	@NamedQuery(name = "Score.findByUser", query = "Select s FROM Score s WHERE s.user.id = :userId "),
	@NamedQuery(name = "Score.findByProd", query = "Select s FROM Score s WHERE s.prod.id = :prodId ")})

public class Score implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	// bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "prodId")
	private Product prod;
	
	@Column(name = "points", nullable = false)
	private int points;

	public Score() {

	}
	

	public Score(User user, Product prod, int points) {
		super();
		this.user = user;
		this.prod = prod;
		this.points = points;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
