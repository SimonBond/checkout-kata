package com.sjb8080.checkout;

import java.util.Collection;

public interface IReceipt {
  void addItem(String sku) throws InvalidSku, PriceMissingException;
  int getTotal();
  Collection<ReceiptLine> getLines();
}
