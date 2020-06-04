package com.tyss.cg.springbootdatajpa.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tyss.cg.springbootdatajpa.entity.Applyloan;
import com.tyss.cg.springbootdatajpa.entity.LoanPrograms;
import com.tyss.cg.springbootdatajpa.entity.User;


public interface UserServices {
	
	//view
	public List<User> findAll();
	public List<User> viewClients();
	public List<User> viewCustomer();
	public User getByEmail(String email);
	
	
	//manipulation
	public void deleteUser(int userid);
	public User getById(int empid);
	public boolean saveUser(User user);	
	public boolean saveUser2(User user);	
	public boolean updatePassword(User user);
	public boolean updateUser(User user);
	public boolean updateApplication(User user);
	
	
	//pagination and sorting methods
	public Page<User> getClients(int pageNo, int itemsPerPage);
	public Page<User> getSortClients(int pageNo, int itemsPerPage, String fieldname);
	public int retrieveId();
	
	//application forms
	public List<User> requestedApplications();
	public List<User> rejectedApplications();
	public List<User> approvedApplications();
	
}
