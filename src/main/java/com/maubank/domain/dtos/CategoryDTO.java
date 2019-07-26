package com.maubank.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.maubank.domain.Category;
import com.maubank.utils.Messages;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull(message = Messages.MANDATORY_FIELD)
	private UsersDTO user;

	@Pattern(regexp = "^[\\pL\\pN ,-]+$", message = Messages.INVALID_DESCRIPTION)
	@NotEmpty(message = Messages.MANDATORY_FIELD)
	private String description;

	@NotEmpty(message = Messages.MANDATORY_FIELD)
	private String group;

	public CategoryDTO() {
	}

	public CategoryDTO(Category category) {
		this.setId(category.getId())
			.setUser(new UsersDTO(category.getUser().setPassword(null)))
			.setDescription(category.getDescription())
			.setGroup(category.getGroup());
	}

	public Integer getId() {
		return id;
	}

	public CategoryDTO setId(Integer id) {
		this.id = id;
		return this;
	}
	
	public UsersDTO getUser() {
		return user;
	}

	public CategoryDTO setUser(UsersDTO user) {
		this.user = user;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public CategoryDTO setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getGroup() {
		return group;
	}

	public CategoryDTO setGroup(String group) {
		this.group = group;
		return this;
	}

}
