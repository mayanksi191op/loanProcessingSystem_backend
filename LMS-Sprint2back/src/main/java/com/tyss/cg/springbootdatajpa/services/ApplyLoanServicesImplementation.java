package com.tyss.cg.springbootdatajpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tyss.cg.springbootdatajpa.dao.ApplyLoanRepository;
import com.tyss.cg.springbootdatajpa.entity.Applyloan;
import com.tyss.cg.springbootdatajpa.entity.User;

@Service
public class ApplyLoanServicesImplementation implements ApplyLoanServices{
	
	@Autowired
	private ApplyLoanRepository loanApplicationRepository;
	
	@Override
	public List<Applyloan> findAllApplications() {
		return loanApplicationRepository.findAll();
	}

	@Override
	public Applyloan findApplicationById(int applicationId) {
		Optional<Applyloan> result = loanApplicationRepository.findById(applicationId);
		Applyloan theApplication =  null;
		if(result.isPresent()) {
			theApplication=result.get();
			return theApplication;
		}
		return null;
	}

	@Override
	public Applyloan saveApplication(Applyloan applocation) {
		return loanApplicationRepository.save(applocation);
	}

	@Override
	public void deleteApplication(int applicationId) {
		loanApplicationRepository.deleteById(applicationId);
	}

	@Override
	public List<Applyloan> requestedApplications() {
		return loanApplicationRepository.requestedApplication();
	}
	
	@Override
	public Page<Applyloan> requestedApplication(int pageNo, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);
		return loanApplicationRepository.requestedApplication(pageable);
	}
	
	@Override
	public Page<Applyloan> sortRequestedApplication(int pageNo, int itemsPerPage, String fieldname) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldname));
		return loanApplicationRepository.requestedApplication(pageable);
	}
	
	
	
	
	
	
	@Override
	public List<Applyloan> rejectedApplications() {
		return loanApplicationRepository.rejectedApplication();
	}
	
	@Override
	public Page<Applyloan> rejectedApplications(int pageNo, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);
		return loanApplicationRepository.rejectedApplication(pageable);
	}
	
	@Override
	public Page<Applyloan> sortRejectedApplications(int pageNo, int itemsPerPage, String fieldname) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldname));
		return loanApplicationRepository.rejectedApplication(pageable);
	}
	
	@Override
	public List<Applyloan> approvedApplications() {
		return loanApplicationRepository.approvedApplication();
	}
	
	@Override
	public Page<Applyloan> approvedApplications(int pageNo, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);
		return loanApplicationRepository.approvedApplication(pageable);
	}
	
	@Override
	public Page<Applyloan> sortApprovedApplications(int pageNo, int itemsPerPage, String fieldname) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldname));
		return loanApplicationRepository.approvedApplication(pageable);
	}
	
	
	
	@Override
	public Applyloan setApprovedStatus(int loanid){
		return loanApplicationRepository.setApprovedStatus(loanid);
	}
	
	@Override
	public Applyloan setApproved(int loanid) {
		Optional<Applyloan> result = loanApplicationRepository.findById(loanid);
		Applyloan applyloan = null;
		applyloan = result.get();
		applyloan.setStatus("Approved");
		loanApplicationRepository.save(applyloan);
		return applyloan;
		
	}
	
	@Override
	public Applyloan setRejected(int loanid) {
		Optional<Applyloan> result = loanApplicationRepository.findById(loanid);
		Applyloan applyloan = null;
		applyloan = result.get();
		applyloan.setStatus("Rejected");
		loanApplicationRepository.save(applyloan);
		return applyloan;
		
	}

}
