package com.maubank.domain.enums;

public enum Groups {

	IN("IN", "Renda"),
	ESSENTIAL_EXPENSES("EE", "Gastos Essenciais"),
	LIFESTYLE("LS", "Estilo de vida"),
	LOANS("LO", "Empr\u00E9stimos"),
	BETWEEN_ACCOUNTS("BA", "Lan\u00E7amentos entre contas"),
	OTHERS("OT", "Outros");

	private String code;
	private String description;

	public static final String GROUPS_CODES = "IN|EE|LS|LO|BA|OT";

	private Groups(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public Groups setCode(String code) {
		this.code = code;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Groups setDescription(String description) {
		this.description = description;
		return this;
	}

	public static Groups toEnum(String code) {
		if(code == null) return null;

		for(Groups g : Groups.values()) {
			if(g.getCode().equals(code)) return g;
		}

		return null;
	}

}
