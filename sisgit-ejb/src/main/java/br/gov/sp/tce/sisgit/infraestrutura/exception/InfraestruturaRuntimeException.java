package br.gov.sp.tce.sisgit.infraestrutura.exception;


public class InfraestruturaRuntimeException extends NegocioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2320594460275832141L;

	@Override
	public String getCodigoDeErro() {
		return "infraestrutura.exception.infraestruturaRuntimeException";
	}

}
