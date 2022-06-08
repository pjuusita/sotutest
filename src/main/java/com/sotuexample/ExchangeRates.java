package com.sotuexample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ExchangeRates {

	private static String apiKey = "jkmdxVtz6kAAxwBRAyDi0v7apcCGAEcO";
	
	private double eurRate = 0;
	private double sekRate = 0;
	private double usdRate = 0;
	
	
	public ExchangeRates() {
		boolean success = loadRates();
		if (success == false) {
			fetchRates();
			saveRates();
		}
	}
	
	public void setEurRate(double value) {
		this.eurRate = value;
	}

	public void setSekRate(double value) {
		this.sekRate = value;
	}
	

	public void setUsdRate(double value) {
		this.usdRate = value;
	}
	
	public void printRates() {
		System.out.println("--EUR:" +  this.eurRate);
		System.out.println("--SEK:" +  this.sekRate);
		System.out.println("--USD:" +  this.usdRate);
	}
	
	
	

	public boolean loadRates() {
		try {
			File file = new File("rates.txt");
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				
				if (data.startsWith("EUR:")) {
					this.eurRate = Double.parseDouble(data.substring(4));
				}
				if (data.startsWith("SEK:")) {
					this.sekRate = Double.parseDouble(data.substring(4));
				}
				if (data.startsWith("USD:")) {
					this.usdRate = Double.parseDouble(data.substring(4));
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	public void saveRates() {
		
		File file = null;
	    try {
	    	file = new File("rates.txt");
	    	if (file.createNewFile()) {
	    		System.out.println("File created: " + file.getName());
	    	} else {
	    		System.out.println("File already exists.");
	    	}
	    } catch (IOException e) {
	    	System.out.println("An error occurred.");
	    	e.printStackTrace();
	    }
	    
	    try {
	        FileWriter writer = new FileWriter("rates.txt");
	        writer.write("EUR:" + eurRate + "\n");
	        writer.write("SEK:" + sekRate + "\n");
	        writer.write("USD:" + usdRate + "\n");
	        writer.close();
	        //System.out.println("Successfully wrote to the file. - " + file.getAbsolutePath());
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
	
	
	public boolean isCurrencyAvailable(String currencyStr) {
		currencyStr.toUpperCase();
		if (currencyStr.equals("EUR")) return true;
		if (currencyStr.equals("SEK")) return true;
		if (currencyStr.equals("USD")) return true;
		return false;
	}
	
	
	public double convertCurrency(String fromCurrency, String toCurrency, String amountStr) {

		fromCurrency.toUpperCase();
		toCurrency.toUpperCase();

		double fromRate = 0;
		if (fromCurrency.equals("EUR")) {
			fromRate = this.eurRate;
		}
		if (fromCurrency.equals("SEK")) {
			fromRate = this.sekRate;
		}
		if (fromCurrency.equals("USD")) {
			fromRate = this.usdRate;
		}
		double toRate = 0;
		if (toCurrency.equals("EUR")) {
			toRate = this.eurRate;
		}
		if (toCurrency.equals("SEK")) {
			toRate = this.sekRate;
		}
		if (toCurrency.equals("USD")) {
			toRate = this.usdRate;
		}
		
		double amount = Double.parseDouble(amountStr);

		System.out.println("fromRate - " + fromRate);
		System.out.println("toRate - " + toRate);
		System.out.println("amount - " + amount);

		double result = toRate / fromRate * amount;
		System.out.println("result - " + result);
		return result;
	}
	
	

	public double getRate(String fromCurrency, String toCurrency) {

		fromCurrency.toUpperCase();
		toCurrency.toUpperCase();

		double fromRate = 0;
		if (fromCurrency.equals("EUR")) {
			fromRate = this.eurRate;
		}
		if (fromCurrency.equals("SEK")) {
			fromRate = this.sekRate;
		}
		if (fromCurrency.equals("USD")) {
			fromRate = this.usdRate;
		}
		double toRate = 0;
		if (toCurrency.equals("EUR")) {
			toRate = this.eurRate;
		}
		if (toCurrency.equals("SEK")) {
			toRate = this.sekRate;
		}
		if (toCurrency.equals("USD")) {
			toRate = this.usdRate;
		}
		
		double result = toRate / fromRate;
		return result;
	}
	
	
	public void parseJSONTest() {
		JSONParser parser = new JSONParser();
	    JSONObject json = null;
	    try {
		    json = (JSONObject)parser.parse("{\r\n"
		    		+ "    \"success\": true,\r\n"
		    		+ "    \"timestamp\": 1654625884,\r\n"
		    		+ "    \"base\": \"EUR\",\r\n"
		    		+ "    \"date\": \"2022-06-07\",\r\n"
		    		+ "    \"rates\": {\r\n"
		    		+ "        \"EUR\": 1,\r\n"
		    		+ "        \"USD\": 1.070595,\r\n"
		    		+ "        \"SEK\": 10.486766\r\n"
		    		+ "    }\r\n"
		    		+ "}");
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	    Object arry = json.get("rates");
	    System.out.println("rate eur :" + arry.getClass().getName());
	}
	
	
	// hakee vaihtokurssit apilayerin serveriltä...
	public void fetchRates() {
		
	    OkHttpClient client = new OkHttpClient().newBuilder().build();
	    
	    Request request = new Request.Builder()
	      .url("https://api.apilayer.com/exchangerates_data/latest?symbols=EUR,USD,SEK&base=EUR")
	      .addHeader("apikey", apiKey)
	      .build();
	    
	    Response response = null;
	    JSONParser parser = new JSONParser();
	    JSONObject json = null;
	    try {
		    response = client.newCall(request).execute();
		    String responseStr = response.body().string();
		    System.out.println(responseStr);
		    json = (JSONObject)parser.parse(responseStr);

	    } catch (Exception ex) {
	      	ex.printStackTrace();
	    }
	    
	    if (json == null) {
	    	System.out.println("jsonni nulli");
	    }
	    JSONObject rates = (JSONObject)json.get("rates");
	    
	    this.eurRate = this.jsonObjectToDouble(rates.get("EUR"));
	    this.usdRate = this.jsonObjectToDouble(rates.get("USD"));
	    this.sekRate = this.jsonObjectToDouble(rates.get("SEK"));

	    
	    System.out.println("eurRate - " + eurRate);
	    System.out.println("usdRate - " + usdRate);
	    System.out.println("sekRate - " + sekRate);

	    saveRates();
	}
	
	
	public double jsonObjectToDouble(Object value) {
	    if (value instanceof Long) {
		    return ((Long)value).doubleValue();
	    }
	    if (value instanceof Double) {
		    return ((Double)value).doubleValue();
	    }
	    return (double)0;
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println("Jeejee");
		
		ExchangeRates rates = new ExchangeRates();
		//rates.fetchRates();
		//rates.loadRates();
		rates.printRates();
		
		rates.convertCurrency("EUR", "SEK", "120");
		rates.convertCurrency("SEK", "EUR", "1200");
		
		/*
		rates.setEurRate(0.1);
		rates.setSekRate(0.2);
		rates.setUsdRate(0.3);
		rates.saveRates();
		*/
	}
}
