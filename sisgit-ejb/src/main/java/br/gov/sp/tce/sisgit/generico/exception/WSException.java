package br.gov.sp.tce.sisgit.generico.exception;

public class WSException extends Exception {
	private static final long serialVersionUID = -7196196879307524101L;

	public WSException() {
		super();
	}

	public WSException(String message) {
		super(message);
	}

	public WSException(Throwable throwable) {
		super(throwable);
	}

	public WSException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
