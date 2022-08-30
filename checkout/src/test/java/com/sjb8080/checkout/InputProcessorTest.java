package com.sjb8080.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class InputProcessorTest {

  private String skus_ABCD = "A\nB\nC\nD\n\n";
  private String skus_AAAA = "A\nA\nA\nA\n\n";
  private String skus_AAAAAA = "A\nA\nA\nA\nA\nA\n\n";
  private String skus_DCBA = "D\nC\nB\nA\n\n";

  @Test
  void testProcessInput() 
  {
    testWithFile(skus_ABCD, 115);
    testWithFile(skus_AAAA, 180);
    testWithFile(skus_AAAAAA, 260);
    testWithFile(skus_DCBA, 115);
  }

  private void testWithFile(String file, int expectedTotal)
  {
    try
    {
      PriceList priceList = new PriceList();
      priceList.readPriceList("..\\priceList.csv");

      IPriceCalculator priceCalculator = new PriceCalculator();
        
      Receipt receipt = new Receipt(priceList, priceCalculator);

      InputProcessor inputProcessor = new InputProcessor(receipt);

      Scanner scanner = new Scanner(file);

      while (inputProcessor.processInput(scanner)) {}

      assertEquals(expectedTotal, receipt.getTotal());
    }
    catch (Exception e)
    {
      assertTrue(false, "Exception : " + e.toString());
    }
  }
}
