package bom.exception;

public class ReassignObjectException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public ReassignObjectException() {
    this("field already set in object. Object could not be reassigned.");
  }

  public ReassignObjectException(String message) {
    super(message);
  }

}
