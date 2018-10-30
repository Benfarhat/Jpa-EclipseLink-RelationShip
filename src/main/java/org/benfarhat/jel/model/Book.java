package org.benfarhat.jel.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Book implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private LocalDate publication;
	private Lang language;
	private Genre genre;
	private Author author;
	private int numberOfPage;
	
	public Book() {
		super();
	}
	
	public Book(String title, Author author, Lang language, Genre genre, int numberOfPage, LocalDate publication) {
		super();
		this.title = title;
		this.setAuthor(author);
		this.language = language;
		this.genre = genre;
		this.numberOfPage = numberOfPage;
		this.publication = publication;
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", publication=" + publication + ", language=" + language.getTitle()
				+ ", genre=" + genre.getTitle() + ", author=" + author.getFirstname() + ", numberOfPage=" + numberOfPage + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getPublication() {
		return publication;
	}
	public void setPublication(LocalDate publication) {
		this.publication = publication;
	}
	@ManyToOne
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@ManyToOne	
	public Lang getLanguage() {
		return language;
	}
	public void setLanguage(Lang language) {
		this.language = language;
	}
	@ManyToOne	
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public int getNumberOfPage() {
		return numberOfPage;
	}
	public void setNumberOfPage(int numberOfPage) {
		this.numberOfPage = numberOfPage;
	}

	
}
