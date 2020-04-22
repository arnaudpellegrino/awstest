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
@Table(name="miarmessage", 
       uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class Message {

	@Id
	@SequenceGenerator(name="pk_sequence_miarmessage",sequenceName="miarmessage_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence_miarmessage")
    @Column(name="id", nullable=false, unique=true, length=11)
	private long id;
	
	@Column(name="iduser", length=11, nullable=false)
	private long idUser ;
	
	@Column(name="message", nullable=false)
	private byte[] message ;
	
	@Column(name="key", nullable=false)
	private byte[] key ;
	
	@Column(name="creationdate", nullable=false)
	private Date creationTime ;
	
	@Column(name="code", nullable=false)
	private String code ;
	
	
	public Message() {
		super();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getIdUser() {
		return idUser;
	}


	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}


	public byte[] getMessage() {
		return message;
	}


	public void setMessage(byte[] message) {
		this.message = message;
	}


	public byte[] getKey() {
		return key;
	}


	public void setKey(byte[] key) {
		this.key = key;
	}


	public Date getCreationTime() {
		return creationTime;
	}


	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}



	
}
