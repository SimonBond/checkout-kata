package com.sjb8080.checkout;

import java.io.IOException;

public interface IPriceList {
  int getVersion();
  IPriceRecord getPriceRecord(String sku);
  void addPriceRecord(IPriceRecord priceRecord);
  void readPriceList(String filePath) throws IOException, InstantiationException;
}
