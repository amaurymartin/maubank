package com.maubank.resources.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;
	private String message;
	private LocalDateTime timestamp;

	public Integer getStatus() {
		return status;
	}

	public StandardError setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public StandardError setMessage(String message) {
		this.message = message;
		return this;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public StandardError setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
		return this;
	}

}
