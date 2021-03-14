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
	@Override
	public String toString() {
		return "Offer [id=" + id + ", title=" + title + ", description=" + description + ", date=" + date + ", price="
				+ price + "]";
	}

	@Id
	@GeneratedValue
	private long id;
	
	private String title;
	
	private String description;
	
	private LocalDate date;
	
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
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
		this.date=date;
		
	}

	public void setUser(User user) {
		this.user=user;
		
	}


	
	
}

