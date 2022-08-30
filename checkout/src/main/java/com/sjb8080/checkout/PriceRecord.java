package com.sjb8080.checkout;

public class PriceRecord implements IPriceRecord 
{
  private final String sku;
  private final int unitPrice;
  private final MultiBuy multiBuy;

  @SuppressWarnings("all")
  private PriceRecord()
  {
    sku = "";
    unitPrice = 0;
    multiBuy = null;
  }

  public PriceRecord(String sku, int unitPrice, int multiBuyUnits, int multiBuyPrice) throws InstantiationException
  {
    if (sku.length() != 1)
    {
      throw new InstantiationException("Invalid SKU");
    }
    if (unitPrice < 0)
    {
      throw new InstantiationException("Invalid Unit Price");
    }
    this.sku = sku;
    this.unitPrice = unitPrice;
    this.multiBuy = new MultiBuy(multiBuyUnits, multiBuyPrice);
  }

  public PriceRecord(String sku, int unitPrice)
  {
    this.sku = sku;
    this.unitPrice = unitPrice;
    this.multiBuy = null;
  }

  @Override
  public String getSku() {
    return sku;
  }

  @Override
  public int getUnitPrice() {
    return unitPrice;
  }

  @Override
  public MultiBuy getMultiBuy(int units) {
    if ((multiBuy != null) && (multiBuy.units <= units))
    {
      return multiBuy;
    }

    return null;
  }
  
  
}
