package com.messageprocessor.processor.impl;

import com.messageprocessor.entity.Adjustments;
import com.messageprocessor.entity.ProductSale;
import com.messageprocessor.entity.Sales;
import com.messageprocessor.processor.MessageProcessor;
import com.messageprocessor.utils.MessageParser;

// A threadsafe Singleton is implemented
public final class MessageProcessorSalesImpl implements MessageProcessor,
		Cloneable {
	private static MessageProcessorSalesImpl messageProcessor = null;
	private int messageCounter = 0;
	private boolean printedAdjustment = false;

	private MessageProcessorSalesImpl() {

	}

	public static synchronized MessageProcessorSalesImpl getInstance() {
		if (messageProcessor == null) {
			messageProcessor = new MessageProcessorSalesImpl();
		}
		return messageProcessor;
	}

	public boolean process(String message) {

		Object object = MessageParser.parseMessage(message);
		if (object instanceof ProductSale) {
			ProductSale productSale = (ProductSale) object;
			Sales.add(message, productSale);
			messageCounter++;
		} else {
			if (object instanceof Adjustments) {
				Adjustments adjustments = (Adjustments) object;
				Sales.doAdjustment(message, adjustments);
				messageCounter++;
			} else {
				System.out.println("invalid message..");
			}
		}

		if (messageCounter >= 50) {
			if (printedAdjustment == false) {

				System.out
						.println("Sorry, Application is paused because MessageCount reaches to 50.");
				System.out
						.println("Logging the adjustments report that have been made to each sale type");
				// printAdjustmentreport
				Sales.printAdjustments();
				printedAdjustment = true;
			}
		} else {

			if (messageCounter % 10 == 0) {
				System.out
						.println("===== Another 10 messages processed =============");
				Sales.printSale();
			}

		}
		return true;
	}

	@Override
	public Object clone() {
		return messageProcessor;
	}
}
