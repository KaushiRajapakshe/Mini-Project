package com.shopping.mini.controller;

import com.shopping.mini.config.JwtTokenUtil;
import com.shopping.mini.model.JwtRequest;
import com.shopping.mini.model.JwtResponse;
import com.shopping.mini.model.User;
import com.shopping.mini.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-07
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody User user) throws Exception {
		User userObj = userDetailsService.addUser(user);
		if (userObj.getUserName() != null)
			return new ResponseEntity<>("User registered", HttpStatus.CREATED);
		else
			return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
	}


	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateUser(@RequestBody User user) throws Exception {
		User userObj = userDetailsService.updateUser(user);
		if (userObj.getUserName() != null)
			return new ResponseEntity<>("User successfully updated", HttpStatus.OK);
		else
			return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
