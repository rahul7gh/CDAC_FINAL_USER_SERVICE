package com.customermanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customermanagement.security.config.TokenManager;
import com.customermanagement.security.dto.JwtRequestModel;
import com.customermanagement.security.dto.JwtResponseModel;

@RestController
@CrossOrigin
public class JwtController {
   @Autowired
   private UserDetailsService userDetailsService;
   
   @Autowired
   private AuthenticationConfiguration authenticationManager;
   @Autowired
   private TokenManager tokenManager;
   @PostMapping("/login")
   public ResponseEntity<?> createToken(@RequestBody JwtRequestModel
   request) throws Exception {
      try {
    	  System.err.println("AUTHCONFIG "+authenticationManager.getAuthenticationManager());
         authenticationManager.getAuthenticationManager().authenticate(
            new
            UsernamePasswordAuthenticationToken(request.getUsername(),
            request.getPassword())
         );
      } catch (DisabledException e) {
         throw new Exception("USER_DISABLED", e);
      } catch (BadCredentialsException e) {
         throw new Exception("INVALID_CREDENTIALS", e);
      }
      final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
      final String jwtToken = tokenManager.generateJwtToken(userDetails);
      return ResponseEntity.ok(new JwtResponseModel(jwtToken));
   }
}