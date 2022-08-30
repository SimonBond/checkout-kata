package com.sjb8080.checkout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collection;

public class ReceiptTest {
  private static PriceRecord priceA;
  private static PriceRecord priceB;
  private static PriceRecord priceC;
  private static PriceRecord priceD;

  private static PriceList priceList;

  static {
    try
    {
      priceA = new PriceRecord("A", 50, 3, 130);
      priceB = new PriceRecord("B", 30, 2, 45);
      priceC = new PriceRecord("C", 20);
      priceD = new PriceRecord("D", 15);

      priceList = new PriceList();
      priceList.addPriceRecord(priceA);
      priceList.addPriceRecord(priceB);
      priceList.addPriceRecord(priceC);
      priceList.addPriceRecord(priceD);
    }
    catch (Exception e)
    {}
  }

  @Test
  public void testConstructor()
  {
    try
    {
      PriceCalculator priceCalculator = new PriceCalculator();
      
      Receipt receipt = new Receipt(priceList, priceCalculator);

      assertNotNull(receipt);
    }
    catch (Exception e)
    {}
  }

  @Test
  public void testAddItem()
  {
    try
    {
      PriceCalculator priceCalculator = new PriceCalculator();
      
      Receipt receipt = new Receipt(priceList, priceCalculator);

      receipt.addItem("A");
      Collection<ReceiptLine> lines = receipt.getLines();
      assertEquals(1, lines.size());
      ReceiptLine line = find(lines, "A");
      assertNotNull(line);
      assertEquals(50, line.price);

      // Add another "A", no extra line
      receipt.addItem("A");
      lines = receipt.getLines();
      assertEquals(1, lines.size());
      line = find(lines, "A");
      assertNotNull(line);
      assertEquals(100, line.price);

      // Add another "A", no extra line, multi-buy price
      receipt.addItem("A");
      lines = receipt.getLines();
      assertEquals(1, lines.size());
      line = find(lines, "A");
      assertNotNull(line);
      assertEquals(130, line.price);
    }
    catch (Exception e)
    {}
  }

  @Test
  public void testMultipleItems()
  {
    try
    {
      PriceCalculator priceCalculator = new PriceCalculator();
      
      Receipt receipt = new Receipt(priceList, priceCalculator);

      receipt.addItem("A");
      receipt.addItem("B");
      receipt.addItem("A");
      receipt.addItem("C");

      Collection<ReceiptLine> lines = receipt.getLines();
      assertEquals(3, lines.size());
      ReceiptLine line = find(lines, "A");
      assertNotNull(line);
      assertEquals(100, line.price);
      line = find(lines, "B");
      assertNotNull(line);
      assertEquals(30, line.price);
      line = find(lines, "C");
      assertNotNull(line);
      assertEquals(20, line.price);

      int total = receipt.getTotal();
      assertEquals(150, total);
    }
    catch (Exception e)
    {}
  }

  private ReceiptLine find(Collection<ReceiptLine> lines, String sku)
  {
    for (ReceiptLine line : lines)
    {
      if (line.sku.equals(sku))
      {
        return line;
      }
    }
    return null;
  }
}
