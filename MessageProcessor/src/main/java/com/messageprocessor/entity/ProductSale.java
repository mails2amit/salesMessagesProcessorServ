package com.messageprocessor.entity;

public class ProductSale {

	private final String productName;
	private int productQuantity;
	private double totalSaleAmount;

	public ProductSale(String productName, double totalSaleAmount,
			int productQuantity) {
		this.productName = productName;
		this.totalSaleAmount = totalSaleAmount;
		this.productQuantity = productQuantity;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public double getTotalSaleAmount() {
		return totalSaleAmount;
	}

	public void setTotalSaleAmount(double totalSaleAmount) {
		this.totalSaleAmount = totalSaleAmount;
	}

	public String getProductName() {
		return productName;
	}

}
