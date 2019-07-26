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

import com.maubank.domain.Budget;
import com.maubank.domain.Category;
import com.maubank.domain.dtos.BudgetsDTO;
import com.maubank.repositories.BudgetRepository;
import com.maubank.repositories.CategoryRepository;
import com.maubank.resources.exceptions.FieldMessage;
import com.maubank.utils.Messages;

public class BudgetValidator implements ConstraintValidator<BudgetValid, BudgetsDTO> {

	@Autowired
    private HttpServletRequest request;

	@Autowired
	private BudgetRepository repository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void initialize(BudgetValid ann) {
	}

	@Override
	public boolean isValid(BudgetsDTO budget, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		@SuppressWarnings("unchecked")
		final Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		Integer pathVariable = map.isEmpty() ? null : Integer.valueOf(map.get("id"));
		Integer category = budget.getCategory();

		if(pathVariable != null) {
			if(budget.getId() != null && budget.getId() != pathVariable)
				list.add(new FieldMessage().setField("id").setMessage(Messages.INVALID_ID));

			if(list.isEmpty()) {
				Budget repoBudget = this.repository.findById(pathVariable).orElse(new Budget());
				if(repoBudget.getId() == null) throw new ObjectNotFoundException(pathVariable, Budget.class.toString());

				if(category != null && category != repoBudget.getCategory().getId())
					list.add(new FieldMessage().setField("category").setMessage(Messages.INVALID_ID));
			}
		}

		if(list.isEmpty() && category != null) {
			Category repoCategory = this.categoryRepository.findById(budget.getCategory()).orElse(new Category());
			if(repoCategory.getId() == null) list.add(new FieldMessage().setField("category").setMessage(Messages.INVALID_ID));

			Budget repoBudget = this.repository.findByCategoryAndYear(repoCategory, budget.getYear()).orElse(new Budget());
			if(repoBudget.getId() != null && repoBudget.getId() != pathVariable)
				list.add(new FieldMessage().setField("year").setMessage(Messages.EXISTING_YEAR));
		}

		for(FieldMessage error : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(error.getMessage()).addPropertyNode(error.getField())
				   .addConstraintViolation();
		}

		return list.isEmpty();
	}

}
