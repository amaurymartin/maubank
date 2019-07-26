package com.maubank.domain.enums;

public enum Status {

	PENDING("PD", "Pendente"),
	CONFIRMED("CF", "Confirmado"),
	CANCELED("CN", "Cancelado");

	private String code;
	private String description;

	public static final String STATUS_CODES = "PD|CF|CN";

	private Status(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public Status setCode(String code) {
		this.code = code;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Status setDescription(String description) {
		this.description = description;
		return this;
	}

	public static Status toEnum(String code) {
		if(code == null) return null;

		for(Status s : Status.values()) {
			if(s.getCode().equals(code)) return s;
		}

		return null;
	}

}
