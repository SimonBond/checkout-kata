package com.sjb8080.checkout;

import java.util.Map;
import java.util.Collection;
import java.util.HashMap;

public class Receipt implements IReceipt {
  private Map<String, ReceiptLine> lines;
  private final IPriceList priceList;
  private final IPriceCalculator priceCalculator;
  
  public Receipt(IPriceList priceList, IPriceCalculator priceCalculator) throws InstantiationException
  {
    if ((priceList == null) || (priceCalculator == null))
    {
      throw new InstantiationException("dependence passed as null");
    }

    this.priceList = priceList;
    this.priceCalculator = priceCalculator;
    this.lines = new HashMap<>();
  }

  @Override
  public void addItem(String sku) throws InvalidSku, PriceMissingException {
    if (sku.length() != 1)
    {
      throw new InvalidSku(sku);
    }
    ReceiptLine receiptLine = lines.get(sku);
    IPriceRecord priceRecord = priceList.getPriceRecord(sku);

    if (priceRecord == null)
    {
      throw new PriceMissingException(sku);
    }
    if (receiptLine == null)
    {
      receiptLine = new ReceiptLine();
      receiptLine.sku = sku;
      receiptLine.noOfItems = 1;
      lines.put(sku, receiptLine);
    }
    else
    {
      ++receiptLine.noOfItems;
    }
    receiptLine.price = priceCalculator.calculatePrice(receiptLine.noOfItems, priceRecord);
  }

  @Override
  public int getTotal() {
    int total = 0;

    for (ReceiptLine line : lines.values())
    {
      total += line.price;
    }
    return total;
  }

  @Override
  public Collection<ReceiptLine> getLines() {
    return lines.values();
  }
  
}
