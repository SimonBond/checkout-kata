package com.sjb8080.checkout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PriceList implements IPriceList{
  private final int version;
  private final Map<String, IPriceRecord> priceRecords;

  public PriceList()
  {
    version = 0;
    priceRecords = new HashMap<>();
  }

  @Override
  public int getVersion()
  {
    return this.version;
  }

  @Override
  public IPriceRecord getPriceRecord(String sku)
  {
    return priceRecords.get(sku);
  }

  @Override
  public void addPriceRecord(IPriceRecord priceRecord)
  {
    priceRecords.put(priceRecord.getSku(), priceRecord);
  }

  @Override
  public void readPriceList(String filePath) throws IOException, InstantiationException, InvalidSku
  {
    try (
      FileReader in = new FileReader(filePath);
      BufferedReader buffReader = new BufferedReader(in))
    {
      while (buffReader.ready()) 
      {
        String line = buffReader.readLine();

        String[] parts = line.split(",");

        if (parts.length == 2)
        {
          String sku = parts[0].trim();
          if ((sku.length() != 1) || !sku.matches("^[a-zA-Z]*$"))
          {
            throw new InvalidSku(sku);
          }
          int unitPrice = Integer.parseInt(parts[1]);
          IPriceRecord priceRecord = new PriceRecord(sku, unitPrice);
          this.priceRecords.put(sku, priceRecord);
        }
        else if (parts.length == 4)
        {
          String sku = parts[0];
          int unitPrice = Integer.parseInt(parts[1]);
          int multiBuyUnits = Integer.parseInt(parts[2]);
          int multiBuyPrice = Integer.parseInt(parts[3]);
          IPriceRecord priceRecord = new PriceRecord(sku, unitPrice, multiBuyUnits, multiBuyPrice);
          this.priceRecords.put(sku, priceRecord);
        }
        else
        {
          throw new InstantiationException("Invalid Price Record - must have 2 or 4 fields");
        }
      }
    }
  }
}
