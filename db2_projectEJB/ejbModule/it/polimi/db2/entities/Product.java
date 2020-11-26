package it.polimi.db2.entities;

import java.io.Serializable;
import java.util.Date;

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
	private byte[] photo;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="highlightDate")
	private Date displayDate;
	


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
		
}
