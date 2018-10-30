package org.benfarhat.jel.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Author implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String surname;
	private String firstname;
	
	
	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Author(String surname, String firstname) {
		super();
		this.surname = surname;
		this.firstname = firstname;
	}


	@Override
	public String toString() {
		return "Author [id=" + id + ", surname=" + surname + ", firstname=" + firstname + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	
}
