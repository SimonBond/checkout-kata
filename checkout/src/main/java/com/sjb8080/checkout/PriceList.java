package com.sjb8080.checkout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PriceList {
  private int version;
  private Map<String, PriceRecord> priceRecords;

  public PriceList()
  {
    version = 0;
    priceRecords = new HashMap<>();
  }

  public void setVersion(int version)
  {
    this.version = version;
  }

  public int getVersion()
  {
    return this.version;
  }

  public PriceRecord getPriceRecord(String sku)
  {
    return priceRecords.get(sku);
  }

  public void addPriceRecord(PriceRecord priceRecord)
  {
    priceRecords.put(priceRecord.getSku(), priceRecord);
  }

  public void readPriceList(String filePath) throws IOException
  {
    FileReader in = null;
    BufferedReader buffReader = null;

    try
    {
      in = new FileReader(filePath);
      buffReader = new BufferedReader(in);
  
      while (buffReader.ready()) 
      {
        String line = buffReader.readLine();

        String[] parts = line.split(",");

        if (parts.length == 2)
        {
          String sku = parts[0];
          int unitPrice = Integer.parseInt(parts[1]);
          PriceRecord priceRecord = new PriceRecord(sku, unitPrice);
          this.priceRecords.put(sku, priceRecord);
        }
        else if (parts.length == 4)
        {
          String sku = parts[0];
          int unitPrice = Integer.parseInt(parts[1]);
          int multiBuyUnits = Integer.parseInt(parts[2]);
          int multiBuyPrice = Integer.parseInt(parts[3]);
          PriceRecord priceRecord = new PriceRecord(sku, unitPrice, multiBuyUnits, multiBuyPrice);
          this.priceRecords.put(sku, priceRecord);
        }
        else
        {
          // ERROR
        }
      }
    }
    catch (InstantiationException e)
    {

    }
    finally{
      if (buffReader != null)
      {
        buffReader.close();
      }
      if (in != null)
      { 
        in.close();
      }
    }
  }
}
