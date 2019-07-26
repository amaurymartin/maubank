package com.maubank.services.validations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.maubank.domain.User;
import com.maubank.domain.dtos.UsersDTO;
import com.maubank.repositories.UserRepository;
import com.maubank.resources.exceptions.FieldMessage;
import com.maubank.utils.Constants;
import com.maubank.utils.Messages;

public class UserValidator implements ConstraintValidator<UserValid, UsersDTO> {

	@Autowired
    private HttpServletRequest request;

	@Autowired
	private UserRepository repository;

	@Override
	public void initialize(UserValid ann) {
	}

	@Override
	public boolean isValid(UsersDTO user, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		@SuppressWarnings("unchecked")
		final Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		Integer pathVariable = map.isEmpty() ? 0 : Integer.valueOf(map.get("id"));

		if(pathVariable != 0) {
			User repoUser = this.repository.findById(pathVariable).orElse(new User());
			if(repoUser.getId() == null) throw new ObjectNotFoundException(pathVariable, User.class.toString());

			if(user.getId() != null && user.getId() != pathVariable) list.add(new FieldMessage().setField("id").setMessage(Messages.INVALID_ID));
		}

		if(list.isEmpty()) {
			if(user.getUsername() != null) {
				User repoUser = this.repository.findByUsername(user.getUsername().toLowerCase()).orElse(new User());
				if(repoUser.getId() != null && repoUser.getId() != pathVariable)
					list.add(new FieldMessage().setField("username").setMessage(Messages.EXISTING_USERNAME));
			}

			if(user.getEmail() != null) {
				User repoUser = this.repository.findByEmail(user.getEmail().toLowerCase()).orElse(new User());
				if(repoUser.getId() != null && repoUser.getId() != pathVariable)
					list.add(new FieldMessage().setField("email").setMessage(Messages.EXISTING_EMAIL));
			}

			if(user.getCpf() != null) {
				User repoUser = this.repository.findByCpf(user.getCpf().replaceAll("\\D", "")).orElse(new User());
				if(repoUser.getId() != null && repoUser.getId() != pathVariable)
					list.add(new FieldMessage().setField("cpf").setMessage(Messages.EXISTING_CPF));
			}

			if(user.getBirth() != null) {
				if(user.getBirth().equals(LocalDate.parse(Constants.BEGIN_DATE)))
					list.add(new FieldMessage().setField("birth").setMessage(Messages.INVALID_DATE));
			}
		}

		for(FieldMessage error : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(error.getMessage()).addPropertyNode(error.getField())
				   .addConstraintViolation();
		}

		return list.isEmpty();
	}

}
