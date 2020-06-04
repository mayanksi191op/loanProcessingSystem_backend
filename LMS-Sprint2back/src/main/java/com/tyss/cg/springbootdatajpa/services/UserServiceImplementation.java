package com.tyss.cg.springbootdatajpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tyss.cg.springbootdatajpa.dao.UserRepository;
import com.tyss.cg.springbootdatajpa.entity.Applyloan;
import com.tyss.cg.springbootdatajpa.entity.User;
import com.tyss.cg.springbootdatajpa.exception.NoDataPresentException;
import com.tyss.cg.springbootdatajpa.exception.UserNotFoundException;

@Service
public class UserServiceImplementation implements UserServices {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	//view
	
	@Override
	public List<User> findAll() {
		List<User> list = userRepository.findAll();
		if (list.isEmpty()) {
			throw new NoDataPresentException("No data present");
		} else {
			return list;
		}
	}
	
	@Override
	public List<User> viewClients() {
		List<User> list = userRepository.viewClients();
		if (list.isEmpty()) {
			throw new NoDataPresentException("No data present");
		} else {
			return list;
		}
	}
	
	@Override
	public List<User> viewCustomer() {
		List<User> list = userRepository.viewCustomer();
		if (list.isEmpty()) {
			throw new NoDataPresentException("No data present");
		} else {
			return list;
		}
	}
	
	@Override
	public Page<User> getClients(int pageNo, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);
		return userRepository.viewClients(pageable);
	}
	
	@Override
	public Page<User> getSortClients(int pageNo, int itemsPerPage, String fieldname) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldname));
		return userRepository.viewClients(pageable);
	}
	
	
	
	
	//manipulation

	@Override
	public User getById(int id) {
		Optional<User> result = userRepository.findById(id);
		
		User user =null;
		if (result.isPresent()) {
			user = result.get();
		
		}else {
			throw new UserNotFoundException("id not found " + id);
		}
		return user;
	}

	@Override
	public boolean saveUser(User user) {
		Optional<User> resultOptional = userRepository.findById(user.getUserid());
		
		if (resultOptional.isPresent()) {
			return false;
			
		}else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return true;
		}
	}
	
	@Override
	public boolean saveUser2(User user) {
		Optional<User> resultOptional = userRepository.findById(user.getUserid());
		
		if (resultOptional.isPresent()) {
			return false;
		}else {
			userRepository.save(user);
			return true;
		}
	}
	
	@Override
	public boolean updatePassword(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return true;
	}
	
	@Override
	public boolean updateUser(User user) {
		userRepository.save(user);
		return true;
	}
	
	@Override
	public void deleteUser(int empid) {
		userRepository.deleteById(empid);
	}

	@Override
	public int retrieveId() {
		return userRepository.retrieveId();
	}

	@Override
	public boolean updateApplication(User user) {
		userRepository.save(user);
		return true;
	}
	
	@Override
	public List<User> requestedApplications() {
		return userRepository.requestedApplications();
	}
	
	@Override
	public List<User> rejectedApplications() {
		return userRepository.rejectedApplications();
	}
	
	@Override
	public List<User> approvedApplications() {
		return userRepository.approvedApplications();
	}

	@Override
	public User getByEmail(String email) {
		return userRepository.getByEmail(email);
	}

	
}
