package it.polimi.db2.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Products")
@NamedQueries({
	@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p"),
	@NamedQuery(name="Product.today", query="SELECT p FROM Product p WHERE p.displayDate = CURRENT_DATE")
})
public class Product implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="name")
	private String name;
	
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name="image")
	private byte[] photo;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="highlightDate")
	private Date displayDate;
	
	
	//bi-directional relationship with Question
	@OneToMany(mappedBy = "prodId", fetch = FetchType.EAGER,  cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH }, orphanRemoval = true )
	private List<Question> questions;
	
	//bi-directional relationship with StatisticAnswer
	@OneToMany(mappedBy = "prod", fetch = FetchType.EAGER,  cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH }, orphanRemoval = true )
	private List<StatisticAnswer> statAns;
	
	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy = "prod", fetch = FetchType.EAGER,  cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH }, orphanRemoval = true )
	private List<Review> reviews;
	
	//bi-directional many-to-one association to Score
	@OneToMany(mappedBy = "prod", fetch = FetchType.EAGER,  cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH }, orphanRemoval = true )
	private List<Score> scores;
	
	//bi-directional many-to-one association to Score
		@OneToMany(mappedBy = "prod", fetch = FetchType.EAGER,  cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
				CascadeType.REFRESH }, orphanRemoval = true )
		private List<QuestionaireLog> qLog;

	public List<Review> getReviews() {
		return reviews;
	}


	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}


	public List<Score> getScores() {
		return scores;
	}


	public void setScores(List<Score> scores) {
		this.scores = scores;
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public byte[] getPhoto() {
		return photo;
	}


	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}


	public Date getDisplayDate() {
		return displayDate;
	}


	public void setDisplayDate(Date displayDate) {
		this.displayDate = displayDate;
	}
	
	public void addReview(Review review) {
		getReviews().add(review);
		review.setProd(this);
	}
	
	
	public void removeReview(Review review) {
		getReviews().remove(review);
	}
	
	public void addScore(Score score) {
		getScores().add(score);
		score.setProd(this);
	}
	
	public void removeScore(Score score) {
		getScores().remove(score);
	}
}
