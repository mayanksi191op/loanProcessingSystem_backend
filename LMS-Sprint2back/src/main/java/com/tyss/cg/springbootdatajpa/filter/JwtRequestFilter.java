package com.tyss.cg.springbootdatajpa.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tyss.cg.springbootdatajpa.services.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
        final String requestTokenHeader = request.getHeader("Authorization"); //Bearer adhgauylu37qh3e7
        
        String username = null;
        String jwt = null;
        
        // JWT is in the form of 'Bearer token'
        // Remove the Bearer string and get only the token(jwt)
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
        	jwt = requestTokenHeader.substring(7);
        	try {
        		username = jwtUtil.getUsernameFromToken(jwt);
        		
        	} catch (IllegalArgumentException iae) {
				
        		logger.warn("Unable to get JWT.");
        		logger.warn(iae.getMessage());
        		
			}  catch (ExpiredJwtException jwtException) {
				
        		logger.warn("Token has expired!");
        		logger.warn(jwtException.getMessage());
        		
			}
        	
        } else {
        	 
        	logger.warn("Token not present or doesn't start with Bearer String");
        }
	
        // Once we get the token, Validate it.
        
        if(username != null && SecurityContextHolder.getContext().getAuthentication()== null) {
        	
           
              UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        	
              // if token is valid configure Spring security to manually set authentication
        	  if (jwtUtil.validateToken(jwt, userDetails)) {
        		  
        		  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        				  new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());
        		  
        		  usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        		  // After setting the Authentication in the context,
        		  //specify that the current user is authenticated.
        		  // so that it passes the Spring Security configuration successfully
        		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        	  }
      
        }// end of outer if
        
        filterChain.doFilter(request, response);
      
	}//End of doFilterInternal()
}
