

package mx.tis.com.infraestructure.exception;

import mx.tis.com.application.controller.CalculatorInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * The Class GlobalExceptionHandler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Bad request.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler({CalculatorInputException.class})
  public ResponseEntity<ErrorResponse> badRequest(CalculatorInputException ex) {
    return new ResponseEntity<>(new ErrorResponse(1001, ex.getMessage(), "Input validation",
        "reenvie la petición con valores de inversión corrector"), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<ErrorResponse> internalServerException(Exception ex) {
    return new ResponseEntity<>(
        new ErrorResponse(1002, ex.getMessage(), "Error interno en la app", null),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
