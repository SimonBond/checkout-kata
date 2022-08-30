package com.sjb8080.checkout;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceCalculatorTest 
{
  @Test
  public void testCalculatePriceWithMultiBuy()
  {
    try
    {
      PriceCalculator priceCalculator = new PriceCalculator();

      PriceRecord priceRecord = new PriceRecord("A", 50, 3, 130);
      int priceFor1 = priceCalculator.calculatePrice(1, priceRecord);
      assertEquals(50, priceFor1);
      int priceFor2 = priceCalculator.calculatePrice(2, priceRecord);
      assertEquals(100, priceFor2);
      int priceFor3 = priceCalculator.calculatePrice(3, priceRecord);
      assertEquals(130, priceFor3);
      int priceFor4 = priceCalculator.calculatePrice(4, priceRecord);
      assertEquals(180, priceFor4);
    }
    catch (Exception e)
    {}
  }

  @Test
  public void testCalculatePriceWithUnitPrice()
  {
    try
    {
      PriceCalculator priceCalculator = new PriceCalculator();

      PriceRecord priceRecord = new PriceRecord("A", 50);
      int priceFor1 = priceCalculator.calculatePrice(1, priceRecord);
      assertEquals(50, priceFor1);
      int priceFor2 = priceCalculator.calculatePrice(2, priceRecord);
      assertEquals(100, priceFor2);
      int priceFor3 = priceCalculator.calculatePrice(3, priceRecord);
      assertEquals(150, priceFor3);
      int priceFor4 = priceCalculator.calculatePrice(4, priceRecord);
      assertEquals(200, priceFor4);
    }
    catch (Exception e)
    {}
  }
}
