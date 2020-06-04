package com.tyss.cg.springbootdatajpa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "usertable")
@SuppressWarnings("serial")
public class User implements Serializable{
	
	
	//@Pattern(regexp = "([1-9]|1[0-4]).([0-9] {1,4})", message = "must match the pattern")

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	@Column
	@NotNull
	@Size(min=5, message = "name must have at least 2 characters")
	private String fullname;
	@Column
	@NotNull
	@JsonIgnore
	@Pattern(regexp = "(?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,}", message = "password must contain at least one number, one uppercase, one lowercase, one special character and atleast 8 or more characters.")
	private String password;
	@Column
	@NotNull
	@Email(message = "Please enter proper email address")
	private String email;
	@Column
	@NotNull(message = "Please fill the Address.")
	private String address;
	@Column
	@NotNull
	@Pattern(regexp = "[a-z A-Z]{3,20}", message = "Please write letters only.")
	private String state;
	@Column
	@NotNull
	@Pattern(regexp = "[a-z A-Z]{3,12}", message = "Please write letters only.")
	private String city;
	@Column
	@NotNull
	@Pattern(regexp = "[0-9]{6,6}", message = "Please write 6 digit pin containing numbers only.")
	private String pincode;
	@Column
	@NotNull
	@Pattern(regexp = "[1-9][0-9]{9,9}", message = "please match the pattern")
	private String phone;
	@Column
	@NotNull
	@Pattern(regexp = "^0*(1[89]|[2-6][0-9]|70)$", message = "Write age between 18-70.")
	private String age;
	@Column
	@NotNull
	@Pattern(regexp = "Male|Female" , message = "please enter proper gender (Male or Female)")
	private String gender;
	@Column
	@NotNull
	@Pattern(regexp = "(ROLE_)+([A-Z]{2,15})" , message = "Please enter role in the format ROLE_XYZ")
	private String role;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumns( {
//        @JoinColumn(name = "email", referencedColumnName = "email"),
//        @JoinColumn(name = "password", referencedColumnName = "password"),
//        @JoinColumn(name = "role", referencedColumnName = "role"),
//    })
//	private Login login;
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
//	@Exclude
//	private List<Applyloan> applyloans = new LinkedList<Applyloan>();
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_fk", referencedColumnName = "userid")
	@JsonManagedReference
	private List<Applyloan> applyloans;
	
	public User(int userid, String fullname, String password, String email, String address, String state,
			String city, String pincode, String phone, String age, String gender, String role) {
		this.userid = userid;
		this.fullname = fullname;
		this.password = password;
		this.email = email;
		this.address = address;
		this.state = state;
		this.city = city;
		this.pincode = pincode;
		this.phone = phone;
		this.age = age;
		this.gender = gender;
		this.role = role;
	}
	
	public User() {
		
	}
}
