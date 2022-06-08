package com.sotuexample;

public class ExchangeAmountResponse {

	private String from;
	private String to;
	private String toAmount;
	private String exchangeRate;
	private String success;
	private String errorMessage;
	
	public ExchangeAmountResponse(String from, String to, String toAmount, String exchangeRate) {
		super();
		this.setFrom(from);
		this.setTo(to);
		this.setToAmount(toAmount);
		this.setExchangeRate(exchangeRate);
		this.setSuccess("true");
		this.setErrorMessage("");
	}
	
	
	public void setError(String errorMessage) {
		this.setSuccess("false");
		this.setErrorMessage(errorMessage);
	}
	
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getToAmount() {
		return toAmount;
	}

	public void setToAmount(String toAmount) {
		this.toAmount = toAmount;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
