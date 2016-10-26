package br.gov.sp.tce.sisgit.infraestrutura.exception;

public abstract class NegocioException extends Exception {
	private static final long serialVersionUID = 5879267262758190898L;

	private Object[] args;
	
	/** Proximo erro de valida��o da cadeia. */
    private NegocioException next = null;

	public NegocioException() {
	}

	public NegocioException(Throwable cause) {
		super(cause);
	}

	public NegocioException(Object[] args) {
		this.args = args;
	}

	public NegocioException(Object[] args, Throwable cause) {
		super(cause);

		this.args = args;
	}

	/**
	 * @return o valor do atributo <code>args</code>
	 */
	public Object[] getArgs() {
		return args;
	}

	/**
	 * @return o c�digo de erro da mensagem, que ser� utilizado para recuperar a
	 *         mensagem de erro do arquivo "MensagemErro.properties"
	 */
	public abstract String getCodigoDeErro();

	public void addNext(NegocioException exception) {

		NegocioException veAtual = this;
		while (veAtual.getNext() != null) {
			veAtual = veAtual.getNext();
		}
		veAtual.setNext(exception);

	}
	
    public void setNext( NegocioException exception ) {
    	
    	next = exception;
    	
    }
    
    public NegocioException getNext() {
    	
        return next;
        
    }

}
