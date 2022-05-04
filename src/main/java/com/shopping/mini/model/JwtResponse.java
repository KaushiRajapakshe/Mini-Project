package com.shopping.mini.model;

import java.io.Serializable;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-25
 */
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}