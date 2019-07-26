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

import com.maubank.domain.Goal;
import com.maubank.domain.User;
import com.maubank.domain.dtos.GoalsDTO;
import com.maubank.repositories.GoalRepository;
import com.maubank.repositories.UserRepository;
import com.maubank.resources.exceptions.FieldMessage;
import com.maubank.utils.CommonFunctions;
import com.maubank.utils.Messages;

public class GoalValidator implements ConstraintValidator<GoalValid, GoalsDTO> {

	@Autowired
    private HttpServletRequest request;

	@Autowired
	private GoalRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(GoalValid ann) {
	}

	@Override
	public boolean isValid(GoalsDTO goal, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		@SuppressWarnings("unchecked")
		final Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		Integer pathVariable = map.isEmpty() ? null : Integer.valueOf(map.get("id"));
		Integer user = goal.getUser();
		String description = goal.getDescription() == null ? null : CommonFunctions.toUnicode(goal.getDescription().toUpperCase());

		if(pathVariable != null) {
			if(goal.getId() != null && goal.getId() != pathVariable)
				list.add(new FieldMessage().setField("id").setMessage(Messages.INVALID_ID));

			if(list.isEmpty()) {
				Goal repoGoal = this.repository.findById(pathVariable).orElse(new Goal());
				if(repoGoal.getId() == null) throw new ObjectNotFoundException(pathVariable, Goal.class.toString());

				if(user != null && user != repoGoal.getUser().getId())
					list.add(new FieldMessage().setField("user").setMessage(Messages.INVALID_ID));
			}
		}

		if(list.isEmpty() && user != null && description != null) {
			User repoUser = this.userRepository.findById(user).orElse(new User());

			if(repoUser.getId() != null) {
				Goal goalWallet = this.repository.findByUserAndDescription(repoUser, description).orElse(new Goal());
				if(goalWallet.getId() != pathVariable)
					list.add(new FieldMessage().setField("description").setMessage(Messages.EXISTING_DESCRIPTION));
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
