package com.messageprocessor.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Sales {

	private static Map<String, ProductSale> salesMap = new LinkedHashMap<String, ProductSale>();
	private static List<String> salesMessageLog = new ArrayList<String>();
	private static List<Adjustments> adjustmentReport = new ArrayList<Adjustments>();

	public static void add(String salesMessage, ProductSale productSale) {
		if (salesMap.get(productSale.getProductName()) == null) {
			salesMap.put(productSale.getProductName(), productSale);
			salesMessageLog.add(salesMessage);
		} else {
			ProductSale existingProductSale = salesMap.get(productSale
					.getProductName());
			existingProductSale.setProductQuantity(productSale
					.getProductQuantity()
					+ existingProductSale.getProductQuantity());
			existingProductSale.setTotalSaleAmount(productSale
					.getTotalSaleAmount()
					+ existingProductSale.getTotalSaleAmount());
			salesMessageLog.add(salesMessage);
		}

	}

	public static boolean doAdjustment(String salesMessage,
			Adjustments adjustments) {
		boolean returnStatus = false;
		ProductSale productSale = salesMap.get(adjustments.getProductName());

		if (productSale != null) {
			if (adjustments.getOperation().equals("Add")) {
				adjustments
						.setProductQuantity(productSale.getProductQuantity());
				adjustments.setAmountBeforeAdjustment(productSale
						.getTotalSaleAmount());
				adjustments.setAmountAfterAdjustment(productSale
						.getTotalSaleAmount()
						+ (adjustments.getAmount() * productSale
								.getProductQuantity()));
				productSale.setTotalSaleAmount(adjustments
						.getAmountAfterAdjustment());
				adjustmentReport.add(adjustments);
				returnStatus = true;
			}

			if (adjustments.getOperation().equals("Subtract")) {
				adjustments
						.setProductQuantity(productSale.getProductQuantity());
				adjustments.setAmountBeforeAdjustment(productSale
						.getTotalSaleAmount());
				adjustments.setAmountAfterAdjustment(productSale
						.getTotalSaleAmount()
						- (adjustments.getAmount() * productSale
								.getProductQuantity()));
				productSale.setTotalSaleAmount(adjustments
						.getAmountAfterAdjustment());
				adjustmentReport.add(adjustments);
				returnStatus = true;
			}

			if (adjustments.getOperation().equals("Multiply")) {
				adjustments
						.setProductQuantity(productSale.getProductQuantity());
				adjustments.setAmountBeforeAdjustment(productSale
						.getTotalSaleAmount());
				adjustments.setAmountAfterAdjustment(productSale
						.getTotalSaleAmount()
						+ (productSale.getTotalSaleAmount() * adjustments
								.getAmount())
						+ (productSale.getProductQuantity() * adjustments
								.getAmount()));
				productSale.setTotalSaleAmount(adjustments
						.getAmountAfterAdjustment());
				adjustmentReport.add(adjustments);
				returnStatus = true;
			}

		}
		return returnStatus;
	}

	public static void printSale() {
		Iterator iterator = salesMap.values().iterator();
		System.out.println("============== Log Report ===============");
		System.out.println("|Product           |Quantity   |Value    |");

		while (iterator.hasNext()) {
			ProductSale productSale = (ProductSale) iterator.next();
			String lineItem = String.format("|%-18s|%-11d|%-11.2f|",
					productSale.getProductName(),
					productSale.getProductQuantity(),
					productSale.getTotalSaleAmount());
			System.out.println(lineItem);
		}
	}

	public static void printAdjustments() {
		Iterator iterator = adjustmentReport.iterator();
		while (iterator.hasNext()) {
			Adjustments adjustments = (Adjustments) iterator.next();
			String adjustmentReport = String
					.format("Performed %s %.2fp to %d %s and price adjusted from %.2fp to %.2fp",
							adjustments.getOperation(),
							adjustments.getAmount(),
							adjustments.getProductQuantity(),
							adjustments.getProductName(),
							adjustments.getAmountBeforeAdjustment(),
							adjustments.getAmountAfterAdjustment());
			System.out.println(adjustmentReport);
		}
	}
}
