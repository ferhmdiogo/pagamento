package com.diogofernando.pagamento.repository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = -7962403652743489235L;
	
	public ResourceNotFoundException(String exception) {
		super(exception);
	}

}