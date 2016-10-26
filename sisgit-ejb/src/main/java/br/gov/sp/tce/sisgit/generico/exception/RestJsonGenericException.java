package br.gov.sp.tce.sisgit.generico.exception;

public class RestJsonGenericException extends RuntimeException {

	private static final long serialVersionUID = 1527656744305444534L;
	
	public RestJsonGenericException() {
		super();
	}
	
	/**
	 * @param message
	 */
	public RestJsonGenericException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public RestJsonGenericException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RestJsonGenericException(String message, Throwable cause) {
		super(message, cause);
	}

}