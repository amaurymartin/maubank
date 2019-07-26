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

import com.maubank.domain.User;
import com.maubank.domain.Wallet;
import com.maubank.domain.dtos.WalletsDTO;
import com.maubank.repositories.UserRepository;
import com.maubank.repositories.WalletRepository;
import com.maubank.resources.exceptions.FieldMessage;
import com.maubank.utils.CommonFunctions;
import com.maubank.utils.Messages;

public class WalletValidator implements ConstraintValidator<WalletValid, WalletsDTO> {

	@Autowired
    private HttpServletRequest request;

	@Autowired
	private WalletRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(WalletValid ann) {
	}

	@Override
	public boolean isValid(WalletsDTO wallet, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		@SuppressWarnings("unchecked")
		final Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		Integer pathVariable = map.isEmpty() ? null : Integer.valueOf(map.get("id"));
		Integer user = wallet.getUser();
		String description = wallet.getDescription() == null ? null : CommonFunctions.toUnicode(wallet.getDescription().toUpperCase());

		if(pathVariable != null) {
			if(wallet.getId() != null && wallet.getId() != pathVariable)
				list.add(new FieldMessage().setField("id").setMessage(Messages.INVALID_ID));

			if(list.isEmpty()) {
				Wallet repoWallet = this.repository.findById(pathVariable).orElse(new Wallet());
				if(repoWallet.getId() == null) throw new ObjectNotFoundException(pathVariable, Wallet.class.toString());

				if(user != null && user != repoWallet.getUser().getId())
					list.add(new FieldMessage().setField("user").setMessage(Messages.INVALID_ID));
			}
		}

		if(list.isEmpty() && user != null && description != null) {
			User repoUser = this.userRepository.findById(user).orElse(new User());

			if(repoUser.getId() != null) {
				Wallet repoWallet = this.repository.findByUserAndDescription(repoUser, description).orElse(new Wallet());
				if(repoWallet.getId() != pathVariable)
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
