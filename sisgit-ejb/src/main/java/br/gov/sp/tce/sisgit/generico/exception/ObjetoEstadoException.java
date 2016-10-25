package br.gov.sp.tce.sisgit.generico.exception;

/**
 * RuntimeException para erros envolvendo fluxo do estado dos Objetos (Pacote e Remessa)
 * 
 * @author p-mvicente
 * @version 1.0
 * @since 1.0
 * 
 */
public class ObjetoEstadoException extends RuntimeException {

	private static final long serialVersionUID = 8911154993292067586L;

	public ObjetoEstadoException() {
		super("Erro no fluxo de Objeto no Sistema");
	}

	public ObjetoEstadoException(String message) {
		super(message);
	}

}
