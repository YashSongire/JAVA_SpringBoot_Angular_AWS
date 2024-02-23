package com.express.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserGen")
	@SequenceGenerator(sequenceName = "UserSeq", name = "UserGen", initialValue = 200300)
	private long userid;
	
	@Column(name = "UserName")
	private String username;
	
	@Column(name = "Usertype")
	@Enumerated(EnumType.STRING)
	private UserType usertype;
	
	@Column(name = "UserPassword")
	private String userpassword;
	
	@Column(name = "UserPhone")
	private String userphone;
	
	@Column(name = "UserEmail")
	private String useremail;
	
    public enum UserType {
        Admin,
        Customer
    }

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserType getUsertype() {
		return usertype;
	}

	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public User(String username, UserType usertype, String userpassword, String userphone, String useremail) {
		super();
		this.username = username;
		this.usertype = usertype;
		this.userpassword = userpassword;
		this.userphone = userphone;
		this.useremail = useremail;
	}

	public User(long userid, String username, UserType usertype, String userpassword, String userphone,
			String useremail) {
		super();
		this.userid = userid;
		this.username = username;
		this.usertype = usertype;
		this.userpassword = userpassword;
		this.userphone = userphone;
		this.useremail = useremail;
	}

	public User() {
		super();
	}
	
}
