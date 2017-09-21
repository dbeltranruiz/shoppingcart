package com.usb.test.shoppingcart.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ubs.test.shoppingcart.domain.Item;
import com.ubs.test.shoppingcart.domain.PricePolicy;

public class PricePolicyTest {
	
	private Item item;

	@Before
	public void setUp() throws Exception {
		item = new Item("A", "A name", "A description");
	}
	
	@Test
	public void shouldBeEqualAndMatchHashesThanOtherPricingPolicyIfItemAndAmountTheyAreTheSame() {
		
		PricePolicy pricingPolicy1 = new PricePolicy(item, 1);
		PricePolicy pricingPolicy2 = new PricePolicy(item, 1);
		
		assertEquals(pricingPolicy1, pricingPolicy2);
		assertEquals(pricingPolicy1.hashCode(), pricingPolicy2.hashCode());
	}	

	@Test
	public void shouldNotBeEqualAndNotMatchHashesThanOtherPricingPolicyIfItemisTheSameButAmountisDifferent() {
		PricePolicy pricingPolicy1 = new PricePolicy(item, 1);
		PricePolicy pricingPolicy2 = new PricePolicy(item, 2);
		
		assertNotEquals(pricingPolicy1, pricingPolicy2);
		assertNotEquals(pricingPolicy1.hashCode(), hashCode());
	}	
	
	@Test
	public void shouldNotBeEqualAndNotMatchHashesThanOtherPricingPolicyIfAmountisTheSameButItemisDifferent() {
		PricePolicy pricingPolicy1 = new PricePolicy(item, 1);
		PricePolicy pricingPolicy2 = new PricePolicy(new Item("B", "A name", "A description"), 1);
		
		assertNotEquals(pricingPolicy1, pricingPolicy2);
		assertNotEquals(pricingPolicy1.hashCode(), hashCode());
	}

	@Test
	public void shouldBeOderedBeforeIfAmountIsSmaller() {
		PricePolicy pricingPolicy1 = new PricePolicy(item, 1);
		PricePolicy pricingPolicy2 = new PricePolicy(item, 2);
		
		assertTrue(pricingPolicy1.compareTo(pricingPolicy2) < 0);
	}
}
