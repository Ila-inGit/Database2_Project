package it.polimi.db2.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "questions", schema = "db2_project")
@NamedQueries({
		@NamedQuery(name = "Question.findByProd", query = "Select q FROM Question q WHERE q.prodId.id = :prodId"),
		@NamedQuery(name = "Question.findById", query = "Select q FROM Question q WHERE q.id = :id")})
public class Question implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    //bi-directional relationship with Product
    @ManyToOne
    @JoinColumn(name = "prodId")
    private Product prodId;

    //bi-directional relationship with Answer
    @OneToMany (mappedBy = "quest", cascade = {CascadeType.PERSIST, CascadeType.REMOVE,
                CascadeType.REFRESH}, orphanRemoval = true)
    private List<Answer> answers;

    @Column(name = "body", nullable = false)
	private String body;

    public Question() {}

    public Question(Product prod, String body){
        this.prodId = prod;
        this.body = body;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public Product getProd() {return prodId;}

	public void setProd(Product prod) {this.prodId = prod;}

    public String getBody() {return body;}

    public void setBody(String body) {this.body = body;}
    
    public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public void addAnswer(Answer answer) {
		getAnswers().add(answer);
		answer.setQuest(this);
	}
	
	public void removeAnswer(Answer answer) {
		getAnswers().remove(answer);
	}
}