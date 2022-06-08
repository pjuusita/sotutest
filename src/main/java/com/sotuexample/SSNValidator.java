package com.sotuexample;

public class SSNValidator {

	
	private String lastErrorMessage = "";
	
	
	public SSNValidator() {
		
	}
	
	
	public boolean validateSSN(String ssn) {
		
		if (ssn.length() != 11) {
			lastErrorMessage = "Invalid SSN length";
			return false;
		}
		
		char centuryCode = ssn.charAt(6);
		if (!((centuryCode == '+') || (centuryCode == '-') || (centuryCode == 'A'))) {
			lastErrorMessage = "Invalid character \'" + centuryCode + "\' after birthdate";
			return false;
		}

		char verificationCharacter = ssn.charAt(10);

		String numberString = ssn.substring(0,6) + ssn.substring(7,10);
		int number = Integer.parseInt(numberString);
		int remainder = number % 31;
		
		char shoulBeCharacter = this.getVerificationMark(remainder);
		if (Character.compare(shoulBeCharacter, verificationCharacter) != 0) {
			lastErrorMessage = "Verification character fails.";
			return false;
		}
		return true;
	}
	
	
	public char getVerificationMark(int remainder) {
		
		switch(remainder) {
		  case 0: return '0';
		  case 1: return '1';
		  case 2: return '2';
		  case 3: return '3';
		  case 4: return '4';
		  case 5: return '5';
		  case 6: return '6';
		  case 7: return '7';
		  case 8: return '8';
		  case 9: return '9';
		  case 10: return 'A';
		  case 11: return 'B';
		  case 12: return 'C';
		  case 13: return 'D';
		  case 14: return 'E';
		  case 15: return 'F';
		  case 16: return 'H';
		  case 17: return 'J';
		  case 18: return 'K';
		  case 19: return 'L';
		  case 20: return 'M';
		  case 21: return 'N';
		  case 22: return 'P';
		  case 23: return 'R';
		  case 24: return 'S';
		  case 25: return 'T';
		  case 26: return 'U';
		  case 27: return 'V';
		  case 28: return 'W';
		  case 29: return 'X';
		  case 30: return 'Y';
		}
		
		return '?';
	}
	
	
	public String getLastErrorMessage() {
		return lastErrorMessage;
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println("Jeejee");
		
		SSNValidator validator = new SSNValidator();
		
		validator.validateSSN("131052X308Taaa");  // väärin
		validator.validateSSN("131052X308T");  // väärin
		validator.validateSSN("131052-308T");  // oikein
		validator.validateSSN("131052-308R");  // väärin
		
		validator.validateSSN("311299-9872");  // oikein
		validator.validateSSN("311299-987A");  // väärin
		
		validator.validateSSN("210198-118E");  // oikein
		validator.validateSSN("210198-118F");  // oikein
	}
	
}
