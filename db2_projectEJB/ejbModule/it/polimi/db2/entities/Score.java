package it.polimi.db2.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "score", schema = "db2_project")
@NamedQueries({ @NamedQuery(name = "Score.findAll", query = "SELECT s FROM Score s"),
	@NamedQuery(name = "Score.findByUser", query = "SELECT s FROM Score s WHERE s.user.id = :userId "),
	@NamedQuery(name = "Score.createScoreBoard", query = "SELECT s.user.id, s.user.userName, s.points FROM Score s where s.points > 0 ORDER BY s.points DESC")}
)
//SELECT s.user AS us, sum(s.points) AS pointsSum FROM Score AS s GROUP BY s.user ORDER BY pointsSum 
public class Score implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@Column(name = "points", nullable = false)
	private int points;

	public Score() {

	}
	

	public Score(User user, int points) {
		super();
		this.user = user;
		this.points = points;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPoints() {
		return points;
	}

}
