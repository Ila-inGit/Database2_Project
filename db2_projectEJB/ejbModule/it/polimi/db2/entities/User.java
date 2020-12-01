package it.polimi.db2.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "users", schema = "db2_project")
@NamedQuery(name = "User.checkCredentials", query = "SELECT r FROM User r  WHERE r.userName = ?1 and r.password = ?2")

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="username", nullable=false)
	private String userName;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="isBlocked")
	private boolean isBlocked;
	
	@Column(name="isAdmin")
	private boolean isAdmin;
	
	//bi-directional relationship with Answer
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER,  cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
	private List<Answer> answers;
	
	//bi-directional relationship with StatisticAnswer
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER,  cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
	private List<StatisticAnswer> statAns;
	
	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER,  cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH }, orphanRemoval = true )
	private List<Review> reviews;
	
	//bi-directional many-to-one association to Score
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER,  cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH }, orphanRemoval = true )
	private List<Score> scores;
	
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER,  cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH }, orphanRemoval = true )
	private List<QuestionaireLog> qLog;
	

	
	public User() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

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

	
	public void addReview(Review review) {
		getReviews().add(review);
		review.setUser(this);
	}
	
	
	public void removeReview(Review review) {
		getReviews().remove(review);
	}
	
	public void addScore(Score score) {
		getScores().add(score);
		score.setUser(this);
	}
	
	public void removeScore(Score score) {
		getScores().remove(score);
	}
	
}







