package it.polimi.db2.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="BlackList")
@NamedQuery(name="ForbiddenWord.findAll", query="SELECT w FROM ForbiddenWord w")
public class ForbiddenWord implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id", unique = true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="word", nullable=false)
	private String text;

	
	public int getId()
	{
		return id;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
