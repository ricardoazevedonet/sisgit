package br.gov.sp.tce.sisgit.generico.exception;

/**
 * Exceção para erros de autenticação
 * 
 * @author dvivencio
 *
 */
public class AutenticacaoException extends RuntimeException {

	private static final long serialVersionUID = 8090887743223956707L;

	public AutenticacaoException() {
		super();
	}

	public AutenticacaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public AutenticacaoException(String message) {
		super(message);
	}

	public AutenticacaoException(Throwable cause) {
		super(cause);
	}

	
}
