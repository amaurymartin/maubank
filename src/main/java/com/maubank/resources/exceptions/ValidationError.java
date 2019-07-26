package com.maubank.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String field, String message) {
		this.errors.add(new FieldMessage().setField(field).setMessage(message));
	}

}
