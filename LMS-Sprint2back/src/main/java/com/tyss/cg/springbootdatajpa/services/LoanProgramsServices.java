package com.tyss.cg.springbootdatajpa.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tyss.cg.springbootdatajpa.entity.LoanPrograms;

public interface LoanProgramsServices {
	
	//Loan CRUD
		public List<LoanPrograms> findAll();
		
		public Page<LoanPrograms> getLoans(int pageNo, int itemsPerPage);
		
		public Page<LoanPrograms> getSortLoans(int pageNo, int itemsPerPage, String fieldname);
		
		public LoanPrograms getById(int loan_no);
		
		public void deleteLoan(int loan_no);
		
		public boolean saveLoan(LoanPrograms loanPrograms);	
		
		public boolean updateLoan(LoanPrograms loanPrograms);
		
}
