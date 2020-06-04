package com.tyss.cg.springbootdatajpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyss.cg.springbootdatajpa.entity.LoanPrograms;

@Repository
public interface LoanProgramsRepository extends JpaRepository<LoanPrograms, Integer> {

}
