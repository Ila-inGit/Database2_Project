package it.polimi.db2.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "questions", schema = "db2_project")
@NamedQueries({@NamedQuery(name = "Question.findByProd", query = "Select q FROM Question q WHERE q.prod.id = :prodId")})
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
    @OneToOne (mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.REMOVE,
                CascadeType.REFRESH}, orphanRemoval = true)
    private Answer ans;

    @Column(name = "body", nullable = false)
	private char body[];

    public Question() {}

    public Question(Product prod, char[] body){
        this.prodId = prod;
        this.body = body;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public Product getProd() {return prodId;}

	public void setProd(Product prod) {this.prodId = prod;}

    public char[] getBody() {return body;}

    public void setBody(char[] body) {this.body = body;}
}