package com.sjb8080.checkout;

import java.util.Scanner;

public class Checkout 
{
  private static PriceList priceList;
  private static IPriceCalculator priceCalculator;
  private static Receipt receipt;
  
  static
  {
    priceCalculator = new PriceCalculator();
    priceList = new PriceList();

    try{
      priceList.readPriceList("C:\\Source\\checkout-kata\\priceList.csv");
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
    Scanner input = new Scanner(System.in);

    try{
      boolean stop = false;
      while (!stop)
      {
        String line = input.nextLine();

        if (line.length() == 1)
        {
          try
          {
            receipt.addItem(line);
            System.out.println("total: " + receipt.getTotal());
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
        else if (line.length() == 0)
        {
          System.out.println("=================");
          System.out.println("TOTAL: " + receipt.getTotal());
          stop = true;
        }
        else
        {
          System.out.println("Unrecognised input");
        }
      }
    }
    finally
    {
      input.close();
    }
  }
}
