package it.polimi.db2.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Products")
@NamedQueries({
	@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p"),
	@NamedQuery(name="Product.findById", query="SELECT p FROM Product p WHERE p.id = :id"),
	@NamedQuery(name="Product.today", query="SELECT p FROM Product p WHERE p.displayDate = CURRENT_DATE"),
	@NamedQuery(name="Product.upcoming", query="SELECT p FROM Product p where p.displayDate >= CURRENT_DATE order by p.displayDate"),
	@NamedQuery(name="Product.old", query="SELECT p FROM Product p where p.displayDate < CURRENT_DATE order by p.displayDate"),
	@NamedQuery(name="Product.ofDate", query="SELECT p FROM Product p WHERE p.displayDate = :date")
})
public class Product implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	@OneToMany(mappedBy = "prodId", fetch = FetchType.EAGER,  cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Question> questions;
	
	//bi-directional relationship with StatisticAnswer
	@OneToMany(mappedBy = "prod", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	private List<StatisticAnswer> statAns;
	
	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy = "prod", fetch = FetchType.EAGER,  cascade = {CascadeType.PERSIST, CascadeType.REFRESH} )
	private List<Review> reviews;
	
	//bi-directional many-to-one association to Score
		@OneToMany(mappedBy = "prod", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH} )
		private List<QuestionnaireLog> qLog;

	public List<Review> getReviews() {
		return reviews;
	}


	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}


	public List<Question> getQuestions() {
		return questions;
	}
	
	public void clearQuestions()
	{
		questions.clear();
	}

	public void setQuestion(List<Question> question) {
		this.questions = question;
	}
	
	public List<StatisticAnswer> getStatAnswers() {
		return statAns;
	}

	public void setStatAnswer(List<StatisticAnswer> statAns) {
		this.statAns = statAns;
	}
	
	public List<QuestionnaireLog> getQLogs() {
		return qLog;
	}

	public void setQLogs(List<QuestionnaireLog> qLog) {
		this.qLog = qLog;
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
	
	public void addQuestion(Question question) {
		getQuestions().add(question);
		question.setProd(this);
	}
	
	public void removeQuestion(Question question) {
		getQuestions().remove(question);
	}
	
	public void addStatAnswer(StatisticAnswer statAnswer) {
		getStatAnswers().add(statAnswer);
		statAnswer.setProd(this);
	}
	
	public void removeStatAnswer(StatisticAnswer statAnswer) {
		getStatAnswers().remove(statAnswer);
	}
	
	public void addQLogs(QuestionnaireLog qLog) {
		getQLogs().add(qLog);
		qLog.setProduct(this);
	}
	
	public void removeQLogs(QuestionnaireLog qLog) {
		getQLogs().remove(qLog);
	}
}
