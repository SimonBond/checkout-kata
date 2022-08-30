package com.sjb8080.checkout;

public interface IPriceCalculator {
  int calculatePrice(int units, IPriceRecord priceRecord);
}
