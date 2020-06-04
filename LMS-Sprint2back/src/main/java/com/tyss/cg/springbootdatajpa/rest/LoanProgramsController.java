package com.tyss.cg.springbootdatajpa.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.cg.springbootdatajpa.entity.LoanPrograms;
import com.tyss.cg.springbootdatajpa.exception.EntryAlreadyExistsException;
import com.tyss.cg.springbootdatajpa.exception.InvalidDataEnteredException;
import com.tyss.cg.springbootdatajpa.exception.LoanNotFoundException;
import com.tyss.cg.springbootdatajpa.exception.UserNotFoundException;
import com.tyss.cg.springbootdatajpa.response.Response;
import com.tyss.cg.springbootdatajpa.services.LoanProgramsServices;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoanProgramsController {
	
	@Autowired
	private LoanProgramsServices loanProgramsServices;
	
	//CRUD Loan

	@GetMapping("/loanprograms")
		public Response<List<LoanPrograms>> findAll() {
			List<LoanPrograms> lists = loanProgramsServices.findAll();
			return new Response<>(false, "list retrieved", lists);
		}
		
		@GetMapping("/loanprograms/{pageNo}/{itemsPerPage}")
		public Page<LoanPrograms> getLoans(@PathVariable int pageNo, @PathVariable int itemsPerPage){
			return loanProgramsServices.getLoans(pageNo, itemsPerPage);
		}
		
		@GetMapping("/loanprograms/{pageNo}/{itemsPerPage}/{fieldname}")
		public Page<LoanPrograms> getLoans(@PathVariable int pageNo, @PathVariable int itemsPerPage, @PathVariable String fieldname){
			return loanProgramsServices.getSortLoans(pageNo, itemsPerPage, fieldname);
		}
		
		@GetMapping("/loanprograms/{loan_no}")
		public Response<LoanPrograms> getById(@PathVariable int lona_no) {
			LoanPrograms loanPrograms = loanProgramsServices.getById(lona_no);
			
			if (loanPrograms == null) {
				throw new LoanNotFoundException("Loan not found!!!");
			}else {
				return new Response<LoanPrograms>(false, "loan found", loanPrograms);
			}
			
		}
		
		@DeleteMapping("/loanprograms/delete/{lona_no}")
		public Response<LoanPrograms> delete(@PathVariable int lona_no) {
			LoanPrograms loanPrograms = loanProgramsServices.getById(lona_no);
			
			if (loanPrograms == null) {
				throw new LoanNotFoundException("loan not found!!!");
			} else {
				loanProgramsServices.deleteLoan(lona_no);
				return new Response<LoanPrograms>(false, "Loan has been deleted!!!", loanPrograms);
			}
		}
		
		@PutMapping("/loanprograms/update")
		public Response<LoanPrograms> update(@Valid @RequestBody LoanPrograms loanPrograms) {
			LoanPrograms loanPrograms2 = loanProgramsServices.getById(loanPrograms.getLoan_no());
			if (loanPrograms2 == null) {
				throw new LoanNotFoundException("Loan not found!!!");
			}
			if (loanProgramsServices.updateLoan(loanPrograms) == false) {
				throw new InvalidDataEnteredException("please enter the data correctly");
			}else {
				return new Response<LoanPrograms>(false, "Loan has been updated!!!", loanPrograms);
			}

		}
		
		@PostMapping("/loanprograms/add")
		public Response<LoanPrograms> save(@Valid @RequestBody LoanPrograms loanPrograms) {
			
			if (loanProgramsServices.saveLoan(loanPrograms) == false) {
				//throw new RuntimeException("please enter correctly");
				throw new EntryAlreadyExistsException("Loan already exists!!!");
			}else {
				return new Response<LoanPrograms>(false, "Loan added successfuly!!!", loanPrograms);
			}
		}
}
