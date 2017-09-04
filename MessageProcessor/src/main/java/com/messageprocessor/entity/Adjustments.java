package com.messageprocessor.entity;

public class Adjustments {

	private String operation;
	private String productName;
	private double amount;
	private double amountBeforeAdjustment;
	private double amountAfterAdjustment;
	private int productQuantity;

	public Adjustments(String operation, String productName, double amount) {
		this.amount = amount;
		this.operation = operation;
		this.productName = productName;
	}

	public Adjustments(String operation, String productName, double amount,
			double amountBeforeAdjustment, double amountAfterAdjustment,
			int currentQuantity) {
		this.amount = amount;
		this.operation = operation;
		this.productName = productName;
		this.amountAfterAdjustment = amountAfterAdjustment;
		this.amountBeforeAdjustment = amountBeforeAdjustment;
		this.productQuantity = currentQuantity;
	}

	public String getOperation() {
		return operation;
	}

	public String getProductName() {
		return productName;
	}

	public double getAmount() {
		return amount;
	}

	public double getAmountBeforeAdjustment() {
		return amountBeforeAdjustment;
	}

	public void setAmountBeforeAdjustment(double amountBeforeAdjustment) {
		this.amountBeforeAdjustment = amountBeforeAdjustment;
	}

	public double getAmountAfterAdjustment() {
		return amountAfterAdjustment;
	}

	public void setAmountAfterAdjustment(double amountAfterAdjustment) {
		this.amountAfterAdjustment = amountAfterAdjustment;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

}
