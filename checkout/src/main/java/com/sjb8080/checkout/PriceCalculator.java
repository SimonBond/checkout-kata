package com.sjb8080.checkout;

public class PriceCalculator implements IPriceCalculator{

  @Override
  public int calculatePrice(int units, IPriceRecord priceRecord) {
    if ((units == 0) || (priceRecord == null) )
    {
      return 0;
    }
    
    MultiBuy multiBuy = priceRecord.getMultiBuy(units);
    int price = 0;
    if (multiBuy != null)
    {
      int remainingUnits = units;
      while (remainingUnits >= multiBuy.units)
      {
        price += multiBuy.price;
        remainingUnits -= multiBuy.units;
      }
      price += priceRecord.getUnitPrice() * remainingUnits;
    }
    else
    {
      price = units * priceRecord.getUnitPrice();
    }
    return price;
  }
  
}
