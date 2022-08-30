package com.sjb8080.checkout;

import java.util.Scanner;

public class InputProcessor implements IInputProcessor {
  private IReceipt receipt;

  @SuppressWarnings("unused")
  private InputProcessor(){}

  public InputProcessor(IReceipt receipt)
  {
    this.receipt = receipt;
  }

  @Override
  public boolean processInput(Scanner scanner) throws InvalidSku, PriceMissingException
  {
    String line = scanner.nextLine();

    if (line.length() == 1)
    {
      receipt.addItem(line);
    }
    else if (line.length() == 0)
    {
      return false;
    }
    else
    {
      throw new InvalidSku(line);
    }
    return true;
  }
}
