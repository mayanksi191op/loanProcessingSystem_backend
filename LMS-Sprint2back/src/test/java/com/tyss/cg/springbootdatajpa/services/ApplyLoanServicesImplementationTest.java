package com.tyss.cg.springbootdatajpa.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tyss.cg.springbootdatajpa.entity.Applyloan;

@SpringBootTest
class ApplyLoanServicesImplementationTest {
	
	Applyloan applyloan;
	Applyloan addApplication;
	
	@Autowired
	private ApplyLoanServices applyLoanServices;
	
	@BeforeEach
	void addApplication() {
		applyloan = new Applyloan();
		applyloan.setAddress("Nayi Basti Chotti Julahti");
		applyloan.setCard("ADH12345");
		applyloan.setCity("Rath");
		applyloan.setLoanamount("20000");
		applyloan.setLoantenure("25");
		applyloan.setLoantype("NRI Loan");
		applyloan.setMobile("7681012323");
		applyloan.setMonthlyincome("20000");
		applyloan.setOccupation("ChaiWala");
		applyloan.setPincode("123456");
		applyloan.setState("Delhi");
		applyloan.setStatus("Requested");
		addApplication = applyLoanServices.saveApplication(applyloan);
	}
	
	@Test
	void testSaveLoan() {
		assertNotNull(addApplication);
	}
	
	@Test
	void testGetById() {
		Applyloan application=applyLoanServices.findApplicationById(this.applyloan.getLoanid());
		assertNotNull(application);
	}
	
	@Test
	void testFindAll() {
		List<Applyloan> applicationList=applyLoanServices.findAllApplications();
		assertNotNull(applicationList);
	}
	
	@AfterEach
	void testDeleteUser() {
		applyLoanServices.deleteApplication(this.applyloan.getLoanid());
	}

}
