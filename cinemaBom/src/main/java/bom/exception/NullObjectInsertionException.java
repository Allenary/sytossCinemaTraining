package bom.exception;

public class NullObjectInsertionException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  public NullObjectInsertionException(String message){
    super(message);
  }
  public NullObjectInsertionException(){
   this("Null object cannot be added");
  }

}
