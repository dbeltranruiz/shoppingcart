package com.ubs.test.shoppingcart;

import com.ubs.test.shoppingcart.domain.Item;
import com.ubs.test.shoppingcart.domain.PricePolicy;
import com.ubs.test.shoppingcart.domain.ShoppingSummary;
import com.ubs.test.shoppingcart.repository.PricingRepository;
import com.ubs.test.shoppingcart.services.ShoppingCartService;
import com.ubs.test.shoppingcart.services.impl.ShoppingCartServiceImpl;

public class Main {
	private static PricingRepository initializePricingRepository() {
		PricingRepository pricingRepo = new PricingRepository();
		pricingRepo.addPricingPolicy(createPricingPolicy("A", 1, 40));
		pricingRepo.addPricingPolicy(createPricingPolicy("A", 3, 70));
		pricingRepo.addPricingPolicy(createPricingPolicy("B", 1, 10));
		pricingRepo.addPricingPolicy(createPricingPolicy("B", 2, 15));
		pricingRepo.addPricingPolicy(createPricingPolicy("C", 1, 30));
		pricingRepo.addPricingPolicy(createPricingPolicy("D", 1, 25));
		return pricingRepo;
	}

	private static PricePolicy createPricingPolicy(String itemId, int minimalAmount, double price) {
		return new PricePolicy(createItem(itemId), minimalAmount, price);
	}
	private static Item createItem(String id) {
		return new Item(id, "A name", "A description");	
	}
	
	public static void main(String[] args) {
		ShoppingCartService shoppingService = new ShoppingCartServiceImpl(initializePricingRepository());
		
		shoppingService.addItemByAmount(createItem("A"), 7);
		shoppingService.addItemByAmount(createItem("B"), 3);
		shoppingService.addItemByAmount(createItem("C"), 2);
		shoppingService.addItemByAmount(createItem("D"), 2);
		
		ShoppingSummary summary = shoppingService.getShoppingSummary();
		for(ShoppingSummary.ShoppingRow row: summary.getShoppingRows()) {
			System.out.println(row);
		}
		System.out.println("Total Price: " + summary.getTotalPrice());

	}

}
