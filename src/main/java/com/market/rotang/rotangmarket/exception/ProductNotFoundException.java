package com.market.rotang.rotangmarket.exception;

public class ProductNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Product with id %d not found";

    public ProductNotFoundException(Long id) {
        super(String.format(MESSAGE, id));
    }

}
