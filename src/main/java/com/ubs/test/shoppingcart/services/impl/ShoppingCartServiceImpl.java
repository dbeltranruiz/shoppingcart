package com.ubs.test.shoppingcart.services.impl;

import com.ubs.test.shoppingcart.domain.Item;
import com.ubs.test.shoppingcart.domain.PricePolicy;
import com.ubs.test.shoppingcart.domain.ShoppingSummary;
import com.ubs.test.shoppingcart.repository.PricingRepository;
import com.ubs.test.shoppingcart.repository.ShoppingSession;
import com.ubs.test.shoppingcart.services.UndefinedNormalPriceException;
import com.ubs.test.shoppingcart.services.ShoppingCartService;

public class ShoppingCartServiceImpl implements ShoppingCartService {

	private ShoppingSession session;
	private PricingRepository pricingRepo;
	
	public ShoppingCartServiceImpl(PricingRepository pricingRepo) {
		super();
		this.pricingRepo = pricingRepo;
		createOrClearSession();
	}

	@Override
	public void createOrClearSession() {
		session = new ShoppingSession();
	}

	@Override
	public int addItemByAmount(Item item, int amount) {
		return session.addItemByAmount(item, amount);
	}

	@Override
	public int removeItemByAmount(Item item, int amount) {
		return session.removeItemByAmount(item, amount);
	}
	
	@Override
	public ShoppingSummary getShoppingSummary() {
		ShoppingSummary summary =  new ShoppingSummary();
		
		double totalPrice = calculatePricing(summary);
	    summary.setTotalPrice(totalPrice);
		
		return summary;
	}
	
	@Override
	public double calculateSessionPrice() {
		return calculatePricing(null);
	}
	
	class RowPriceAndItems{
		int rowItems;
		double rowPrice;
		
		public RowPriceAndItems(int rowItems, double rowPrice) {
			super();
			this.rowItems = rowItems;
			this.rowPrice = rowPrice;
		}
	}
	
	private double calculatePricing(ShoppingSummary summary) {
		double totalPrice = 0;
		
		for(Item item : session.getItems()) {
			int amount = session.getItemAmount(item);
			while(amount > 0) {
				RowPriceAndItems row = applyNextPricingPolicy(item, amount, summary);
				totalPrice += row.rowPrice;
				amount -= row.rowItems;
			}
		}
		
		return totalPrice;
	}
	
	private RowPriceAndItems applyNextPricingPolicy(Item item, int amount, ShoppingSummary summary)
	{
		final PricePolicy pricingPolicy = pricingRepo.getPricePolicyFor(item, amount);
		if(pricingPolicy == null)
			throw new UndefinedNormalPriceException("Normal price not defined for item " + item.getId());
		
		final int amountOfOffers = amount / pricingPolicy.getAmount();
		final int rowItems = amountOfOffers * pricingPolicy.getAmount();
		final double rowPrice = pricingPolicy.getPrice() * amountOfOffers;
		if(summary != null)
			summary.addRow(item, rowItems, rowPrice);
		
		return new RowPriceAndItems(rowItems, rowPrice);
	}
	
}
