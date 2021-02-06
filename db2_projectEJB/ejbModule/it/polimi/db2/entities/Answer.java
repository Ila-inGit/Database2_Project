package it.polimi.db2.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "answers", schema = "db2_project")
@NamedQueries({@NamedQuery(name = "Answer.findByQuest", query = "Select a FROM Answer a WHERE a.quest.id = :questId"),
    @NamedQuery(name = "Answer.findByUser", query = "Select a FROM Answer a WHERE a.user.id = :userId")})
public class Answer implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    // bi-directional relationship with Question
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "questionID")
	private Question quest;
	
	// bi-directional relationship with User
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

    @Column(name = "body", nullable = false)
	private String body;

    public Answer() {}

    public Answer(Question quest, User user, String body){
        this.quest = quest;
        this.user = user;
        this.body = body;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public Question getQuest() {return quest;}

	public void setQuest(Question quest) {this.quest = quest;}

	public User getUser() {return user;}

	public void setUser(User user) {this.user = user;}

    public String getBody() {return body;}

    public void setBody(String body) {this.body = body;}
}