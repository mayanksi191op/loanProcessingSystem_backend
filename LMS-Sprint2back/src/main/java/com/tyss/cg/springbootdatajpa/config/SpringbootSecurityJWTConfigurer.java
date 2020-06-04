package com.tyss.cg.springbootdatajpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tyss.cg.springbootdatajpa.filter.JwtRequestFilter;
import com.tyss.cg.springbootdatajpa.security.BootAunthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringbootSecurityJWTConfigurer extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Autowired
	private BootAunthenticationEntryPoint  bootAuthenticationEntryPoint;

	@Autowired 
	private UserDetailsService userDetailsService;

	@Autowired 
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService);

	} // End of configureGlobal()


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable()
		.authorizeRequests().antMatchers("/api/login").permitAll()
		.and()
		.authorizeRequests().antMatchers("/api/clients").hasRole("ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/loanprograms").permitAll()
		.and()
		.authorizeRequests().antMatchers("/api/loanprograms/{pageNo}/{itemsPerPage}").permitAll()
		.and()
		.authorizeRequests().antMatchers("/api/loanprograms/{pageNo}/{itemsPerPage}/{fieldname}").permitAll()
		.and()
		.authorizeRequests().antMatchers("/api/application/requested/").hasAnyRole("LAD", "ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/application/requested/{pageNo}/{itemsPerPage}").hasAnyRole("LAD", "ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/application/requested/{pageNo}/{itemsPerPage}/{fieldname}").hasAnyRole("LAD", "ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/application/rejected/").hasAnyRole("LAD", "ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/application/rejected/{pageNo}/{itemsPerPage}").hasAnyRole("LAD", "ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/application/rejected/{pageNo}/{itemsPerPage}/{fieldname}").hasAnyRole("LAD", "ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/loanprograms/delete/{lona_no}").hasRole("ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/loanprograms/update").hasRole("ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/loanprograms/add").hasRole("ADMIN")
		.and() 
		.authorizeRequests().antMatchers("/api/makeloan/{email}").hasRole("CUSTOMER")
		.and()
		.authorizeRequests().antMatchers("/api/clients/{pageNo}/{itemsPerPage}").hasRole("ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/clients/{pageNo}/{itemsPerPage}/{fieldname}").hasRole("ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/application/approved/").hasAnyRole("LAD", "ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/application/approved/{pageNo}/{itemsPerPage}").hasAnyRole("LAD", "ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/application/approved/{pageNo}/{itemsPerPage}/{fieldname}").hasAnyRole("LAD", "ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/clients/{userid}").hasAnyRole("LAD", "ADMIN")
		.and()
		.authorizeRequests().antMatchers("/api/customers").permitAll()
		.and()
		.authorizeRequests().antMatchers("/api/customers/put").permitAll()
		.and()
		.authorizeRequests().antMatchers("/api/customers/{userid}").permitAll()
		.and()
		.authorizeRequests().antMatchers("/api/customers/password/put").permitAll()
		.and()
		.authorizeRequests().antMatchers("/api/user/{email}").permitAll()
		.and()
		.authorizeRequests().antMatchers("/register/getAllUsers").permitAll()
		.and()
		.authorizeRequests().antMatchers("/addEmployee").permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(bootAuthenticationEntryPoint)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}// End of configure()
}
