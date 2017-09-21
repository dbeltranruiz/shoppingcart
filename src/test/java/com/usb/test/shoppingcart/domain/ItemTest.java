package com.usb.test.shoppingcart.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ubs.test.shoppingcart.domain.Item;

public class ItemTest {

	@Test
	public void shouldBeEqualAndMatchHashesThanOtherItemIfIdTheyAreTheSame() {
		Item item1 = new Item("A", "Some name", "Some description");
		Item item2 = new Item("A", "Another name", "Anpther description");
		assertEquals(item1, item2);
		assertEquals(item1.hashCode(), item2.hashCode());
	}

	@Test
	public void shouldNotBeEqualAndNotMatchHashesThanOtherItemIfTheyAreDifferent() {
		Item item1 = new Item("A", "Some name", "Some description");
		Item item2 = new Item("B", "Some name", "Some description");
		assertNotEquals(item1, item2);
		assertNotEquals(item1.hashCode(), item2.hashCode());
	}
}
