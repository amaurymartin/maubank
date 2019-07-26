package com.maubank.utils;

import java.text.Normalizer;

public class CommonFunctions {

	public static String toUnicode(String text) {
		return text.trim().replaceAll(" +", " ").replaceAll("\\p{C}", "?");
	}

	public static String toASCII(String text) {
		return Normalizer.normalize(text.trim().replaceAll(" +", " "), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
}
