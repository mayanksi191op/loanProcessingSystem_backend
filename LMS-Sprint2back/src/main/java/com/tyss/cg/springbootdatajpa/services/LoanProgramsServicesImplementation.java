package com.tyss.cg.springbootdatajpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tyss.cg.springbootdatajpa.dao.LoanProgramsRepository;
import com.tyss.cg.springbootdatajpa.entity.LoanPrograms;
import com.tyss.cg.springbootdatajpa.exception.NoDataPresentException;
import com.tyss.cg.springbootdatajpa.exception.UserNotFoundException;

@Service
public class LoanProgramsServicesImplementation implements LoanProgramsServices {
	
	@Autowired
	private LoanProgramsRepository loanProgramsRepository;
	
	@Override
	public List<LoanPrograms> findAll() {
		List<LoanPrograms> list = loanProgramsRepository.findAll();
		if (list.isEmpty()) {
			throw new NoDataPresentException("No Data Present...");
		}
		return list;
	}

	@Override
	public LoanPrograms getById(int lona_no) {
		Optional<LoanPrograms> result = loanProgramsRepository.findById(lona_no);
		
		LoanPrograms loanPrograms =null;
		if (result.isPresent()) {
			loanPrograms = result.get();
		
		}else {
			throw new UserNotFoundException("Loan not found");
		}
		return loanPrograms;
	}

	@Override
	public void deleteLoan(int lona_no) {
		loanProgramsRepository.deleteById(lona_no);
	}

	@Override
	public boolean saveLoan(LoanPrograms loanPrograms) {
		Optional<LoanPrograms> resultOptional = loanProgramsRepository.findById(loanPrograms.getLoan_no());
		
		if (resultOptional.isPresent()) {
			return false;
			
		}else {
			loanProgramsRepository.save(loanPrograms);
			return true;
		}
	}

	@Override
	public Page<LoanPrograms> getLoans(int pageNo, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);
		return loanProgramsRepository.findAll(pageable);
	}

	@Override
	public boolean updateLoan(LoanPrograms loanPrograms) {
		loanProgramsRepository.save(loanPrograms);
		return true;
	}

	@Override
	public Page<LoanPrograms> getSortLoans(int pageNo, int itemsPerPage, String fieldname) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldname));
		return loanProgramsRepository.findAll(pageable);
	}

}
