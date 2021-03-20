package com.uniovi.entities;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set; //A collection that contains no duplicate elements

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;
	private String password;
	@Transient // propiedad que no se almacena e la tabla.
	private String passwordConfirm;
	private double saldo;
	private String role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Offer> offers;
	
	@OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
	private Set<Offer> ownedOffers;
	
	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public Set<Offer> getOwnedOffers() {
		return ownedOffers;
	}

	public void setOwnedOffers(Set<Offer> ownedOffers) {
		this.ownedOffers = ownedOffers;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public User(String email, String name, String lastName) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
	}

	public User() {
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}
	
	public double getSaldo() {
		return saldo;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public void setSaldo(Double i) {
		this.saldo=i;
		
	}

	public void buyOffer(Offer offer) {
		setSaldo(getSaldo()-offer.getPrice());
		
		
	}

}
