package com.sotuexample;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ValidateSSNController {

	
	@RequestMapping("/test_validator") 
	public ValidateSSNResponse getExampleValue() {
		return new ValidateSSNResponse(false, "this is validatortest");
	}	

	
	@GetMapping("/validate_ssnget") 
	public ValidateSSNResponse validateSSNGet(
			@RequestParam(name="ssn") String ssn,
			@RequestParam(name="country_code") String countryCode) {
			
		ValidateSSNResponse response = new ValidateSSNResponse(true, "");
		if (!countryCode.equals("FI")) {
			response.setErroMessage("Invalid countrycode");
			response.setSsnValid("false");
			return response;
		}


		SSNValidator validator = new SSNValidator();
		boolean success = validator.validateSSN(ssn);
		if (!success) {
			response.setErroMessage(validator.getLastErrorMessage());
			response.setSsnValid("false");
		}
		
		return response;
	}	

	
	@PostMapping("/validate_ssn") 
	public ValidateSSNResponse validateSSNPost(
			@RequestParam(name="ssn") String ssn,
			@RequestParam(name="country_code") String countryCode) {
			
		ValidateSSNResponse response = new ValidateSSNResponse(true, "");
		if (!countryCode.equals("FI")) {
			response.setErroMessage("Invalid countrycode");
			response.setSsnValid("false");
			return response;
		}


		SSNValidator validator = new SSNValidator();
		boolean success = validator.validateSSN(ssn);
		if (!success) {
			response.setErroMessage(validator.getLastErrorMessage());
			response.setSsnValid("false");
		}
		
		return response;
	}	

}
