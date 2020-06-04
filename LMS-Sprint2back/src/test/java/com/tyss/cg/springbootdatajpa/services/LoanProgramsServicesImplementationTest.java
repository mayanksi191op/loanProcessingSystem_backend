package com.tyss.cg.springbootdatajpa.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tyss.cg.springbootdatajpa.entity.LoanPrograms;



@SpringBootTest
class LoanProgramsServicesImplementationTest {
	
	LoanPrograms loanPrograms;
	Boolean addLoan;
	
	@Autowired
	private LoanProgramsServices loanServices;
	
	@BeforeEach
	void addProgram() {
		loanPrograms = new LoanPrograms();
		loanPrograms.setInterestrates("13");
		loanPrograms.setLoanname("UrgetNRILoan");
		loanPrograms.setMaxtenure("23");
		loanPrograms.setValidage("20-60");
		addLoan = loanServices.saveLoan(loanPrograms);
	}
	
	@Test
	void testSaveProgram() {
		assertEquals(true, addLoan);
	}
	
	@Test
	void testGetById() {
		LoanPrograms loanPrograms2=loanServices.getById(this.loanPrograms.getLoan_no());
		assertNotNull(loanPrograms2);
	}
	
	@Test
	void testFindAll() {
		List<LoanPrograms> loanList=loanServices.findAll();
		assertNotNull(loanList);
	}
	
	@AfterEach
	void testDeleteProgram() {
		loanServices.deleteLoan(this.loanPrograms.getLoan_no());
	}
}
