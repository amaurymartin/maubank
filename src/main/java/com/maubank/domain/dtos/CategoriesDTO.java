package com.maubank.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.maubank.domain.Category;
import com.maubank.services.validations.CategoryValid;
import com.maubank.utils.Messages;

@CategoryValid
public class CategoriesDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull(message = Messages.MANDATORY_FIELD)
	private Integer user;

	@Pattern(regexp = "^[\\pL\\pN ,-]+$", message = Messages.INVALID_DESCRIPTION)
	@NotEmpty(message = Messages.MANDATORY_FIELD)
	private String description;

	@NotEmpty(message = Messages.MANDATORY_FIELD)
	private String group;

	public CategoriesDTO() {
	}

	public CategoriesDTO(Category category) {
		this.setId(category.getId())
			.setUser(category.getUser().getId())
			.setDescription(category.getDescription())
			.setGroup(category.getGroup());
	}

	public Integer getId() {
		return id;
	}

	public CategoriesDTO setId(Integer id) {
		this.id = id;
		return this;
	}
	
	public Integer getUser() {
		return user;
	}

	public CategoriesDTO setUser(Integer user) {
		this.user = user;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public CategoriesDTO setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getGroup() {
		return group;
	}

	public CategoriesDTO setGroup(String group) {
		this.group = group;
		return this;
	}

}
