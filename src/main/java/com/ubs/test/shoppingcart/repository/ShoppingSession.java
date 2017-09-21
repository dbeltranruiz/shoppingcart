package com.ubs.test.shoppingcart.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.ubs.test.shoppingcart.domain.Item;

public class ShoppingSession {
	Map<Item, Integer> shoppingCart = new HashMap<Item, Integer>();

	public int addItemByAmount(Item item, int incrAmount) {
		Integer currentAmount = shoppingCart.get(item);
		if (currentAmount == null) {
			shoppingCart.put(item, incrAmount);
			return incrAmount;
		} else {
			shoppingCart.put(item, currentAmount + incrAmount);
			return currentAmount + incrAmount;
		}
	}

	public int removeItemByAmount(Item item, int decrAmount) {
		Integer currentAmount = shoppingCart.get(item);
		if (currentAmount != null)
			if (currentAmount < decrAmount) {
				shoppingCart.remove(item);
				return currentAmount;
			} else {
				shoppingCart.put(item, currentAmount - decrAmount);
				return currentAmount - decrAmount;
			}
		else {
			return 0;
		}
	}

	public Set<Item> getItems() {
		return shoppingCart.keySet();
	}

	public int getItemAmount(Item item) {
		Integer amount = shoppingCart.get(item);
		if (amount == null)
			return 0;
		return amount;
	}
}