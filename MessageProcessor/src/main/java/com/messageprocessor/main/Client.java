package com.messageprocessor.main;

import java.io.BufferedReader;
import java.io.FileReader;

import com.messageprocessor.processor.impl.MessageProcessorSalesImpl;

// Main class to invoke the flow
public class Client {

	public static void main(String[] args) {

		try {
			MessageProcessorSalesImpl messageProcessor = MessageProcessorSalesImpl
					.getInstance();
			String message;
			BufferedReader inputFile = new BufferedReader(new FileReader(
					"./src/main/resources/inputSalesData.txt"));
			while ((message = inputFile.readLine()) != null) {
				messageProcessor.process(message);
			}
			inputFile.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();

		}
	}

}
