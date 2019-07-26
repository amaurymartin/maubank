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
import com.maubank.domain.Goal;
import com.maubank.domain.Payment;
import com.maubank.domain.Wallet;
import com.maubank.domain.dtos.PaymentsDTO;
import com.maubank.repositories.CategoryRepository;
import com.maubank.repositories.PaymentRepository;
import com.maubank.repositories.WalletRepository;
import com.maubank.resources.exceptions.FieldMessage;
import com.maubank.utils.Messages;

public class PaymentValidator implements ConstraintValidator<PaymentValid, PaymentsDTO> {

	@Autowired
    private HttpServletRequest request;

	@Autowired
	private PaymentRepository repository;

	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void initialize(PaymentValid ann) {
	}

	@Override
	public boolean isValid(PaymentsDTO payment, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		@SuppressWarnings("unchecked")
		final Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		Integer pathVariable = map.isEmpty() ? null : Integer.valueOf(map.get("id"));
		Integer wallet = payment.getWallet();
		Integer category = payment.getCategory();

		if(pathVariable != null) {
			if(payment.getId() != null && payment.getId() != pathVariable)
				list.add(new FieldMessage().setField("id").setMessage(Messages.INVALID_ID));

			Payment repoPayment = this.repository.findById(pathVariable).orElse(new Payment());
			if(repoPayment.getId() == null) throw new ObjectNotFoundException(pathVariable, Goal.class.toString());
		}

		if(list.isEmpty() && wallet != null & category != null) {
			Wallet repoWallet = this.walletRepository.findById(wallet).orElse(new Wallet());
			Category repoCategory = this.categoryRepository.findById(category).orElse(new Category());

			if(repoWallet.getId() != null && repoCategory.getId() != null) {
				if(repoWallet.getUser() != repoCategory.getUser()) {
					list.add(new FieldMessage().setField("wallet").setMessage(Messages.INVALID_ID));
					list.add(new FieldMessage().setField("category").setMessage(Messages.INVALID_ID));
				}
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
