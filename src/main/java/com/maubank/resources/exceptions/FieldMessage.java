package com.maubank.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String field;
	private String message;

	public FieldMessage() {
	}

	public String getField() {
		return field;
	}

	public FieldMessage setField(String field) {
		this.field = field;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public FieldMessage setMessage(String message) {
		this.message = message;
		return this;
	}

}
