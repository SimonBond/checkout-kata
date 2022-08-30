package com.sjb8080.checkout;

@SuppressWarnings("empty")
public class InvalidSku extends Exception { 
  public InvalidSku(String sku) {
    super(sku);
  }
}
