package com.sotuexample;

public class ValidateSSNResponse {

	private String ssnValid;
	private String errorMessage = "";
	
	
	public ValidateSSNResponse(boolean bool, String errorMessage) {
		if (bool == true) {
			ssnValid = "true";
			errorMessage = "";
		} else {
			ssnValid = "false";
			errorMessage = "";
		}
	}
	
	
	public String getSsnValid() {
		return ssnValid;
	}
	
	public void setSsnValid(String ssnValid) {
		this.ssnValid = ssnValid;
	}


	public String getErroMessage() {
		return errorMessage;
	}


	public void setErroMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
