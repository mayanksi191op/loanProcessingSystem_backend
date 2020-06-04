//package com.tyss.cg.springbootdatajpa.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.tyss.cg.springbootdatajpa.filter.CustomUsernamePasswordAuthenticationFilter;
//import com.tyss.cg.springbootdatajpa.handlers.MyLogoutSuccessHandler;
//import com.tyss.cg.springbootdatajpa.security.BootAunthenticationEntryPoint;
//
//@Configuration
//@EnableWebSecurity
//public class Sprint2LMSConfigurer extends WebSecurityConfigurerAdapter{
//	
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder(12);
//	}
//	
//	
//	@Autowired
//	private  BootAunthenticationEntryPoint bootAuthenticationEntryPoint;
//	
//	
//	@Autowired
//	private  AuthenticationSuccessHandler authenticationSuccessHandler;
//	
//
//	@Bean
//	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
//		return new SimpleUrlAuthenticationFailureHandler();
//	}
//	
//	
//	@Autowired
//	private  MyLogoutSuccessHandler myLogoutSuccessHandler;
//	
//	
//	
//	@Bean
//	public UsernamePasswordAuthenticationFilter getUsernamePasswordAuthenticationFilter() throws Exception {
//		CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter(); 
//	 filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
//	 filter.setAuthenticationFailureHandler(getAuthenticationFailureHandler());
//	 filter.setAuthenticationManager(authenticationManager());
//	
//	 return filter;
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//	http.csrf().disable()
//	    .exceptionHandling()
//	    .authenticationEntryPoint(bootAuthenticationEntryPoint)
//	    .and()
//	    .authorizeRequests()
//	    .antMatchers("/api/loanprograms").hasAnyRole("USER")
//	    .and()
//	    .authorizeRequests()
//	    .antMatchers("/api/application").hasRole("LAD")
//	    .and()
//	    .authorizeRequests()
//	    .antMatchers("/api/clients").hasRole("ADMIN")
//	    .and()
//	    .cors()
//	    .and()
//	    .addFilterBefore(getUsernamePasswordAuthenticationFilter(), CustomUsernamePasswordAuthenticationFilter.class)
//	    .logout()
//	    .logoutSuccessHandler(myLogoutSuccessHandler);
//		
//	}
//	
//}
