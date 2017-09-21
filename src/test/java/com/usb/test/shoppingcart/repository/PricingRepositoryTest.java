package com.usb.test.shoppingcart.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ubs.test.shoppingcart.domain.Item;
import com.ubs.test.shoppingcart.domain.PricePolicy;
import com.ubs.test.shoppingcart.repository.PricingRepository;

public class PricingRepositoryTest {
 
	PricePolicy policy;
	Item item;
	private PricingRepository repo;
	
	@Before
	public void setUp() throws Exception {
		item = new Item("A", "A name", "A description");
		policy = new PricePolicy(item, 1, 1.0);
		repo = new PricingRepository();
	}

	@Test
	public void shouldAddPolicyIfDoesntExist() {	
		assertTrue(repo.addPricingPolicy(policy));
		assertEquals(1.0, repo.getPricePolicyFor(item, 1).getPrice(), 0.0);
	}

	@Test
	public void shouldNotAddPolicyIfAlreadyExist() {
	    repo.addPricingPolicy(policy);
		assertFalse(repo.addPricingPolicy(new PricePolicy(item,1)));
		assertEquals(1.0, repo.getPricePolicyFor(item, 1).getPrice(), 0.0);
	}

	@Test
	public void shouldReturnProperPolicyDependingOfTheAmount() {
		assertTrue(repo.addPricingPolicy(policy));
		assertTrue(repo.addPricingPolicy(new PricePolicy(item, 10, 10.0)));
		assertTrue(repo.addPricingPolicy(new PricePolicy(item, 20, 20.0)));
		assertEquals(1.0, repo.getPricePolicyFor(item, 1).getPrice(), 0.0);
		assertEquals(1.0, repo.getPricePolicyFor(item, 2).getPrice(), 0.0);
		assertEquals(10.0, repo.getPricePolicyFor(item, 10).getPrice(), 0.0);
		assertEquals(10.0, repo.getPricePolicyFor(item, 11).getPrice(), 0.0);
		assertEquals(20.0, repo.getPricePolicyFor(item, 20).getPrice(), 0.0);
	}

	@Test
	public void returnProperPolicyDependingOfTheAmountAndTheType() {
		Item item2 = new Item("B", "A name", "A description");
		
		assertTrue(repo.addPricingPolicy(policy));
		assertTrue(repo.addPricingPolicy(new PricePolicy(item2, 1, 10.0)));
		
		assertEquals(1.0, repo.getPricePolicyFor(item, 1).getPrice(), 0.0);
		assertEquals(10.0, repo.getPricePolicyFor(item2, 1).getPrice(), 0.0);
	}
}
