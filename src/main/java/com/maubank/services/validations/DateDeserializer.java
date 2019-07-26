package com.maubank.services.validations;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.maubank.utils.Constants;

public class DateDeserializer extends StdDeserializer<LocalDate> {

	private static final long serialVersionUID = 1L;

	protected DateDeserializer() {
		super(LocalDate.class);
	}

	@Override
	public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		try {
			return LocalDate.parse(p.readValueAs(String.class));
	    } catch (DateTimeParseException e) {
	    	System.out.println(e.getMessage());
	    }

		return LocalDate.parse(Constants.BEGIN_DATE);
	}

}
