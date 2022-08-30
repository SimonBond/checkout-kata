package com.sjb8080.checkout;

@SuppressWarnings("empty")
public class PriceMissingException extends Exception { 
  public PriceMissingException(String sku) {
    super(sku);
  }
}
