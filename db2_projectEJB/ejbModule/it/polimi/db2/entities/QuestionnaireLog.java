package it.polimi.db2.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="QuestionnaireLogs")
public class QuestionnaireLog implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="prodId")
	private Product prod;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date openDate;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return prod;
	}

	public void setProduct(Product prod) {
		this.prod = prod;
	}

	public int getId() {
		return id;
	}

	public Date getOpenDate() {
		return openDate;
	}
	
	public void setOpenDate(Date time) {
		openDate = time;
	}
}
