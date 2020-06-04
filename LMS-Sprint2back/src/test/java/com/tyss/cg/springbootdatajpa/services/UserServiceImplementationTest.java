package com.tyss.cg.springbootdatajpa.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tyss.cg.springbootdatajpa.dao.UserRepository;
import com.tyss.cg.springbootdatajpa.entity.User;

@SpringBootTest
class UserServiceImplementationTest {

	User user;
	//User addUser = null;
	Boolean addUser = false;
	@Autowired
	UserServices userServices;
	@Autowired
	UserRepository userRepository;
	
	
	
	@BeforeEach
	void addUser() {
		user=new User();
		user.setFullname("Ashish");
		user.setAddress("101 Kallyan Nagar");
		user.setState("Uttar Pradesh");
		user.setCity("Lucknow");
		user.setGender("male");
		user.setPincode("123456");
		user.setEmail("ashish@gmail.com");
		user.setPhone("9089765432");
		user.setRole("ROLE_ADMIN");
		user.setAge("22");
		user.setGender("Male");
		user.setPassword("Qwerty@123");
		addUser = userServices.saveUser(user);
		
	}
	
	@Test
	void testSaveUser() {
		assertEquals(true, addUser);
	}
	
	@Test
	void testGetById() {
		User user=userServices.getById(this.user.getUserid());
		assertNotNull(user);
	}
	
	@Test
	void testFindAll() {
		List<User> userList=userServices.findAll();
		assertNotNull(userList);
	}
	
	@AfterEach
	void testDeleteUser() {
		userServices.deleteUser(this.user.getUserid());
	}


}
