package com.sjb8080.checkout;

import java.util.Scanner;

public class Checkout 
{
  private static IPriceList priceList;
  private static IPriceCalculator priceCalculator;
  private static IReceipt receipt;
  
  static
  {
    priceCalculator = new PriceCalculator();
    priceList = new PriceList();

    try{
      priceList.readPriceList("priceList.csv");
      receipt = new Receipt(priceList, priceCalculator);
    }
    catch (Exception e) 
    {
      System.out.println("Exception");
      System.out.println(e.toString());
      System.exit(1);
    }
  }

  public static void main(String[] args)
  {
    System.out.println("Enter skus, one per line. Empty line to get total and finish");
    Scanner scanner = new Scanner(System.in);
    IInputProcessor inputProcessor = new InputProcessor(receipt);

    try
    {
      boolean ok = true;
      while (ok)
      {
        try
        {
          ok = inputProcessor.processInput(scanner);
          System.out.println("sub-total: " + receipt.getTotal());
        }
        catch (InvalidSku e)
        {
          System.out.println("Unrecognised item");
        }
        catch (PriceMissingException e)
        {
          System.out.println("We have no price for that item");
        }
      }

      System.out.println("=================");
      System.out.println("TOTAL: " + receipt.getTotal());
    }
    finally
    {
      scanner.close();
    }
  }

}
