package com.ubs.test.shoppingcart.services;

import com.ubs.test.shoppingcart.domain.Item;
import com.ubs.test.shoppingcart.domain.ShoppingSummary;

public interface ShoppingCartService {

	void createOrClearSession();

	int addItemByAmount(Item item, int amount);

	int removeItemByAmount(Item item, int amount);

	ShoppingSummary getShoppingSummary();
	
	double calculateSessionPrice();

}