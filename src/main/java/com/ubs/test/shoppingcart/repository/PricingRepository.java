package com.ubs.test.shoppingcart.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import com.ubs.test.shoppingcart.domain.Item;
import com.ubs.test.shoppingcart.domain.PricePolicy;

public class PricingRepository {
	private Map<Item, SortedSet<PricePolicy>> repo = new HashMap<Item, SortedSet<PricePolicy>>();

	public boolean addPricingPolicy(PricePolicy policy) {
		SortedSet<PricePolicy> set = getPolicySet(policy.getItem());
		return set.add(policy);
	}

	public PricePolicy getPricePolicyFor(Item item, int amount) {
		SortedSet<PricePolicy> set = getPolicySet(item);

		SortedSet<PricePolicy> headset = set.headSet(new PricePolicy(item, amount + 1));
		if(headset.size() > 0)
			return headset.last();
		else
			return null;
	}

	private SortedSet<PricePolicy> getPolicySet(Item item) {
		SortedSet<PricePolicy> set = repo.get(item);
		if (set == null) {
			set = new TreeSet<PricePolicy>();
			repo.put(item, set);
		}
		return set;
	}
}
