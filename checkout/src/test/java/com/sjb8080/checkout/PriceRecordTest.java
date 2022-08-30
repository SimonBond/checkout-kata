package com.sjb8080.checkout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PriceRecordTest {
  @Test
  public void testConstructorFull()
  {
    try
    {
      PriceRecord priceRecord = new PriceRecord("A", 50, 3, 130);

      assertNotNull(priceRecord);
      assertEquals("A", priceRecord.getSku());
      assertEquals(50, priceRecord.getUnitPrice());
      MultiBuy multiBuy = priceRecord.getMultiBuy(3);
      assertEquals(3, multiBuy.units);
      assertEquals(130, multiBuy.price);
    }
    catch (Exception e)
    {}
  }

  @Test
  public void testConstructorShort()
  {
    PriceRecord priceRecord = new PriceRecord("A", 50);

    assertNotNull(priceRecord);
    assertEquals("A", priceRecord.getSku());
    assertEquals(50, priceRecord.getUnitPrice());
    MultiBuy multiBuy = priceRecord.getMultiBuy(1);
    assertNull(multiBuy);
  }
}
