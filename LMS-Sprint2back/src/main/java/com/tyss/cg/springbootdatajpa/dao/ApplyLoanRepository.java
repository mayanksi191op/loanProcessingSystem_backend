package com.tyss.cg.springbootdatajpa.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tyss.cg.springbootdatajpa.entity.Applyloan;

@Repository
public interface ApplyLoanRepository extends JpaRepository<Applyloan, Integer> {

	@Query("from Applyloan where status='Requested'")
	List<Applyloan> requestedApplication();
	
	@Query("select c from Applyloan c where status='Requested'")
	Page<Applyloan> requestedApplication(Pageable pageable);
	
	@Query("from Applyloan where status='Rejected'")
	List<Applyloan> rejectedApplication();
	
	@Query("select c from Applyloan c where status='Rejected'")
	Page<Applyloan> rejectedApplication(Pageable pageable);
	
	@Query("from Applyloan where status='Approved'")
	List<Applyloan> approvedApplication();
	
	@Query("select c from Applyloan c where status='Approved'")
	Page<Applyloan> approvedApplication(Pageable pageable);
	
	@Query("update Applyloan set status='approved' where loanid=?1")
	Applyloan setApprovedStatus(int loanid);
}
