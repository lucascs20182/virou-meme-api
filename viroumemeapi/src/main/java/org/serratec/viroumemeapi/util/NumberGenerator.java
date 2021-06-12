package org.serratec.viroumemeapi.util;

import java.math.BigInteger;
import java.util.UUID;

public class NumberGenerator {

	private final Integer SIZE_NUMBER = 6;

	public String generate() {

		return String.format("%6s", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).toString()
				.substring(0, SIZE_NUMBER);
	}
}
