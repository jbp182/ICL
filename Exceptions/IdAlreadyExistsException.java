package Exceptions;

public class IdAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public IdAlreadyExistsException(String msg) {
		super(msg);
	}

}
