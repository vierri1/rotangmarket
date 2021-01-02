package com.market.rotang.rotangmarket.exception;

public class CategoryNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Category with id %d not found";

    public CategoryNotFoundException(Long id) {
        super(String.format(MESSAGE, id));
    }
}
