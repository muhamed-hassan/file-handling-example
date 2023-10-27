package com.poc.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;

@Table(name = "user_info")
@Entity
public class UserInfo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
		
	private String name;
	
	@Column(name = "national_id")
	private String nationalId;
	
	@Column(name = "cell_phone")
	private String cellPhone;
		
	private String email;
	
	@Column(name = "mailing_address")
	private String mailingAddress;
	
	@Lob
	@Column(name = "personal_image")
	private byte[] personalImage;

	@Version
	private int version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}
	
	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}	

	public byte[] getPersonalImage() {
		return personalImage;
	}

	public void setPersonalImage(byte[] personalImage) {
		this.personalImage = personalImage;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
