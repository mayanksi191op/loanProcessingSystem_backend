package com.tyss.cg.springbootdatajpa.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.cg.springbootdatajpa.entity.User;
import com.tyss.cg.springbootdatajpa.exception.EntryAlreadyExistsException;
import com.tyss.cg.springbootdatajpa.exception.InvalidCredentialsException;
import com.tyss.cg.springbootdatajpa.exception.UserNotAllowedException;
import com.tyss.cg.springbootdatajpa.exception.UserNotFoundException;
import com.tyss.cg.springbootdatajpa.response.JwtResponse;
import com.tyss.cg.springbootdatajpa.response.Response;
import com.tyss.cg.springbootdatajpa.services.UserServices;
import com.tyss.cg.springbootdatajpa.services.JwtUtil;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserRestController {

	@Autowired
	private UserServices userServices;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil; 

	@Autowired
	private UserDetailsService userDetailsService;

	@GetMapping("/clients")
	public Response<List<User>> viewClients() {
		List<User> lists = userServices.viewClients();
		return new Response<>(false, "list retrieved", lists);
	}

	@GetMapping("/customers")
	public Response<List<User>> viewCustomers() {
		List<User> lists = userServices.viewCustomer();
		return new Response<>(false, "list retrieved", lists);
	}

	@GetMapping("/clients/{pageNo}/{itemsPerPage}")
	public Page<User> getClients(@PathVariable int pageNo, @PathVariable int itemsPerPage){
		return userServices.getClients(pageNo, itemsPerPage);
	}

	@GetMapping("/clients/{pageNo}/{itemsPerPage}/{fieldname}")
	public Page<User> getClients(@PathVariable int pageNo, @PathVariable int itemsPerPage, @PathVariable String fieldname){
		return userServices.getSortClients(pageNo, itemsPerPage, fieldname);
	}

	@GetMapping("/user/{email}")
	public Response<User> getById(@PathVariable String email) {
		User user = userServices.getByEmail(email);

		if (user == null) {
			throw new UserNotFoundException("User not found!!!");
		}else {
			return new Response<User>(false, "User found!!!", user);
		}
	}

		@GetMapping("/customers/{userid}")
		public Response<User> getByIdSustomer(@PathVariable int userid) {
			User user = userServices.getById(userid);
			
			if (user == null) {
				throw new UserNotFoundException("User not found!!!");
			}else {
				return new Response<User>(false, "User found!!!", user);
			}
		}

	@PostMapping("/clients")
	public Response<User> save(@Valid @RequestBody User user) {
		user.setRole("ROLE_LAD");
		user.setPassword("Qwerty@123");
		try {
			userServices.saveUser(user);
			return new Response<User>(false, "Client added successfuly.", user);
		} catch (Exception e) {
			throw new EntryAlreadyExistsException("Client already exist!!!");
		}
	}

	@PostMapping("/customers")
	public Response<User> saveCustomer(@Valid @RequestBody User user) {
		user.setRole("ROLE_CUSTOMER");
		user.setPassword("Qwerty@123");
		try {
			userServices.saveUser(user);
			return new Response<User>(false, "Customer added successfuly.", user);
		} catch (Exception e) {
			throw new EntryAlreadyExistsException("User already exist!!!");
		}
	}

	@DeleteMapping("/clients/{userid}")
	public Response<User> delete(@PathVariable int userid) {
		User user = userServices.getById(userid);
		if (user == null) {
			throw new UserNotFoundException("Client not found!!!");
		} else {
			userServices.deleteUser(userid);
			return new Response<User>(false, "Loan deleted", user);
		}
	}

	@GetMapping("/retrieveid")
	public Response<Integer> retrieveId(){
		return new Response<>(false, "id retrieved", userServices.retrieveId());
	}


	@PutMapping("/customers/password/put")
	public Response<User> putCustomerPassword(@RequestBody User user){
		User user2= userServices.getById(user.getUserid());
		if (user2 == null) {
			throw new UserNotFoundException("User not found!!!");
		} else {
			userServices.updatePassword(user);
			return new Response<User>(false, "Details updated sucessfully!!!", user);
		}
	}
	
	@PutMapping("/customers/put")
	public Response<User> putCustomerDetails(@RequestBody User user){
		User user2= userServices.getById(user.getUserid());
		if (user2 == null) {
			throw new UserNotFoundException("User not found!!!");
		} else {
			userServices.updateUser(user);
			return new Response<User>(false, "Details updated sucessfully!!!", user);
		}
	}
	
	



	//applications
//	@GetMapping("/application/requested/")
//	public Response<List<User>> requestedApplications(){
//		List<User> applyloans = userServices.requestedApplications();
//		if (applyloans != null) {
//			return new Response<>(false, "list found", applyloans);
//		} else {
//			throw new ApplicationNotFoundException("No Requested applications present");
//		}
//	}
//
//	@GetMapping("/application/rejected/")
//	public Response<List<User>> rejectedApplications(){
//		List<User> applyloans = userServices.rejectedApplications();
//		if (applyloans != null) {
//			return new Response<>(false, "list found", applyloans);
//		} else {
//			throw new ApplicationNotFoundException("No Rejected applications present");
//		}
//	}
//
//	@GetMapping("/application/approved/")
//	public Response<List<User>> approvedApplications(){
//		List<User> applyloans = userServices.approvedApplications();
//		if (applyloans != null) {
//			return new Response<>(false, "list found", applyloans);
//		} else {
//			throw new ApplicationNotFoundException("No Approved applications present");
//		}
//	}

	
	
	

	////recheck response
	@GetMapping("/customers/application/{email}")
	public Response<User> getCustomerApplications(@PathVariable String email){
		User user = userServices.getByEmail(email);
		if (userServices.getByEmail(email) == null) {
			throw new UserNotFoundException("User not found!!!");
		} else {
			return new Response<User>(true, "Applications Found", user);
		}
	}

	//jwt
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User register) throws Exception{

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(register.getEmail(),register.getPassword()));
		} catch(DisabledException de) {
			//we should use loggers here
			//System.out.println("User is Disabled");
			throw new UserNotAllowedException("User Disabled!!!");

		} catch(BadCredentialsException bce) {
			//we should  use loggers here
			throw new InvalidCredentialsException("Invalid Credentials!!! Please enter correct credentials.");

		}// End of try catch

		final UserDetails userDetails = userDetailsService.loadUserByUsername(register.getEmail());
		final String email = register.getEmail();

		User user = userServices.getByEmail(register.getEmail());
		String role = user.getRole();

		//		final String role = register.getRole();
		final String jwt = jwtUtil.generateToken(userDetails);
		final int userid = user.getUserid();

		return ResponseEntity.ok(new JwtResponse(jwt, email, role, userid, false));//doubt
	}// End of login()
}











//@PutMapping("/customers/put")
//public Response<User> putCustomer(@RequestBody User user){
//	User user2= userServices.getById(user.getUserid());
//	if (user2 == null) {
//		return new Response<User>(true, "loan not found", null);
//	}
//	if (userServices.updateApplication(user) == false) {
//		throw new RuntimeException("please enter the data correctly");
//	}else {
//		return new Response<User>(false, "loan updated", user);
//	}
//}

//@PostMapping("/customers/{userid}")
//public Response<User> saveCustomer1(@PathVariable int userid) {
//User user = userServices.getById(userid);
////		user.setPassword("Qwerty@123");
//if (userServices.saveUser(user) == false) {
//	return new Response<User>(true, "Customer already exists!!!", null);
//}else {
//	//loginServices.saveLogin(login);
//	return new Response<User>(false, "Customer added successfuly.", user);
//
//}
//}
