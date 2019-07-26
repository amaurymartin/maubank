package com.maubank.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.maubank.domain.User;
import com.maubank.services.validations.DateDeserializer;
import com.maubank.services.validations.DateSerializer;
import com.maubank.services.validations.UserValid;
import com.maubank.utils.Messages;

@UserValid
public class UsersDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@Pattern(regexp = "^(?=.{4,20}$)(?![_.])(?!.*[_.]{2})[a-z0-9._]+(?<![_.])$", message = Messages.INVALID_USERNAME)
	@NotEmpty(message = Messages.MANDATORY_FIELD)
	private String username;

	@Email(message = Messages.INVALID_EMAIL)
	@NotEmpty(message = Messages.MANDATORY_FIELD)
	private String email;

	@Pattern(regexp = "^[\\pL ,.'-]+$", message = Messages.INVALID_NAME)
	@NotEmpty(message = Messages.MANDATORY_FIELD)
	private String name;

	@Pattern(regexp = "^[\\pL ,.'-]+$", message = Messages.INVALID_SURNAME)
	private String surname;

	@CPF(message = Messages.INVALID_CPF)
	private String cpf;

	@JsonDeserialize(using = DateDeserializer.class)
	@JsonSerialize(using = DateSerializer.class)
	@Past(message = Messages.INVALID_DATE)
	private LocalDate birth;

	@JsonInclude(Include.NON_NULL)
	@NotEmpty(message = Messages.MANDATORY_FIELD)
	private String password;

	public UsersDTO() {
	}

	public UsersDTO(User user) {
		this.setId(user.getId())
			.setUsername(user.getUsername())
			.setEmail(user.getEmail())
			.setName(user.getName())
			.setSurname(user.getSurname())
			.setCpf(user.getCpf())
			.setBirth(user.getBirth())
			.setPassword(user.getPassword());
	}

	public Integer getId() {
		return id;
	}

	public UsersDTO setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public UsersDTO setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UsersDTO setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getName() {
		return name;
	}

	public UsersDTO setName(String name) {
		this.name = name;
		return this;
	}

	public String getSurname() {
		return surname;
	}

	public UsersDTO setSurname(String surname) {
		this.surname = surname;
		return this;
	}

	public String getCpf() {
		return cpf;
	}

	public UsersDTO setCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public UsersDTO setBirth(LocalDate birth) {
		this.birth = birth;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UsersDTO setPassword(String password) {
		this.password = password;
		return this;
	}

}
