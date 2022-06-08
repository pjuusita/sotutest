package com.sotuexample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SSNValidatorTest {

	
	@Test
	void TestSSN_001() {
		
		SSNValidator validator = new SSNValidator();
		
		assertEquals(false, validator.validateSSN("131052X308Taaa")); 
		assertEquals(false, validator.validateSSN("131052X308T")); 
		
		assertEquals(true, validator.validateSSN("131052-308T")); 
		assertEquals(false, validator.validateSSN("131052-308R")); 
		
		assertEquals(true, validator.validateSSN("311299-9872")); 
		assertEquals(false, validator.validateSSN("311299-987A")); 
		
		assertEquals(true, validator.validateSSN("210198-118E")); 
		assertEquals(false, validator.validateSSN("210198-118F")); 
		
	}
	
	
}
