package com.uniovi.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "offer")
public class Offer {
	@Id
	@GeneratedValue
	private long id;

	private String title;
	private LocalDate date;
	private String description;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "buyer_id")
	private User buyer;
	
	@Override
	public String toString() {
		return "Offer [id=" + id + ", title=" + title + ", description=" + description + ", date=" + date + ", price="
				+ price + "]";
	}

	

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getDate() {
		return date;
	}

	public double getPrice() {
		return price;
	}

	

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	private double price;

	

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public User getUser() {
		return user;
	}

	public Offer() {

	}

	public Offer(long id, String title, String description, LocalDate date, double price, User user) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.price = price;
		this.user = user;
	}

	public Offer(String title, String description, LocalDate date, double price, User user) {
		super();
		this.title = title;
		this.description = description;
		this.date = date;
		this.price = price;
		this.user = user;
	}

	public void setDate(LocalDate date) {
		this.date = date;

	}

	public void setUser(User user) {
		this.user = user;

	}

}
