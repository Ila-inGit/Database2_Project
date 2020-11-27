package it.polimi.db2.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="QuestionaireLogs")
public class QuestionaireLog implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade={CascadeType.REFRESH, CascadeType.REMOVE})
	@JoinColumn(name="id")
	private User user;
	
	@ManyToOne(cascade={CascadeType.REFRESH, CascadeType.REMOVE})
	@JoinColumn(name="id")
	private Product product;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date openDate;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public Date getOpenDate() {
		return openDate;
	}
	

}
