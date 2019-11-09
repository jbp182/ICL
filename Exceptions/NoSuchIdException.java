package Exceptions;

public class NoSuchIdException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NoSuchIdException(String msg) {
		super(msg);
	}

}
