
package mx.tis.com.application.service.impl;

import mx.tis.com.application.dto.InitialInvestmentDto;
import mx.tis.com.application.dto.InvestmentYieldDto;
import mx.tis.com.application.service.CompoundInterestCalculator;
import java.util.ArrayList;
import java.util.List;


public class CompoundInterestCalculatorImpl implements CompoundInterestCalculator {

  @Override
  public ArrayList<InvestmentYieldDto> createRevenueGrid(InitialInvestmentDto initialInvestment) {
    ArrayList<InvestmentYieldDto> investmentYieldGrid = new ArrayList<>();
    InvestmentYieldDto resultInvestmentYield;
    Double initialInv = 0.0;
    Double yerlyInp = 0.0;
    Double invYield = 0.0;
    Float investmentYield = 0.0f;
    Double finalBal = 0.0;
    Integer investmentYears = initialInvestment.getInvestmentYears();

    for (int i = 0; i < investmentYears; i++) {
      if (i == 0) {
        initialInv = initialInvestment.getInitialInvestment();
        yerlyInp = initialInvestment.getYearlyInput();
        investmentYield = initialInvestment.getInvestmentYield();
        invYield = (initialInv + yerlyInp) * (investmentYield / 100);
        finalBal = initialInv + yerlyInp + invYield;
        resultInvestmentYield = new InvestmentYieldDto(i, initialInv, yerlyInp, invYield, finalBal);
        investmentYieldGrid.add(resultInvestmentYield);
        initialInvestment.setInitialInvestment(finalBal);
      }
      if(i!=0) {
        initialInv = initialInvestment.getInitialInvestment();
        yerlyInp = (initialInvestment.getYearlyInput()) * 
                   (1 + (initialInvestment.getYearlyInputIncrement() / 100));
        invYield = (initialInv + yerlyInp) * (initialInvestment.getInvestmentYield() / 100);
        finalBal = initialInv + yerlyInp + invYield;
        resultInvestmentYield = new InvestmentYieldDto(i, initialInv, yerlyInp, invYield, finalBal);
        investmentYieldGrid.add(resultInvestmentYield);
        initialInvestment.setInitialInvestment(finalBal);
        initialInvestment.setYearlyInput(yerlyInp);
      }
    }

    return investmentYieldGrid;
  }


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

  private void setDefaultValues(InitialInvestmentDto initialInvestmentDto) {
    Double yearlyInput = initialInvestmentDto.getYearlyInput();
    yearlyInput = yearlyInput == null ? 0.0 : yearlyInput;
    initialInvestmentDto.setYearlyInput(yearlyInput);

    Float yearlyInputIncrement = initialInvestmentDto.getYearlyInputIncrement();
    yearlyInputIncrement = yearlyInputIncrement == null ? 0 : yearlyInputIncrement;
    initialInvestmentDto.setYearlyInputIncrement(yearlyInputIncrement);

  }

}
