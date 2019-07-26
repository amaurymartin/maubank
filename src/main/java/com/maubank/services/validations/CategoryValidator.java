package com.maubank.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.maubank.domain.Category;
import com.maubank.domain.User;
import com.maubank.domain.dtos.CategoriesDTO;
import com.maubank.domain.enums.Groups;
import com.maubank.repositories.CategoryRepository;
import com.maubank.repositories.UserRepository;
import com.maubank.resources.exceptions.FieldMessage;
import com.maubank.utils.CommonFunctions;
import com.maubank.utils.Messages;

public class CategoryValidator implements ConstraintValidator<CategoryValid, CategoriesDTO> {

	@Autowired
    private HttpServletRequest request;

	@Autowired
	private CategoryRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(CategoryValid ann) {
	}

	@Override
	public boolean isValid(CategoriesDTO category, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		@SuppressWarnings("unchecked")
		final Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		Integer pathVariable = map.isEmpty() ? null : Integer.valueOf(map.get("id"));
		Integer user = category.getUser();
		String description =  category.getDescription() == null ? null : CommonFunctions.toUnicode(category.getDescription().toUpperCase());

		if(pathVariable != null) {
			if(category.getId() != null && category.getId() != pathVariable)
				list.add(new FieldMessage().setField("id").setMessage(Messages.INVALID_ID));

			if(list.isEmpty()) {
				Category repoCategory = this.repository.findById(pathVariable).orElse(new Category());
				if(repoCategory.getId() == null) throw new ObjectNotFoundException(pathVariable, Category.class.toString());

				if(user != null && user != repoCategory.getUser().getId())
					list.add(new FieldMessage().setField("user").setMessage(Messages.INVALID_ID));
			}
		}

		if(list.isEmpty() && user != null) {
			if(description != null) {
				User repoUser = this.userRepository.findById(user).orElse(new User());

				if(repoUser.getId() != null) {
					Category repoCategory = this.repository.findByUserAndDescription(repoUser, description).orElse(new Category());
					if(repoCategory.getId() != pathVariable)
						list.add(new FieldMessage().setField("description").setMessage(Messages.EXISTING_DESCRIPTION));
				}
			}

			if(Groups.toEnum(category.getGroup().toUpperCase()) == null)
				list.add(new FieldMessage().setField("group").setMessage(Messages.INVALID_GROUP));
		}

		for(FieldMessage error : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(error.getMessage()).addPropertyNode(error.getField())
				   .addConstraintViolation();
		}

		return list.isEmpty();
	}

}
