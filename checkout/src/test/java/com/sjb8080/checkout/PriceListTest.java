package com.sjb8080.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PriceListTest {
  @Test
  void testAddPriceRecord() 
  {

    try
    {
      IPriceList priceList = new PriceList();

      IPriceRecord a = new PriceRecord("A", 55);
      IPriceRecord b = new PriceRecord("B", 20, 4, 50);

      priceList.addPriceRecord(a);
      priceList.addPriceRecord(b);

      assertEquals(a, priceList.getPriceRecord("A"));
      assertEquals(b, priceList.getPriceRecord("B"));
    }
    catch (Exception e)
    {
      assertTrue(false, "Exception:" + e.toString());
    }

  }

  @Test
  void testReadPriceList_and_getPriceRecord() {
    IPriceList priceList = new PriceList();
    try
    {
      priceList.readPriceList("..\\priceList.csv");

      IPriceRecord a = priceList.getPriceRecord("A");
      IPriceRecord b = priceList.getPriceRecord("B");
      IPriceRecord c = priceList.getPriceRecord("C");
      IPriceRecord d = priceList.getPriceRecord("D");

      assertEquals(50, a.getUnitPrice());
      assertEquals(130, a.getMultiBuy(3).price);
      assertEquals(30, b.getUnitPrice());
      assertEquals(45, b.getMultiBuy(2).price);
      assertEquals(20, c.getUnitPrice());
      assertEquals(15, d.getUnitPrice());
    }
    catch (Exception e)
    {
      assertTrue(false, "Exception:" + e.toString());
    }
  }

  @Test
  void testGetVersion() {
    IPriceList priceList = new PriceList();
    assertEquals(0, priceList.getVersion());
  }
}
