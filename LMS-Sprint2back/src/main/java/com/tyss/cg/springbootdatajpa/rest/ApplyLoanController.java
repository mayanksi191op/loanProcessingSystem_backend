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

import com.tyss.cg.springbootdatajpa.entity.Applyloan;
import com.tyss.cg.springbootdatajpa.entity.User;
import com.tyss.cg.springbootdatajpa.exception.ApplicationNotFoundException;
import com.tyss.cg.springbootdatajpa.exception.UserNotFoundException;
import com.tyss.cg.springbootdatajpa.response.Response;
import com.tyss.cg.springbootdatajpa.services.ApplyLoanServices;
import com.tyss.cg.springbootdatajpa.services.UserServices;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApplyLoanController {
	
	@Autowired
	private ApplyLoanServices service;
	
	@Autowired
	private UserServices userServices;

	@GetMapping("/application")
	public Response<List<Applyloan>> getAllApplications(){
		return new Response<>(false, "List Retrieved!!!", service.findAllApplications());
	}
	
	@GetMapping("/application/{appId}")
	public Response<Applyloan> getApplicationById(@PathVariable int appId){
		Applyloan application=service.findApplicationById(appId);
		System.out.println("id is" + application.getUser().getUserid());
		return new Response<Applyloan>(false, "Application found!!!", application);
	}
	
	@DeleteMapping("/application/{appId}")
	public Response<Applyloan> deleteProgram(@PathVariable int appId) {
		Applyloan application= service.findApplicationById(appId);
		if(application!=null) {
			service.deleteApplication(appId);
			return new Response<Applyloan>(false, "Application Deleted sucessfully!!!", application);
		}
		else
			throw new ApplicationNotFoundException("Application not found");
	}
	
	@GetMapping("/application/setstatus/{loanid}")
	public Response<Applyloan> setApprovedStatus(@PathVariable int loanid){
		Applyloan applyloan = service.setApprovedStatus(loanid);
		if (applyloan != null) {
			return new Response<Applyloan>(false, "Status changed to approved", applyloan);
		} else {
			throw new ApplicationNotFoundException("Application not found");
		}
	}
	
	@GetMapping("/application/requested/")
	public Response<List<Applyloan>> requestedApplications(){
		List<Applyloan> applyloans = service.requestedApplications();
		if (applyloans != null) {
			return new Response<>(false, "list found", applyloans);
		} else {
			return new Response<>(true, "no status with this status", null);
		}
	}
	
	@GetMapping("/application/requested/{pageNo}/{itemsPerPage}")
	public Page<Applyloan> requestedApplication(@PathVariable int pageNo, @PathVariable int itemsPerPage){
		return service.requestedApplication(pageNo, itemsPerPage);
	}

	@GetMapping("/application/requested/{pageNo}/{itemsPerPage}/{fieldname}")
	public Page<Applyloan> sortRequestedApplication(@PathVariable int pageNo, @PathVariable int itemsPerPage, @PathVariable String fieldname){
		return service.sortRequestedApplication(pageNo, itemsPerPage, fieldname);
	}
	
	

	@GetMapping("/application/rejected/")
	public Response<List<Applyloan>> rejectedApplications(){
		List<Applyloan> applyloans = service.rejectedApplications();
		if (applyloans != null) {
			return new Response<>(false, "list found", applyloans);
		} else {
			return new Response<>(true, "no status with this status", null);
		}
	}
	
	@GetMapping("/application/rejected/{pageNo}/{itemsPerPage}")
	public Page<Applyloan> rejectedApplications(@PathVariable int pageNo, @PathVariable int itemsPerPage){
		return service.rejectedApplications(pageNo, itemsPerPage);
	}

	@GetMapping("/application/rejected/{pageNo}/{itemsPerPage}/{fieldname}")
	public Page<Applyloan> sortRejectedApplications(@PathVariable int pageNo, @PathVariable int itemsPerPage, @PathVariable String fieldname){
		return service.sortRejectedApplications(pageNo, itemsPerPage, fieldname);
	}

	@GetMapping("/application/approved/")
	public Response<List<Applyloan>> approvedApplications(){
		List<Applyloan> applyloans = service.approvedApplications();
		if (applyloans != null) {
			return new Response<>(false, "list found", applyloans);
		} else {
			return new Response<>(true, "no status with this status", null);
		}
	}
	
	@GetMapping("/application/approved/{pageNo}/{itemsPerPage}")
	public Page<Applyloan> approvedApplication(@PathVariable int pageNo, @PathVariable int itemsPerPage){
		return service.approvedApplications(pageNo, itemsPerPage);
	}

	@GetMapping("/application/approved/{pageNo}/{itemsPerPage}/{fieldname}")
	public Page<Applyloan> sortApprovedApplication(@PathVariable int pageNo, @PathVariable int itemsPerPage, @PathVariable String fieldname){
		return service.sortApprovedApplications(pageNo, itemsPerPage, fieldname);
	}
	
	@PutMapping("/application/setapprove/{loanid}")
	public Response<Applyloan> setApproved(@PathVariable int loanid){
		Applyloan applyloan = service.setApproved(loanid);
		if (applyloan != null) {
			return new Response<Applyloan>(false, "Status changed to approved", applyloan);
		} else {
			throw new ApplicationNotFoundException("Application not found");
		}
	}
	
	@PutMapping("/application/setreject/{loanid}")
	public Response<Applyloan> setRejected(@PathVariable int loanid){
		Applyloan applyloan = service.setRejected(loanid);
		if (applyloan != null) {
			return new Response<Applyloan>(false, "Status changed to rejected", applyloan);
		} else {
			throw new ApplicationNotFoundException("Application not found");
		}
	}
	
	//recheck the response
	@PostMapping("/makeloan/{email}")
	public Response<Applyloan> makeLoan(@Valid @PathVariable String email, @RequestBody Applyloan applyloan){
		User user = userServices.getByEmail(email);
		applyloan.setUser(user);
		applyloan.setStatus("Requested");
		service.saveApplication(applyloan);
		if (user == null) {
			throw new UserNotFoundException("User not found!!!");
		} else {
			return new Response<Applyloan>(false, "Application saved", applyloan);
		}
	}

}







//@PutMapping("/application")
//public Applyloan updateApplication(@RequestBody Applyloan application) {
//	Applyloan applicaLoan1=service.saveApplication(application);
//	return applicaLoan1;
//}

//@PostMapping("/application/add-application")
//public Applyloan addApplication(@RequestBody Applyloan application) {
//	application.setLoanid(0);
//	application.setStatus("Requested");
//	Applyloan Applyloan = service.saveApplication(application);
//	return Applyloan;
//}
