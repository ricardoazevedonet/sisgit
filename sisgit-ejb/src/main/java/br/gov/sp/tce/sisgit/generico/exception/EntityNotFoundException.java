/**
 * 
 */
package br.gov.sp.tce.sisgit.generico.exception;



/**
 * Exceção usada para indicar que não foi encontrada uma {@link EntidadeAbstrata Entidade} que se esperava.
 * 
 * @author p-rafonso
 *
 */
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8181119195234492419L;

	public EntityNotFoundException() {
		super();
	}

	/**
	 * @param message
	 */
	public EntityNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public EntityNotFoundException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
