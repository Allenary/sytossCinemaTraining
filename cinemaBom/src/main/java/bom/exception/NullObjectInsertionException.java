package bom.exception;

public class NullObjectInsertionException extends RuntimeException {

  public NullObjectInsertionException(String message) {
    super(message);
  }

  public NullObjectInsertionException() {
    this("Null object cannot be added");
  }
}
