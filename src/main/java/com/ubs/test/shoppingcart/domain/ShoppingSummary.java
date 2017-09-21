package com.ubs.test.shoppingcart.domain;

import java.util.ArrayList;
import java.util.List;

public class ShoppingSummary {
	public class ShoppingRow{
		private Item item;
		private int amount;
		private double rowPrice;
		
		public ShoppingRow(Item item, int amount, double price) {
			super();
			this.item = item;
			this.amount = amount;
			this.rowPrice = price;
		}

		public Item getItem() {
			return item;
		}

		public int getAmount() {
			return amount;
		}

		public double getRowPrice() {
			return rowPrice;
		}

		@Override
		public String toString() {
			return "ShoppingRow [item=" + item + ", amount=" + amount + ", rowPrice=" + rowPrice + "]";
		}
		
	}
	
	private List<ShoppingRow> shoppingRows = new ArrayList<ShoppingRow>();
	private double totalPrice;
	
	public void addRow(Item item, int amount, double price){
		shoppingRows.add(new ShoppingRow(item, amount, price));
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<ShoppingRow> getShoppingRows() {
		return shoppingRows;
	}

	@Override
	public String toString() {
		return "ShoppingSummary [shoppingRows=" + shoppingRows + ", totalPrice=" + totalPrice + "]";
	} 
}