package com.maubank.resources.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.hibernate.ObjectNotFoundException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.maubank.services.exceptions.DataIntegrityException;
import com.maubank.utils.Messages;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<StandardError> invalidFormat(InvalidFormatException e, HttpServletRequest request) {
		StandardError err = new StandardError()
				.setStatus(HttpStatus.BAD_REQUEST.value())
				.setMessage(String.format(Messages.INVALID_DATA_TYPE, String.format(e.getPath().toString().split("\"")[1]), e.getTargetType().getSimpleName()))
				.setTimestamp(LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError err = (ValidationError) new ValidationError()
				.setStatus(HttpStatus.BAD_REQUEST.value())
				.setMessage(Messages.VALIDATION_ERROR)
				.setTimestamp(LocalDateTime.now());

		for(FieldError fe : e.getBindingResult().getFieldErrors()) err.addError(fe.getField(), fe.getDefaultMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<StandardError> methodArgumentTypeMismatch(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
		StandardError err = (StandardError) new StandardError()
				.setStatus(HttpStatus.BAD_REQUEST.value())
				.setMessage(String.format(Messages.INVALID_PARAMETER_TYPE, String.format(e.getName()), e.getRequiredType().getSimpleName()))
				.setTimestamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
		StandardError err = (StandardError) new StandardError()
				.setStatus(HttpStatus.BAD_REQUEST.value())
				.setMessage(e.getMessage())
				.setTimestamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<StandardError> missingServletRequestParameter(MissingServletRequestParameterException e, HttpServletRequest request) {
		StandardError err = (StandardError) new StandardError()
				.setStatus(HttpStatus.BAD_REQUEST.value())
				.setMessage(String.format(Messages.MISSING_REQUEST_PARAMETER, String.format(e.getParameterName())))
				.setTimestamp(LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardError> constraintViolation(ConstraintViolationException e, HttpServletRequest request) {
		String parameterName = e.getMessage().substring(e.getMessage().lastIndexOf(".") + 1, e.getMessage().lastIndexOf(":"));

		StandardError err = (StandardError) new StandardError()
				.setStatus(HttpStatus.BAD_REQUEST.value())
				.setMessage(String.format(Messages.INVALID_PARAMETER, String.format(parameterName)))
				.setTimestamp(LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(PropertyReferenceException.class)
	public ResponseEntity<StandardError> propertyReference(PropertyReferenceException e, HttpServletRequest request) {
		StandardError err = new StandardError()
				.setStatus(HttpStatus.NOT_FOUND.value())
				.setMessage(String.format(Messages.INVALID_ORDERBY_PARAMETER, String.format(e.getPropertyName())))
				.setTimestamp(LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		String objectName = e.getEntityName().substring(e.getEntityName().lastIndexOf(".") + 1);

		StandardError err = new StandardError()
				.setStatus(HttpStatus.NOT_FOUND.value())
				.setMessage(String.format(Messages.OBJECT_NOT_FOUND, objectName, e.getIdentifier()))
				.setTimestamp(LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		StandardError err = new StandardError()
				.setStatus(HttpStatus.BAD_REQUEST.value())
				.setMessage(e.getMessage())
				.setTimestamp(LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

}
