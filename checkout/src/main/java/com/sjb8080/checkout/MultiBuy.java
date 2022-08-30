package com.sjb8080.checkout;

public class MultiBuy {
  public final int units;
  public final int price;

  @SuppressWarnings("unused")
  private MultiBuy() 
  {
    this.units = 0;
    this.price = 0;
  }

  public MultiBuy(int units, int price) throws InstantiationException
  {
    if ((units <= 0) || (price < 0))
    {
      throw new InstantiationException("Invalid units or price for MultiBuy");
    }
    else
    {
      this.units = units;
      this.price = price;
    }
  }
}
