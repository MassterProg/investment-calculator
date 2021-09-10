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
* Nombre de archivo: CompoundInterestCalculatorImpl.java
* Autor: eddiaz
* Fecha de creación: 9 sep. 2021
*/

package mx.tis.com.application.service.impl;

import mx.tis.com.application.dto.InitialInvestmentDto;
import mx.tis.com.application.dto.InvestmentYieldDto;
import mx.tis.com.application.service.CompoundInterestCalculator;
import java.util.ArrayList;


/**
 * The Class CompoundInterestCalculatorImpl.
 */
public class CompoundInterestCalculatorImpl implements CompoundInterestCalculator {

  /**
   * Creates the revenue grid.
   *
   * @param initialInvestment the initial investment
   * @return the array list
   */
  @Override
  public ArrayList<InvestmentYieldDto> createRevenueGrid(InitialInvestmentDto initialInvestment) {

    ArrayList<InvestmentYieldDto> investmentYieldGrid = new ArrayList<>();
    InvestmentYieldDto resultInvestmentYield;

    Double initialInv = initialInvestment.getInitialInvestment();
    Double yerlyInp = initialInvestment.getYearlyInput();
    Double invYield = 0.0;
    Float investmentYield = initialInvestment.getInvestmentYield();
    Double finalBal = 0.0;
    Integer investmentYears = initialInvestment.getInvestmentYears();

    for (int i = 0; i < investmentYears; i++) {
      if (i == 0) {
        invYield = (initialInv + yerlyInp) * (investmentYield / 100);
        invYield = this.roundDouble(invYield);
        finalBal = initialInv + yerlyInp + invYield;
        resultInvestmentYield = new InvestmentYieldDto(i, initialInv, yerlyInp, invYield, finalBal);
        investmentYieldGrid.add(resultInvestmentYield);
        initialInvestment.setInitialInvestment(finalBal);
      }
      if (i != 0) {
        initialInv = initialInvestment.getInitialInvestment();
        yerlyInp = (initialInvestment.getYearlyInput())
            * (1 + (initialInvestment.getYearlyInputIncrement() / 100));
        yerlyInp = this.roundDouble(yerlyInp);
        invYield = (initialInv + yerlyInp) * (initialInvestment.getInvestmentYield() / 100);
        invYield = this.roundDouble(invYield);
        finalBal = initialInv + yerlyInp + invYield;
        resultInvestmentYield = new InvestmentYieldDto(i, initialInv, yerlyInp, invYield, finalBal);
        investmentYieldGrid.add(resultInvestmentYield);
        initialInvestment.setInitialInvestment(finalBal);
        initialInvestment.setYearlyInput(yerlyInp);
      }
    }

    return investmentYieldGrid;
  }

  private double roundDouble(Double number) {
    return Math.ceil(number);
    
  }

  /**
   * Validate input.
   *
   * @param initialInvestmentDto the initial investment dto
   * @return true, if successful
   */
  @Override
  public boolean validateInput(InitialInvestmentDto initialInvestmentDto) {

    this.setDefaultValues(initialInvestmentDto);
    boolean valid = true;

    valid = valid && (initialInvestmentDto.getInitialInvestment() > 1_000);
    valid = valid && (initialInvestmentDto.getYearlyInput() >= 0.0);
    valid = valid && (initialInvestmentDto.getYearlyInputIncrement() >= 0);
    valid = valid && (initialInvestmentDto.getInvestmentYield() > 0.0);
    return valid;


    /*
     * COMO LO PENSE EN UN INICIO if(input.getInitialInvestment()>1_000 && input.getYearlyInput()>=
     * 0 && input.getYearlyInputIncrement()>= 0 && input.getInvestmentYield()>=0){ return true;
     * }else { return false; }
     */
  }

  /**
   * Sets the default values.
   *
   * @param initialInvestmentDto the new default values
   */
  private void setDefaultValues(InitialInvestmentDto initialInvestmentDto) {
    Double yearlyInput = initialInvestmentDto.getYearlyInput();
    yearlyInput = yearlyInput == null ? 0.0 : yearlyInput;
    initialInvestmentDto.setYearlyInput(yearlyInput);

    Float yearlyInputIncrement = initialInvestmentDto.getYearlyInputIncrement();
    yearlyInputIncrement = yearlyInputIncrement == null ? 0 : yearlyInputIncrement;
    initialInvestmentDto.setYearlyInputIncrement(yearlyInputIncrement);

  }

}
