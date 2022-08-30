package com.sjb8080.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PriceListTest {
  @Test
  void testAddPriceRecord() {

  }

  @Test
  void testGetPriceRecord() {

  }

  @Test
  void testReadPriceList() {
    PriceList priceList = new PriceList();
    try
    {
      priceList.readPriceList("C:\\Source\\checkout-kata\\priceList.csv");

      PriceRecord a = priceList.getPriceRecord("A");
      PriceRecord b = priceList.getPriceRecord("B");
      PriceRecord c = priceList.getPriceRecord("C");
      PriceRecord d = priceList.getPriceRecord("D");

      assertEquals(50, a.getUnitPrice());
      assertEquals(130, a.getMultiBuy(3).price);
      assertEquals(15, d.getUnitPrice());
    }
    catch (Exception e)
    {
      assertTrue(false, "Exception:" + e.toString());
    }
  }

  @Test
  void testSetVersion() {
    PriceList priceList = new PriceList();

    priceList.setVersion(1);
    assertEquals(1, priceList.getVersion());
  }

  @Test
  void testGetVersion() {
    PriceList priceList = new PriceList();
    assertEquals(0, priceList.getVersion());
    priceList.setVersion(1);
    assertEquals(1, priceList.getVersion());
  }
}
