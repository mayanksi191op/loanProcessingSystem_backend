package com.tyss.cg.springbootdatajpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Entity
@Data
@Table(name = "loanprograms")
public class LoanPrograms {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loan_no;
	@Column
	@Pattern(regexp = "[a-z A-Z]{3,30}", message = "Please write letters only")
	private String loanname;
	@Column
	@Pattern(regexp = "^0*(1[89]|2[0-4])-0*([4-6][0-9]|70)$", message = "Please write minimum age ranging from 18-24 and maximum age ranginf from 40-70 (Ex- 23-60)")
	private String validage;
	@Column
	@Pattern(regexp = "^0*([6-9]|1[0-9]|20)$", message = "Enter rates between 6-20")
	private String interestrates;
	@Column
	@Pattern(regexp = "^0*([1-9]|[12][0-9]|30)$", message = "Please write value from 1-30")
	private String maxtenure;
	
	public LoanPrograms(String loanname, String validage, String interestrates, String maxtenure) {
		this.loanname = loanname;
		this.validage = validage;
		this.interestrates = interestrates;
		this.maxtenure = maxtenure;
	}
	
	public LoanPrograms() {
		
	}
}
