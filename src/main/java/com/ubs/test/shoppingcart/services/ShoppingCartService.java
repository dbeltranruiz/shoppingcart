package com.ubs.test.shoppingcart.services;

import com.ubs.test.shoppingcart.domain.Item;
import com.ubs.test.shoppingcart.domain.ShoppingSummary;

public interface ShoppingCartService {

	/**
	 *  Create a blank session for the shopping cart. All the data from the previous session if any sis discharded.
	 */
	void createOrClearSession();

	/**
	 * Add an amount of items to the cart.
	 * 
	 * @param item The item to be added into the cart.
	 * @param amount The amount of items to be added into the cart.
	 * @return The numbers of units of the particular item in the cart after the addition. 
	 */
	int addItemByAmount(Item item, int amount);

	/**
	 * Remove an amount of items from the cart.
	 * 
	 * @param item The item to be removed from the cart.
	 * @param amount The amount of units of the item to be removed from the cart.
	 * @return The numbers of units of the particular item in the cart after the substraction.
	 */
	int removeItemByAmount(Item item, int amount);

	/**
	 * Create a shopping summary with a row for every particular item and price. Disccounts offers are returned in separated rows.
	 * 
	 * @return The shopping summary based on the content of the cart.
	 * @throws UndefinedNormalPriceException in case there is no standar price
	 *         defined for a given item in the cart and therefore calculation can't be completed.
	 */
	ShoppingSummary getShoppingSummary();
	
	/**
	 * Calculate the total price based on the content of the cart
	 * 
	 * @return The total price of the content of the cart.
	 * @throws UndefinedNormalPriceException in case there is no standar price
	 *         defined for a given item in the cart and therefore calculation can't be completed.
	 */
	double calculateSessionPrice();
}