package com.sotuexample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExchangeController {

	@RequestMapping("/get_example_values") 
	public ExchangeAmountResponse getExampleValue() {
		return new ExchangeAmountResponse("EUR", "USD", "150","1,5");
	}	
	
	// http://localhost:8080/exchange_amount?from=EUR&to=USD&from_amoount=100
	@GetMapping("/exchange_amount") 
	public ExchangeAmountResponse getExchangeAmount(
			@RequestParam(name="from") String fromCurrency,
			@RequestParam(name="to") String toCurrency,
			@RequestParam(name="from_amount") String fromAmount) {
		
		ExchangeRates rates = new ExchangeRates();
		ExchangeAmountResponse response = new ExchangeAmountResponse(fromCurrency, toCurrency, "0", "0");
		
		if (!rates.isCurrencyAvailable(fromCurrency)) {
			response.setError("Currency " + fromCurrency + " not recognized or unavailable");
			return response;
		}
		if (!rates.isCurrencyAvailable(toCurrency)) {
			response.setError("Currency " + toCurrency + " not recognized or unavailable");
			return response;
		}
		
		double toAmount = rates.convertCurrency(fromCurrency, toCurrency, fromAmount);
		double rate = rates.getRate(fromCurrency, toCurrency);
		response.setToAmount("" + toAmount);
		response.setExchangeRate("" + rate);
		return response;
	}	
	
	
	@GetMapping("/fetch_rates") 
	public int getExchangeAmount() {
		ExchangeRates rates = new ExchangeRates();
		rates.fetchRates();
		return 1;
	}	
}
