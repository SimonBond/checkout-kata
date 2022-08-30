package com.sjb8080.checkout;

import java.util.Scanner;

public interface IInputProcessor {
  boolean processInput(Scanner scanner) throws InvalidSku, PriceMissingException;
}
