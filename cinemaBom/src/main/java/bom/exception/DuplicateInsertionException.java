package bom.exception;

public class DuplicateInsertionException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public DuplicateInsertionException() {
    this("This Object already is in collection. Could not be added second time.");
  }

  public DuplicateInsertionException(String message) {
    super(message);
  }

}
