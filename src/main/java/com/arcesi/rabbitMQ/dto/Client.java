package com.arcesi.rabbitMQ.dto;

import lombok.Data;

@Data
public class Client {

	private Long code;
	private String firstName;
	private String lastName;
	private String email;
	private String adresse;
	private boolean actived;
	
}
