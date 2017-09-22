package com.usb.test.shoppingcart.services.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ubs.test.shoppingcart.domain.Item;
import com.ubs.test.shoppingcart.domain.PricePolicy;
import com.ubs.test.shoppingcart.domain.ShoppingSummary;
import com.ubs.test.shoppingcart.repository.PricingRepository;
import com.ubs.test.shoppingcart.services.ShoppingCartService;
import com.ubs.test.shoppingcart.services.UndefinedNormalPriceException;
import com.ubs.test.shoppingcart.services.impl.ShoppingCartServiceImpl;

public class ShoppingCartServiceImplTest {

	PricingRepository pricingRepo;
	ShoppingCartService service;
	Item itemA;
	Item itemB;
	
	@Before
	public void setUp() throws Exception {
		pricingRepo = new PricingRepository();
		
		pricingRepo.addPricingPolicy(createPricingPolicy("A",1, 1));
		pricingRepo.addPricingPolicy(createPricingPolicy("A",10, 5));
		pricingRepo.addPricingPolicy(createPricingPolicy("B",1, 2));
		
		service = new ShoppingCartServiceImpl(pricingRepo);
		
		itemA = createItem("A");
		itemB = createItem("B");
	}

	
	@Test
	public void shouldAddAndRemoveItemsToCart() {
		assertEquals(1, service.addItemByAmount(itemA, 1));
		assertEquals(12, service.addItemByAmount(itemA, 11));
		assertEquals(10, service.addItemByAmount(itemB, 10));
		assertEquals(10, service.removeItemByAmount(itemA, 2));
		
		ShoppingSummary summary = service.getShoppingSummary();
		assertEquals(2, summary.getShoppingRows().size());
		
		for(ShoppingSummary.ShoppingRow row:  summary.getShoppingRows()) {
			if (row.getItem().equals(itemA)) {
				assertEquals(10, row.getAmount());
				assertEquals(5, row.getRowPrice(), 0.0);
			}
			else {
				assertEquals(10, row.getAmount());
				assertEquals(20, row.getRowPrice(), 0.0);
			}
		}
		
	}
	
	@Test
	public void shouldCalculateTotal() {
		assertEquals(11, service.addItemByAmount(createItem("A"), 11));
		assertEquals(1, service.addItemByAmount(createItem("B"), 1));
		
		assertEquals(8, service.calculateSessionPrice(), 0.0);
		
		assertEquals(2, service.addItemByAmount(createItem("B"), 1));
		
		ShoppingSummary summary = service.getShoppingSummary();
		assertEquals(10, summary.getTotalPrice(), 0.0);
	}

	@Test
	public void shouldClearServicel() {
		assertEquals(11, service.addItemByAmount(createItem("A"), 11));
		service.createOrClearSession();
		assertEquals(11, service.addItemByAmount(createItem("A"), 11));
	}
	
	@Test(expected=UndefinedNormalPriceException.class)
	public void shouldThrowInCaseThereIsNotStandarPriceForAGivenItemWhenTotalPriceCalculationIsInvoked() {
		assertEquals(1, service.addItemByAmount(createItem("C"), 1));
		service.calculateSessionPrice();
	}
	
	@Test(expected=UndefinedNormalPriceException.class)
	public void shouldThrowInCaseThereIsNotStandarPriceForAGivenItemWhenShoppingSummaryCalculationIsInvoked() {
		assertEquals(1, service.addItemByAmount(createItem("C"), 1));
		service.calculateSessionPrice();
	}
	
	private PricePolicy createPricingPolicy(String itemId, int minimalAmount, double price) {
		return new PricePolicy(createItem(itemId), minimalAmount, price);
	}
	
	private Item createItem(String id) {
		return new Item(id, "A name", "A description");	
	}
	
}
