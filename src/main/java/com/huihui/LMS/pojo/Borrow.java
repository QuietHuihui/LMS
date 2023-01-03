package com.huihui.LMS.pojo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@NamedQuery(name="Borrow.getAll",query = "select b from Borrow b")

@NamedQuery(name="Borrow.getBorrow",query="select b from Borrow b where b.user.id=:id ")

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="borrow")
public class Borrow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bo_id")
    @SequenceGenerator(name = "bo_id", initialValue = 1, allocationSize = 1, sequenceName = "borrow_id")
	@Column(name="id")
	private Integer id;
	
	@Column(name="borrowedFrom")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date borrowedFrom;
	
	@Column(name="borrowedTo")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date borrowedTo;
	
	@Column(name="returnDay")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date returnDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_fk",nullable=false)
	User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_fk",nullable=false)
	Book book;

	public Borrow(Integer id, Date borrowedFrom, Date borrowedTo, Date returnDate, User user, Book book) {
		this.id = id;
		this.borrowedFrom = borrowedFrom;
		this.borrowedTo = borrowedTo;
		this.returnDate = returnDate;
		this.user = user;
		this.book = book;
	}

	public Borrow() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBorrowedFrom() {
		return borrowedFrom;
	}

	public void setBorrowedFrom(Date borrowedFrom) {
		this.borrowedFrom = borrowedFrom;
	}

	public Date getBorrowedTo() {
		return borrowedTo;
	}

	public void setBorrowedTo(Date borrowedTo) {
		this.borrowedTo = borrowedTo;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "Borrow [id=" + id + ", borrowedFrom=" + borrowedFrom + ", borrowedTo=" + borrowedTo + ", returnDate="
				+ returnDate + ", user=" + user + ", book=" + book + "]";
	}
	
}
