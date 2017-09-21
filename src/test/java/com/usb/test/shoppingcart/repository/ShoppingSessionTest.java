package com.usb.test.shoppingcart.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ubs.test.shoppingcart.domain.Item;
import com.ubs.test.shoppingcart.repository.ShoppingSession;

public class ShoppingSessionTest {

	ShoppingSession shoppingSession;
	Item item1;
	Item item2;
	
	@Before
	public void setUp() throws Exception {
		shoppingSession = new ShoppingSession();
		item1 = new Item("A", "A name", "A description");
		item2 = new Item("B", "A name", "A description");
	}

	@Test
	public void shouldAddItemsByTheGivenAmount() {
		assertEquals(1, shoppingSession.addItemByAmount(item1, 1));
		assertEquals(11, shoppingSession.addItemByAmount(item1, 10));
		assertEquals(11, shoppingSession.getItemAmount(item1));
		
		assertEquals(10, shoppingSession.addItemByAmount(item2, 10));
		assertEquals(11, shoppingSession.getItemAmount(item1));
		assertEquals(10, shoppingSession.getItemAmount(item2));
	}

	@Test
	public void shouldRemoveItemsByTheGivenAmount() {
		assertEquals(10, shoppingSession.addItemByAmount(item1, 10));
		assertEquals(5, shoppingSession.removeItemByAmount(item1, 5));
		assertEquals(5, shoppingSession.getItemAmount(item1));
		
		assertEquals(5, shoppingSession.removeItemByAmount(item1, 6));
		assertEquals(0, shoppingSession.getItemAmount(item1));
	}

}
