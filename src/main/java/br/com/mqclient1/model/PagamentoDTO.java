package br.com.mqclient1.model;

import org.springframework.stereotype.Component;

@Component
public class PagamentoDTO {
	String identifier;
	String message;
	
	
	public PagamentoDTO(String identifier, String message) {
		super();
		this.identifier = identifier;
		this.message = message;
	}
	
	public PagamentoDTO() {
	
	}
	
	
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
