package br.gov.sp.tce.sisgit.controleacesso.exception;

import br.gov.sp.tce.sisgit.infraestrutura.exception.NegocioException;

public class UsuarioNaoCadastradorException extends NegocioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1584966244338823440L;

	@Override
	public String getCodigoDeErro() {
		return "controleacesso.exception.usuarioNaoCadastradorException";
	}

}
