package com.sjb8080.checkout;

public interface IPriceRecord {
  String getSku();
  int getUnitPrice();
  MultiBuy getMultiBuy(int units) ;
}
