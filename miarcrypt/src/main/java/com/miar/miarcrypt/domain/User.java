package com.miar.miarcrypt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="miaruser", 
       uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class User {

	@Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
	@SequenceGenerator(name="pk_sequence_miaruser",sequenceName="miaruser_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence_miaruser")
    @Column(name="id", nullable=false, unique=true, length=11)
	private long id;
	
	@Column(name="name", length=20, nullable=false)
	private String name ;
	
	@Column(name="surname", length=20, nullable=false)
	private String surname ;
	
	@Column(name="login", length=20, nullable=false)
	private String login ;
	
	@Column(name="password", length=20, nullable=false)
	private String password ;
	
	@Column(name="email", length=20, nullable=false)
	private String email ;
	
	@Column(name="creationtime", nullable=false)
	private Date creationTime ;
	
	//@Column(name="active", nullable=false)
	//private boolean userConfirmed ;	
	
	@Column(name="idConfirm", nullable=false, length=11)
	private long idConfirm;
	
	@Column(name="active", nullable=false)
	private char active ;
	
	
	public User() {
		super();
	}


	
	public long getIdConfirm() {
		return idConfirm;
	}

	public void setIdConfirm(long idConfirm) {
		this.idConfirm = idConfirm;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Date getCreationTime() {
		return creationTime;
	}



	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}



	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public char getActive() {
		return active;
	}



	public void setActive(char active) {
		this.active = active;
	}



	public boolean isUserConfirmed() {
		boolean isUserConfirmed = ('1' == getActive()) ? true : false;
		return isUserConfirmed;
	}


/*
	public void setUserConfirmed(boolean userConfirmed) {
		boolean isUserConfirmed = ('1' == getActive()) ? true : false;
		userConfirmed = isUserConfirmed;
	}
*/

	
}
