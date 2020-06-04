package com.tyss.cg.springbootdatajpa.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tyss.cg.springbootdatajpa.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	@Query("select c from User c where role='ROLE_LAD'")
	List<User> viewClients();
	
	@Query("select c from User c where role='ROLE_LAD'")
	Page<User> viewClients(Pageable pageable);
	
	@Query("select c from User c where role='ROLE_CUSTOMER'")
	List<User> viewCustomer();
	
	@Query("select c from User c where role='ROLE_CUSTOMER'")
	Page<User> viewCustomer(Pageable pageable);
	
	@Query("select max(userid) from User where role = 'ROLE_CUSTOMER'")
	int retrieveId();
	
	@Query(value = "select DISTINCT u from User u INNER JOIN u.applyloans a where u.role='ROLE_CUSTOMER' and a.status='Requested'")
	List<User> requestedApplications();
	
	@Query(value = "select DISTINCT u from User u INNER JOIN u.applyloans a where u.role='ROLE_CUSTOMER' and a.status='Approved'")
	List<User> approvedApplications();
	
	@Query(value = "select DISTINCT u from User u INNER JOIN u.applyloans a where u.role='ROLE_CUSTOMER' and a.status='Rejected'")
	List<User> rejectedApplications();
	
	
	@Query("from User where email=?1")
	User getByEmail(String email);
}
