package com.ubs.test.shoppingcart.domain;

public class PricePolicy implements Comparable<PricePolicy> {
	private Item item;
	private int amount;
	private double price;
	
	
	public PricePolicy(Item item, int amount, double price) {
		super();
		this.item = item;
		this.amount = amount;
		this.price = price;
	}
	
	public PricePolicy(Item item, int amount) {
		super();
		this.item = item;
		this.amount = amount;
		this.price = 0;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Item getItem() {
		return item;
	}

	public int getAmount() {
		return amount;
	}

	public int compareTo(PricePolicy o) {
		if (o.getItem().equals(o.getItem()))
		 return Integer.compare(this.amount, o.amount);
		else
	     return this.getItem().getId().compareTo(o.getItem().getId());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + amount;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PricePolicy other = (PricePolicy) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (amount != other.amount)
			return false;
		return true;
	}	
}
