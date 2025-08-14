package com.kranthi.AccountManagement.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users extends Audit{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name can't be left empty")
	@Column(name = "name")
	private String name;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Please enter a valid Email Id")
	@Column(name = "email")
	private String email;
	
	@NotBlank(message = "Password is required")
	@Pattern(
		    regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).{8,}$",
		    message = "Password must be at least 8 characters long and contain at least one letter, one number, and one special character"
		)
	@Column(name = "password")
	private String password;
	
	@NotBlank(message = "Account Number is required")
	@Column(name = "account_number", unique = true)
	private String accountNumber;
	
	@NotBlank(message = "Phone Number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
	@Column(name = "phone_number", unique = true)
	private String phoneNumber;
	
	@NotEmpty(message = "Gender can't be left empty")
	@Column(name = "gender")
	private String gender;
	

	@Column(name = "address")
	private String address;
	
	

}
