package com.tyss.cg.springbootdatajpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "applyloan")
public class Applyloan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int loanid;
	@Column
	@NotNull(message = "Please Select the Loan Type")
	private String loantype;
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
	@Pattern(regexp = "[a-zA-Z0-9]{5,12}", message = "Please enter valid adhar card number.")
	private String card;
	@Column
	@NotNull
	@Pattern(regexp = "[a-z A-Z]{3,10}", message = "Please enter letters only.")
	private String occupation;
	@Column
	@NotNull
	@Pattern(regexp = "[0-9]{1,7}", message = "Please enter number ranging 0-1000000.")
	private String monthlyincome;
	@Column
	@NotNull
	@Pattern(regexp = "^[0-9]{0,7}$", message = "Please write amount ranging 1-1000000.")
	private String loanamount;
	@Column
	@NotNull
	@Pattern(regexp = "^0*([1-9]|[12][0-9]|30)$", message = "Please write number ranging 1-30.")
	private String loantenure;
	@Column
	@NotNull(message = "Please fill the Address.")
	private String address;
	@Column
	@NotNull(message = "Status should not be null")
	private String status;
	@Column
	@Pattern(regexp = "[1-9][0-9]{9,9}", message = "Please write 10 digit phone number")
	private String mobile;
	@NotNull
	@Column
	@Size(min=3, message = "name must have at least 3 characters")
	private String fullname;
	@Column
	@NotNull
	@Pattern(regexp = "^0*(1[89]|[2-6][0-9]|70)$", message = "Write age between 18-70.")
	private String age;
	
//	@JsonIgnore
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "userid", referencedColumnName = "userid")
//	private User user;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	@JsonBackReference
	private User user;
	
	public Applyloan(int loanid, String loantype, String state, String city, String pincode, String mobile, 
			String occupation, String monthlyincome, String loanamount, String loantenure, String address,
			String status, String card, String age, String fullname) {
		this.loanid = loanid;
		this.loantype = loantype;
		this.state = state;
		this.city = city;
		this.pincode = pincode;
		this.occupation = occupation;
		this.monthlyincome = monthlyincome;
		this.loanamount = loanamount;
		this.loantenure = loantenure;
		this.address = address;
		this.status = status;
		this.card = card;
		this.age = age;
		this.fullname = fullname;
		this.mobile = mobile;
	}
	
	
	
	public Applyloan() {
		
	}
}
