package com.messageprocessor.utils;

import com.messageprocessor.entity.Adjustments;
import com.messageprocessor.entity.ProductSale;

public class MessageParser {

	public static Object parseMessage(String message) {
		String[] messageArray = message.trim().split("\\s+");
		String firstWord = messageArray[0];
		if (firstWord.matches("Add|Subtract|Multiply")) {
			return parseMessageType3(messageArray);
		} else if (firstWord.matches("^\\d+")) {
			return parseMessageType2(messageArray);
		} else if (messageArray.length == 3 && messageArray[1].contains("at")) {
			return parseMessageType1(messageArray);
		}
		System.out.println("Wrong sales notice");
		return null;
	}

	static Adjustments parseMessageType3(String[] messageArray) {

		if (messageArray.length > 3 || messageArray.length < 3)
			return null;
		String operation = messageArray[0];
		String productName = parseName(messageArray[2]);
		double amount = parsePrice(messageArray[1]);
		return new Adjustments(operation, productName, amount);
	}

	static ProductSale parseMessageType1(String[] messageArray) {
		if (messageArray.length > 3 || messageArray.length < 3)
			return null;
		String productName = parseName(messageArray[0]);
		double productPrice = parsePrice(messageArray[2]);
		int productQuantity = 1;
		return new ProductSale(productName, productPrice, productQuantity);
	}

	static ProductSale parseMessageType2(String[] messageArray) {
		if (messageArray.length > 7 || messageArray.length < 7)
			return null;

		String productName = parseName(messageArray[3]);
		double productPrice = parsePrice(messageArray[5]);
		int productQuantity = Integer.parseInt(messageArray[0]);
		productPrice = productPrice * productQuantity;
		ProductSale productSale = new ProductSale(productName, productPrice,
				productQuantity);
		return productSale;
	}

	static double parsePrice(String amount) {
		double price = Double.parseDouble(amount.replaceAll("£|p", ""));
		if (!amount.contains(".")) {
			price = Double.valueOf(Double.valueOf(price)
					/ Double.valueOf("100"));
		}
		return price;
	}

	static String parseName(String productName) {

		String parsedName = "";
		String typeWithoutLastChar = productName.substring(0,
				productName.length() - 1);
		// enum DEPREC
		if (productName.endsWith("o")) {
			parsedName = String.format("%soes", typeWithoutLastChar);
		} else if (productName.endsWith("y")) {
			parsedName = String.format("%sies", typeWithoutLastChar);
		} else if (productName.endsWith("h")) {
			parsedName = String.format("%shes", typeWithoutLastChar);
		} else if (!productName.endsWith("s")) {
			parsedName = String.format("%ss", productName);
		} else {
			parsedName = String.format("%s", productName);
		}
		return parsedName.toLowerCase();
	}

}
