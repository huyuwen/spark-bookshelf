package spark.bookshelf;

import java.util.Date;

public class Book {

	private String id;

	private String author;

	private String title;

	private Date date;

	public Book(String id, String author, String title) {
		this.id = id;
		this.author = author;
		this.title = title;
		this.date = new Date();
	}

	public Date getDate() {
		return this.date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
