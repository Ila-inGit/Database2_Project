package it.polimi.db2.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "statisticAnswers", schema = "db2_project")
@NamedQueries({@NamedQuery(name = "StatisticAnswer.findByProd", query = "Select s FROM StatisticAnswer s WHERE s.prod.id = :prodId"),
    @NamedQuery(name = "StatisticAnswer.findByUser", query = "Select s FROM StatisticAnswer s WHERE s.user.id = :userId")})
public class StatisticAnswer implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    //bi-directional relationship with Product
	@ManyToOne
	@JoinColumn(name = "prod")
	private Product prod;
	
	//bi-directional relationship with User
	@ManyToOne
	@JoinColumn(name = "user")
	private User user;
 
    @Enumerated(EnumType.STRING)
	private Gender gender;

    @Enumerated(EnumType.STRING)
	private ExpLvl expLvl; 

    @Column(name = "age", nullable = false)
	private int age;

    public StatisticAnswer() {}

    public StatisticAnswer(Product prod, User user, Gender gender, ExpLvl expLvl, int age){
        this.prod = prod;
        this.user = user;
        this.gender = gender;
        this.expLvl = expLvl;
        this.age = age;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public Product getProd() {return prod;}

	public void setProd(Product prod) {this.prod = prod;}

	public User getUser() {return user;}

	public void setUser(User user) {this.user = user;}

    public Gender getGender() {return gender;}

    public void setGender(Gender gender) {this.gender = gender;}

    public ExpLvl getExpLvl() {return expLvl;}

    public void setExpLvl(ExpLvl expLvl) {this.expLvl = expLvl;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}
}