package com.ubs.test.shoppingcart.services;

public class UndefinedNormalPriceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UndefinedNormalPriceException(String message) {
		super(message);
	}
}
