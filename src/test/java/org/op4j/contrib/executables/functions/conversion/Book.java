package org.op4j.contrib.executables.functions.conversion;

import java.util.Calendar;
import java.util.List;

public class Book {

	private Integer id;
 	
	private String title;
	private String summary;
	private transient String content;
	
	private Calendar published;
	private List<Editorial> editorials;

	public Book() {
		super();
		
	}

	public Book(Integer id, String title, String summary, String content,
			Calendar published, List<Editorial> editorials) {
		super();
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.content = content;
		this.published = published;
		this.editorials = editorials;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Calendar getPublished() {
		return this.published;
	}

	public void setPublished(Calendar published) {
		this.published = published;
	}

	public List<Editorial> getEditorials() {
		return this.editorials;
	}

	public void setEditorials(List<Editorial> editorials) {
		this.editorials = editorials;
	}

	@Override
	public String toString() {
		return "Book [editorials=" + this.editorials + ", id=" + this.id
				+ ", published=" + this.published + ", summary=" + this.summary
				+ ", title=" + this.title + "]";
	}
	
}
