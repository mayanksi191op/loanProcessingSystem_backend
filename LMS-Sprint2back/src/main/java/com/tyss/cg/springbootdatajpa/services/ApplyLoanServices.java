package com.tyss.cg.springbootdatajpa.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tyss.cg.springbootdatajpa.entity.Applyloan;

public interface ApplyLoanServices {
	
	public List<Applyloan> findAllApplications();
	
	public Applyloan findApplicationById(int ApplicationId);
	
	public Applyloan saveApplication(Applyloan applyloan);
	
	public void deleteApplication(int loanid);

	//view according to status
	public List<Applyloan> requestedApplications();
	public Page<Applyloan> requestedApplication(int pageNo, int itemsPerPage);
	public Page<Applyloan> sortRequestedApplication(int pageNo, int itemsPerPage, String fieldname);
	public List<Applyloan> approvedApplications();
	public Page<Applyloan> approvedApplications(int pageNo, int itemsPerPage);
	public Page<Applyloan> sortApprovedApplications(int pageNo, int itemsPerPage, String fieldname);
	public List<Applyloan> rejectedApplications();
	public Page<Applyloan> rejectedApplications(int pageNo, int itemsPerPage);
	public Page<Applyloan> sortRejectedApplications(int pageNo, int itemsPerPage, String fieldname);
	
	public Applyloan setApprovedStatus(int loanid);
	
	
	public Applyloan setApproved(int loanid);
	public Applyloan setRejected(int loanid);
	
}