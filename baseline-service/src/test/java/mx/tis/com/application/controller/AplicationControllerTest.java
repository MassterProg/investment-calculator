package mx.tis.com.application.controller;

import mx.tis.com.application.dto.InitialInvestmentDto;
import mx.tis.com.application.dto.InvestmentYieldDto;
import mx.tis.com.application.service.CompoundInterestCalculator;
import mx.tis.com.application.service.impl.CompoundInterestCalculatorImpl;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.assertEquals;




public class AplicationControllerTest {

  private AplicationController controller;
  private InitialInvestmentDto initialInvestment;
  private CompoundInterestCalculator calculator;

  @Before
  public void createValuesBeforeToTest() {
    // instanciar variables
    this.calculator = new CompoundInterestCalculatorImpl();
    this.controller = new AplicationController(this.calculator);

    this.initialInvestment = new InitialInvestmentDto();

    this.initialInvestment.setInitialInvestment(5000.00);
    this.initialInvestment.setYearlyInput(3000.00);
    this.initialInvestment.setYearlyInputIncrement(1f);
    this.initialInvestment.setInvestmentYears(5);
    this.initialInvestment.setInvestmentYield(21f);
  }

  @Test
  public void shouldGenerateTableYield() {
    List<InvestmentYieldDto> tableYield = this.controller.createTableYield(this.initialInvestment);
    assertEquals(5, tableYield.size());
    InvestmentYieldDto firstYear = tableYield.get(1);
    
//    assertEquals(Double.valueOf(3030.00), firstYear.getYearlyInput());
//    assertEquals(Double.valueOf(15380.00), firstYear.getFinalBalance());
    System.out.println(tableYield.get(3).getInitialInvestment());
    System.out.println(tableYield.get(3).getYearlyInput());
    System.out.println(tableYield.get(3).getInvestmentYield());
    System.out.println(tableYield.get(3).getFinalBalance());
  }



}
