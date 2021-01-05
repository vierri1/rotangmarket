package com.market.rotang.rotangmarket.exception;

public class ImageNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Image with name %s not found";

    public ImageNotFoundException(String imageName) {
        super(String.format(MESSAGE, imageName));
    }
}
