package com.sjb8080.checkout;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiBuyTest {

  @Test
  public void testConstructor() {
    try
    {
      MultiBuy multiPrice = new MultiBuy(2, 130);

      assertEquals(2, multiPrice.units);
      assertEquals(130, multiPrice.price);
    }
    catch (Exception e)
    {
      assertTrue(false, "Exception: " + e.toString());
    }
  }

  @Test
  public void testConstructorFail() {
    assertThrows(InstantiationException.class, () -> new MultiBuy(0, 130));
    assertThrows(InstantiationException.class, () -> new MultiBuy(1, -1));
  }
}
