/* 
* This program is free software: you can redistribute it and/or modify  
* it under the terms of the GNU General Public License as published by  
* the Free Software Foundation, version 3.
*
* This program is distributed in the hope that it will be useful, but 
* WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
* General Public License for more details.
*
* Nombre de archivo: AplicationController.java
* Autor: eddiaz
* Fecha de creación: 7 sep. 2021
*/

package mx.tis.com.application.controller;

import mx.tis.com.application.service.CompoundInterestCalculator;

/**
 * The Class AplicationController.
 */
public class AplicationController {
  
  /** The calculator. */
  private CompoundInterestCalculator calculator;

  /**
   * Gets the calculator.
   *
   * @return the calculator
   */
  public CompoundInterestCalculator getCalculator() {
    return calculator;
  }

  /**
   * Sets the calculator.
   *
   * @param calculator the new calculator
   */
  public void setCalculator(CompoundInterestCalculator calculator) {
    this.calculator = calculator;
  }
  
  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    
  }
}
